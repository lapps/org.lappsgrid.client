/*-
 * Copyright 2014 The Language Application Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.lappsgrid.client;


import org.lappsgrid.api.DataSource;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.datasource.ListRequest;
import org.lappsgrid.serialization.datasource.SizeRequest;

import javax.xml.rpc.ServiceException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DataSourceClient provides several helper methods that parse the JSON
 * returned by the service into normal Java types.
 *
 * @author Keith Suderman
 */
public class DataSourceClient extends AbstractClient implements DataSource
{
	public DataSourceClient(String endpoint) throws ServiceException
	{
		super(endpoint);
	}

   public DataSourceClient(String endpoint, String username, String password) throws ServiceException
   {
		super(endpoint, username, password);
   }

	/**
	 * Sends a LIST request (<a href="http://vocab.lappsgrid.org/ns/action/list">http://vocab.lappsgrid.org/ns/action/list</a>)
	 * to the data source and parse the result into a {@link java.util.List} of strings.
	 * <p>Users should be prepared to handle cases where a Datasource refuses to respond to
	 * list requests.  In these cases the Datasource will return a Data object with the discriminator
	 * set to <a href="http://vocab.lappsgrid.org/ns/error">http://vocab.lappsgrid.org/ns/error</a>.
	 * In these cases uses should call the {@link DataSourceClient#size()} method the
	 * {@link DataSourceClient#list(int, int)} method to paginate the index.
	 *
	 * @return A list of document ID values.
	 */
   public List<String> list()
   {
		String json = service.execute(new ListRequest().asJson());
		Data data = Serializer.parse(json, Data.class);
		Object payload = data.getPayload();
		if (payload == null) {
			System.out.println("Service did not return a payload.");
			System.out.println("Discriminator is: " + data.getDiscriminator());
			System.out.println(json);
			return null;
		}
		System.out.println("Payload is a " + payload.getClass().getCanonicalName());
		System.out.println(payload.toString());
		List<String> result = new ArrayList<>();
		if (data.getDiscriminator().equals(Discriminators.Uri.ERROR))
		{
			System.out.println(data.getPayload().toString());
			return result;
		}
		if (payload instanceof List)
		{
			return (List<String>) payload;
		}
		result.add(payload.toString());
		return result;
   }

	/**
	 * Sends LIST request (<a href="http://vocab.lappsgrid.org/ns/action/list">http://vocab.lappsgrid.org/ns/action/list</a>)
	 * with {@code start} and {@code end} offsets to the data source and parse the result into a {@link java.util.List}
	 * of strings.
	 *
	 * @return A list of document ID values.
	 */
   public List<String> list(int start, int end)
   {
		String json = service.execute(DataFactory.list(start, end));
		Data<List<String>> data = Serializer.parse(json, Data.class);
		return data.getPayload();
   }

	/**
	 * Retrieves a single document from the datasource.
	 *
	 * @param key a document ID.
	 * @return the document from the data source.  Typically this will be a LIF container
	 * inside a Data object.
	 */
   public String get(String key)
   {
      return service.execute(DataFactory.get(key));
   }

	/**
	 * Returns the number of documents in the datasource.
	 * <p>
	 * Datasources containing a large number of documents may refuse to return a full listing
	 * of document IDs.
	 * @return
	 */
   public int size()
   {
		String json = service.execute(new SizeRequest().asJson());
		Data<Integer> data = Serializer.parse(json, Data.class);
		return data.getPayload();
   }
}
