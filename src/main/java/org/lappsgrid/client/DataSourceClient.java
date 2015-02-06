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
import org.lappsgrid.serialization.Error;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.datasource.Get;
import org.lappsgrid.serialization.datasource.List;
import org.lappsgrid.serialization.datasource.Size;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * Client class for objects that want to access LAPPS DataSources.
 *
 * @author Keith Suderman
 */
public class DataSourceClient extends AbstractSoapClient implements DataSource
{
   public DataSourceClient(String endpoint, String username, String password) throws ServiceException
   {
      super(endpoint, endpoint);
      super.setCredentials(username, password);
//      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
//      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
//      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
//      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

   public DataSourceClient(String namespace, String endpoint) throws ServiceException
   {
      super(namespace, endpoint);
//      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
//      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
//      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
//      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

   @Override
   public String execute(String input)
   {
      String result;
      try
      {
         result = super.callExecute(input).toString();
      }
      catch (RemoteException e)
      {
         Error error = new Error();
         error.setPayload(e.getMessage());
         result = Serializer.toJson(error);
      }
      return result;
   }

	@Override
	public String getMetadata()
	{
		String result;
		try
		{
			result = super.callGetMetadata();
		}
		catch (RemoteException e)
		{
			result = new Error(e.getMessage()).asJson();
		}
		return result;
	}

   public String list()
   {
      return dispatch(new List());
   }

   public String list(int start, int end)
   {
      return dispatch(new List(start, end));
   }

   public String get(String key)
   {
      return dispatch(new Get(key));
   }

   public String size()
   {
      return dispatch(new Size());
   }
}
