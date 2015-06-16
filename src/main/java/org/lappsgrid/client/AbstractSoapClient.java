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
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.lappsgrid.serialization.*;
import org.lappsgrid.serialization.Error;

/**
 * This class is not used and will be deprecated in an upcoming release.
 * <p>
 * The AbstractSoapClient manages the Axis Service and Call objects
 * and is used invokes methods on the remote service. Typically users will
 * extend the AbstractSoapClient to provide Java methods that invoke the service methods.
 * <pre>
 * class CustomSoapClient extends AbstractSoapClient {
 *    public CustomSoapClient() {
 *       super(SERVICE_NAMESPACE, ENDPOINT_URL);
 *    }
 *
 *    String serviceMethod(String arg1, String arg2) {
 *       Object[] serviceArgs = new String[] { arg1, arg2 };
 *       return (String) super.invoke("serviceMethod", args);
 *    }
 *    ...
 * }
 * </pre>
 * <b>NOTE:</b> Users should use the clients created by the Service Grid's
 * SoapClientFactory instead.
 *
 * @author Keith Suderman
 */
public abstract class AbstractSoapClient
{
	protected String namespace;
	protected String endpoint;

	protected Service service;
	protected Call call;

	public AbstractSoapClient() throws ServiceException
	{
		service = new Service();
		call = (Call) service.createCall();
	}

	public AbstractSoapClient(String namespace) throws ServiceException
	{
		this();
		this.namespace = namespace;
	}

	public AbstractSoapClient(String namespace, String endpoint) throws ServiceException
	{
		this();
		this.namespace = namespace;
		this.endpoint = endpoint;
		call.setTargetEndpointAddress(endpoint);
	}

	public void setNamespace(String namespace)
	{
		this.namespace = namespace;
	}

	public void setEndpoint(String endpoint)
	{
		call.setTargetEndpointAddress(endpoint);
	}

	public void setCredentials(String username, String password)
	{
		call.setUsername(username);
		call.setPassword(password);
	}

	public String getNamespace() { return namespace; }
	public String getEndpoint() { return endpoint; }

	/**
	 * Invokes a method on a remote service.
	 *
	 * @param method The name of the method to be invoked on the
	 * remote service.
	 * @return the object returned from the remote method.
	 * @throws RemoteException
	 */
	public Object invoke(String method) throws RemoteException
	{
		return invoke(method, new Object[0]);
	}

	/**
	 * Invokes a method on a remote service.
	 *
	 * @param method The name of the method to be invoked on the
	 * remote service.
	 * @param args Any parameters that should be passed to the
	 * remote method.
	 * @return the object returned from the remote method.
	 * @throws RemoteException
	 */
	public Object invoke(String method, Object[] args) throws RemoteException
	{
		call.setOperationName(new QName(namespace, method));
		return call.invoke(args);
	}

	public String callExecute(String input) throws RemoteException
	{
		Object[] args = new Object[] { input };
		call.setOperationName(new QName(namespace, "execute"));
		return call.invoke(args).toString();
	}

	public String callGetMetadata() throws RemoteException
	{
		Object[] args = new Object[] { };
		call.setOperationName(new QName(namespace, "getMetadata"));
		return call.invoke(args).toString();
	}

	public String callExecute(Data<?> input) throws RemoteException
	{
		return callExecute(Serializer.toJson(input));
	}

	protected String dispatch(Data<?> data)
	{
		String result;
		try
		{
			result = callExecute(data);
		}
		catch (RemoteException e)
		{
			org.lappsgrid.serialization.Error error = new Error();
			error.setPayload(e.getMessage());
			result = Serializer.toJson(error);
		}
		return result;
	}

	protected String dispatch(String json)
	{
		String result;
		try
		{
			result = callExecute(json);
		}
		catch (RemoteException e)
		{
			Error error = new Error();
			error.setPayload(e.getMessage());
			result = Serializer.toJson(error);
		}
		return result;
	}

}
