package org.lappsgrid.client;

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.soap.SoapClientFactory;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.WebService;
import org.lappsgrid.core.DataFactory;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Keith Suderman
 */
public abstract class AbstractClient
{
	protected WebService service;

	public AbstractClient(String endpoint) throws ServiceException
	{
		try
		{
			URL url = new URL(endpoint);
			service = new SoapClientFactory().create(DataSource.class, url);
		}
		catch (MalformedURLException e)
		{
			throw new ServiceException("Unable to create SOAP client.", e);
		}
	}

	public AbstractClient(String endpoint, String username, String password) throws ServiceException
	{
		try
		{
			URL url = new URL(endpoint);
			service = new SoapClientFactory().create(DataSource.class, url, username, password);
		}
		catch (MalformedURLException e)
		{
			throw new ServiceException("Unable to create SOAP client.", e);
		}
	}

	public void setToken(String token)
	{
		String header = "Bearer " + token;
//		System.out.println("AbstractClient.setToken: Setting the access token: " + header);
		RequestAttributes attributes = (RequestAttributes) service;
		attributes.addRequestMimeHeader("Authorization", header);
		System.out.println("Setting OAuth-Authorization header to " + header);
		attributes.addRequestMimeHeader("OAuth-Authorization", header);
	}

	public String execute(String input)
	{
		return service.execute(input);
	}

	public String getMetadata()
	{
		try
		{
			return service.getMetadata();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return DataFactory.error(e.getMessage());
		}
	}

}
