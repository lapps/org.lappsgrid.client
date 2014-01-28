package org.lappsgrid.client.service;

import org.anc.soap.client.AbstractSoapClient;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.WebService;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.Types;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

/**
 * @author Keith Suderman
 */
public class ServiceClient extends AbstractSoapClient implements WebService
{
   public ServiceClient(String url, String user, String password) throws ServiceException
   {
      super(url, url);
      super.setCredentials(user, password);
      QName q = new QName ("uri:org.lappsgrid.api/", "Data");
      BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
      BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
      call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
   }

   public ServiceClient(Server server, String endpoint) throws ServiceException
   {
      this(server.getUrl() + "/service_manager/invoker/" + endpoint, server.getUser(), server.getPassword());
   }

   @Override
   public long[] requires()
   {
      try
      {
         return (long[]) super.invoke("requires");
      }
      catch (RemoteException e)
      {
         //TODO This error should be logged.
         e.printStackTrace();
         return new long[] { Types.ERROR };
      }
   }

   @Override
   public long[] produces()
   {
      try
      {
         return (long[]) super.invoke("produces");
      }
      catch (RemoteException e)
      {
         // TODO This error should be logged.
         e.printStackTrace();
         return new long[] { Types.ERROR };
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
}
