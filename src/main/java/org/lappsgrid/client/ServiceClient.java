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

//import org.lappsgrid.api.Data;
import org.lappsgrid.experimental.api.WebService;
import org.lappsgrid.core.DataFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.lappsgrid.serialization.aas.Token;
import org.lappsgrid.serialization.service.GetMetadata;

/**
 * @author Keith Suderman
 */
public class ServiceClient extends AbstractSoapClient implements WebService
{
   public ServiceClient(String url) throws ServiceException
   {
      this(url, null, null);
   }

   public ServiceClient(String url, String user, String password) throws ServiceException
   {
      super(url, url);
      super.setCredentials(user, password);
//      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
//      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
//      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
//      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

   public String getMetadata(Token token)
   {
      return dispatch(new GetMetadata(token));
   }

   public String execute(String json)
   {
      return dispatch(json);
   }

//   public ServiceClient(Server server, String endpoint) throws ServiceException
//   {
//      this(server.getUrl() + "/service_manager/invoker/" + endpoint, server.getUser(), server.getPassword());
//   }

//   @Override
//   public long[] requires()
//   {
//      throw new UnsupportedOperationException("This method has been deprecated.");
//   }
//
//   @Override
//   public long[] produces()
//   {
//      throw new UnsupportedOperationException("This method has been deprecated.");
//   }


   /*
   @Override
   public Data getMetadata()
   {
      try
      {
         return (Data) super.invoke("getMetadata");
      }
      catch (RemoteException e)
      {
         return DataFactory.error(e.getMessage());
      }
   }

   @Override
   public Data execute(Data input)
   {
      Object[] args = { input };
      try
      {
         return (Data) super.invoke("execute", args);
      }
      catch (RemoteException e)
      {
         //TODO This error should be logged.
         return DataFactory.error(e.getMessage());
      }
   }

   @Override
   public Data configure(Data config)
   {
      Object[] args = { config };
      try
      {
         return (Data) super.invoke("configure", args);
      }
      catch (RemoteException e)
      {
         //TODO This error should be logged.
         return DataFactory.error(e.getMessage());
      }
   }
   */
}
