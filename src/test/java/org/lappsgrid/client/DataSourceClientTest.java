/*
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Assert.*;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.WebService;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.datasource.ListRequest;
import org.lappsgrid.serialization.lif.Container;

import javax.xml.rpc.ServiceException;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.lappsgrid.discriminator.Discriminators.Uri;

/**
 * @author Keith Suderman
 */
//@Ignore
public class DataSourceClientTest
{

   public static final String USER = "operator";
   public static final String PASS = "operator";


   public DataSourceClientTest()
   {

   }

	@Ignore
	public void testSetToken() throws ServiceException, RemoteException
	{
		System.out.println("DataSourceClientTest.testSetToken");
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource");
		client.setToken("123abc");
		List<String> list = client.list();
		assertTrue(list.size() == 392);
	}

	@Ignore
	public void testServiceGridToken() throws ServiceException
	{
		System.out.println("DataSourceClientTest.testServiceGridToken");
		DataSourceClient client = new DataSourceClient("http://localhost:8080/service_manager/invoker/picard:masc.text_2.0.0-SNAPSHOT", "operator", "operator");
		client.setToken("123abc");
		List<String> list = client.list();
		assertTrue("Expected 392 found " + list.size(), list.size() == 392);
	}

	@Ignore
	public void testConverterMetadata() throws ServiceException, RemoteException
	{
		String url = "http://localhost:9080/GateConverter/1.0.0-SNAPSHOT/services/GateToJson";
		WebService service = new ServiceClient(url, null, null);
		String json = service.getMetadata();
		System.out.println(json);
	}

	@Ignore
	public void testSoapClientFactory() throws MalformedURLException
	{
		URL url = new URL("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource");
		DataSource service = new SoapClientFactory().create(DataSource.class, url,  USER, PASS);

		RequestAttributes attributes = (RequestAttributes) service;
		attributes.addRequestMimeHeader("Authorization", "Bearer 123abc");
		String result = service.execute(new ListRequest(0,10).asJson());
		assertNotNull(result);
		Data<Object> data = Serializer.parse(result, Data.class);

		assertFalse(data.getPayload().toString(), data.getDiscriminator().equals(Uri.ERROR));
		System.out.println(result);
	}

	@Ignore
	public void testServiceGridSoapClientFactory() throws MalformedURLException
	{
		URL url = new URL("http://localhost:8080/service_manager/invoker/picard:masc.text_2.0.0-SNAPSHOT");
		DataSource service = new SoapClientFactory().create(DataSource.class, url,  USER, PASS);

		RequestAttributes attributes = (RequestAttributes) service;
		attributes.addRequestMimeHeader("X-Langrid-Service-Authorization", "Bearer 123abc");
		String result = service.execute(new ListRequest(0,10).asJson());
		assertNotNull(result);
		Data<Object> data = Serializer.parse(result, Data.class);
		assertFalse(data.getPayload().toString(), data.getDiscriminator().equals(Uri.ERROR));
		System.out.println(result);
	}


	@Ignore
	public void testDataSourceList() throws ServiceException
	{
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
		java.util.List<String> list = client.list();
		assertTrue(list.size() > 0);
		assertTrue(list.size() == client.size());
		assertTrue(client.size() == 392);
	}

	@Ignore
	public void testDataSourceListWithOffsets() throws ServiceException
	{
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
		List<String> list = client.list(0, 100);
		assertTrue("Invalid list size, expected 100 found " + list.size(), list.size() == 100);
	}

	@Ignore
	public void testDataSourceGet() throws ServiceException
	{
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
		List<String> list = client.list();
		assertTrue(list.size() == 392);
		String json = client.get(list.get(0));
		Data<String> data = Serializer.parse(json, Data.class);
		assertEquals(data.getDiscriminator(), Uri.TEXT);
		String text = data.getPayload();
		System.out.println(text);
	}

}
