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

import jp.go.nict.langrid.client.RequestAttributes;
import jp.go.nict.langrid.client.soap.SoapClientFactory;
import org.lappsgrid.api.WebService;
import org.lappsgrid.core.DataFactory;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The AbstractClient provides all of the functionality needed to interact
 * with a LAPPS web service.
 *
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
			service = new SoapClientFactory().create(WebService.class, url);
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
			service = new SoapClientFactory().create(WebService.class, url, username, password);
		}
		catch (MalformedURLException e)
		{
			throw new ServiceException("Unable to create SOAP client.", e);
		}
	}

	public void setToken(String token)
	{
		String header = "Bearer " + token;
		RequestAttributes attributes = (RequestAttributes) service;
		attributes.addRequestMimeHeader("Authorization", header);
		attributes.addRequestMimeHeader("X-Langrid-Service-Authorization", header);
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
			//e.printStackTrace();
			return DataFactory.error(e.getMessage());
		}
	}

}
