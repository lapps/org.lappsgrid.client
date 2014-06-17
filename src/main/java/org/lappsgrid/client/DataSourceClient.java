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

import org.anc.soap.client.AbstractSoapClient;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.InternalException;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.Types;

import javax.xml.namespace.QName;
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
      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

   public DataSourceClient(String namespace, String endpoint) throws ServiceException
   {
      super(namespace, endpoint);
      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

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
         e.printStackTrace();
         result = DataFactory.error(e.getMessage());
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
         return DataFactory.error(e.getMessage());
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
         return DataFactory.error(e.getMessage());
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
         return DataFactory.error(e.getMessage());
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
}
