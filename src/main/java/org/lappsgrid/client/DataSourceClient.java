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

import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
//import org.lappsgrid.api.Data;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.InternalException;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.Types;
import org.lappsgrid.experimental.api.WebService;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Error;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.aas.Token;
import org.lappsgrid.serialization.datasource.Get;
import org.lappsgrid.serialization.datasource.List;
import org.lappsgrid.serialization.datasource.Size;
import org.lappsgrid.serialization.service.GetMetadata;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * Client class for objects that want to access LAPPS DataSources.
 *
 * @author Keith Suderman
 */
public class DataSourceClient extends AbstractSoapClient implements WebService
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
         result = super.execute(input).toString();
      }
      catch (RemoteException e)
      {
         Error error = new Error();
         error.setPayload(e.getMessage());
         result = Serializer.toJson(error);
      }
      return result;
   }

   public String list(Token token)
   {
      return dispatch(new List(token));
   }

   public String list(Token token, int start, int end)
   {
      return dispatch(new List(token, start, end));
   }

   public String get(Token token, String key)
   {
      return dispatch(new Get(token, key));
   }

   public String size(Token token)
   {
//      String json = dispatch(new Size(token));
//      HashMap<String,Object> response = Serializer.parse(json, HashMap.class);
//      return 0;
      return dispatch(new Size(token));
   }

   public String getMetadata(Token token)
   {
      return dispatch(new GetMetadata(token));
   }


   /*
   @Override
   public Data query(Data input)
   {
      Data[] args = { input };
      Data result = null;
      try
      {
         result = (Data) super.invoke("query", args);
      }
      catch (RemoteException e)
      {
         //e.printStackTrace();
         result = DataFactory.error(e);
      }
      return result;
   }

   public Data get(String key)
   {
      try
      {
         return (Data) super.invoke("get", new Object[] { key });
      }
      catch (RemoteException e)
      {
         return DataFactory.error(e);
      }
   }

	public Data getMetadata()
	{
		try
		{
			return (Data) super.invoke("getMetadata");
		}
		catch (RemoteException e)
		{
			return DataFactory.error("Unable to retrieve metadata.", e);
		}
	}

   public Data list()
   {
      try
      {
         return (Data) super.invoke("list");
      }
      catch (RemoteException e)
      {
         return DataFactory.error(e);
      }
   }

   public Data list(int start, int end)
   {
      Object[] args = { start, end };
      try
      {
         return (Data) super.invoke("list", args);
      }
      catch (RemoteException e)
      {
         return DataFactory.error(e);
      }
   }

   public int size()
   {
      try
      {
         return (Integer) super.invoke("size");
      }
      catch (RemoteException e)
      {
         return -1;
      }
   }
   */
}
