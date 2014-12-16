package org.lappsgrid.client.org.lappsgrid.experimental.client;

import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.lappsgrid.api.Data;
import org.lappsgrid.client.AbstractSoapClient;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class ServiceClient extends AbstractSoapClient
{
	public ServiceClient(String url) throws ServiceException
	{
		this(url, null, null);
	}

	public ServiceClient(String url, String user, String password) throws ServiceException
	{
		super(url, url);
		super.setCredentials(user, password);
		QName q = new QName ("uri:org.lappsgrid.api/", "Data");
		BeanSerializerFactory serializer =   new BeanSerializerFactory(Data.class,q);   // step 2
		BeanDeserializerFactory deserializer = new BeanDeserializerFactory(Data.class,q);  // step 3
		call.registerTypeMapping(Data.class, q, serializer, deserializer); //step 4
	}

}
