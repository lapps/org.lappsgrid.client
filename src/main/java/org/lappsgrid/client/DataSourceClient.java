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
      return this.query(DataFactory.get(key));
   }

   public String[] list() throws InternalException
   {
      Data result = this.query(DataFactory.list());
      if (result.getDiscriminator() == Types.ERROR)
      {
         throw new InternalException(result.getPayload());
      }
      String payload = result.getPayload();
      if (payload == null)
      {
         return new String[0];
      }
      return payload.split("\\s+");
   }
}
