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
//   public static final String ROOT_URL = "http://grid.anc.org:8080/service_manager/invoker";
//   public static final String DATASOURCE_URL = ROOT_URL + "/anc:test.datasource_1.0.0";

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
//		TestClient client = new TestClient();
//		System.out.println(client.callGetMetadata());

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
//		attributes.addRequestMimeHeader("X-Langrid-Service-Authorization: ", "Bearer oauth");
		attributes.addRequestMimeHeader("Authorization", "Bearer 123abc");
		String result = service.execute(new ListRequest(0,10).asJson());
		assertNotNull(result);
		Data<Object> data = Serializer.parse(result, Data.class);
		assertNotEquals(data.getPayload().toString(), data.getDiscriminator(), Uri.ERROR);
		System.out.println(result);
	}

	@Ignore
	public void testServiceGridSoapClientFactory() throws MalformedURLException
	{
		URL url = new URL("http://localhost:8080/service_manager/invoker/picard:masc.text_2.0.0-SNAPSHOT");
		DataSource service = new SoapClientFactory().create(DataSource.class, url,  USER, PASS);

		RequestAttributes attributes = (RequestAttributes) service;
//		attributes.addRequestMimeHeader("Authorization: ", "Bearer 123abc");
		attributes.addRequestMimeHeader("X-Langrid-Service-Authorization", "Bearer 123abc");
		String result = service.execute(new ListRequest(0,10).asJson());
		assertNotNull(result);
		Data<Object> data = Serializer.parse(result, Data.class);
		assertNotEquals(data.getPayload().toString(), data.getDiscriminator(), Uri.ERROR);
		System.out.println(result);
	}

//   @Ignore
//   public void testDataSource() throws ServiceException
//   {
//      DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
//		RequestAttributes attributes = (RequestAttributes) client;
//		attributes.addRequestMimeHeader("X-WWW-Authentication: ", "oauth");
//		attributes.addRequestMimeHeader("X-OAUTH-TOKEN", UUID.randomUUID().toString());
//      String json = Serializer.toJson(new ListRequest());
//      json = client.execute(json);
//      Map response = Serializer.parse(json, HashMap.class);
//		String discriminator = response.get("discriminator").toString();
//		Object payload = response.get("payload");
//      assertFalse(payload.toString(), Uri.ERROR.equals(discriminator));
//      //System.out.println(response);
//   }

	@Ignore
	public void testDataSourceList() throws ServiceException
	{
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
		//String json = DataFactory.list()
		java.util.List<String> list = client.list();
		assertTrue(list.size() > 0);
		assertTrue(list.size() == client.size());
		assertTrue(client.size() == 392);
	}

	@Ignore
	public void testDataSourceListWithOffsets() throws ServiceException
	{
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
		//String json = DataFactory.list()
		List<String> list = client.list(0, 100);
		assertTrue("Invalid list size, expected 100 found " + list.size(), list.size() == 100);
	}

	@Ignore
	public void testDataSourceGet() throws ServiceException
	{
		DataSourceClient client = new DataSourceClient("http://localhost:9080/MascDataSource/2.0.0-SNAPSHOT/services/MascTextSource", null, null);
		//String json = DataFactory.list()
		List<String> list = client.list();
		assertTrue(list.size() == 392);
		String json = client.get(list.get(0));
		Data<String> data = Serializer.parse(json, Data.class);
		assertEquals(data.getDiscriminator(), Uri.TEXT);
		String text = data.getPayload();
		System.out.println(text);
	}

   /*
   @Test
   public void testQuery() throws ServiceException
   {
      DataSourceClient client = new DataSourceClient(DATASOURCE_URL, USER, PASS);
      Data get = DataFactory.get("foo");
      Data result = client.query(get);
      long type = DiscriminatorRegistry.getType(result.getDiscriminator());
      assertTrue(type == Types.OK);

      Data list = DataFactory.list();
      result = client.query(list);
      type = DiscriminatorRegistry.getType(result.getDiscriminator());
      assertTrue(type == Types.OK);
   }

   @Test
   public void testGet() throws ServiceException
   {
      DataSourceClient client = new DataSourceClient(DATASOURCE_URL, USER, PASS);
      Data result = client.get("foo");
      long type = DiscriminatorRegistry.getType(result.getDiscriminator());
      assertTrue(type == Types.OK);
   }

   // This test will always fails as the TestDataSource service's query method
   // always just returns a Data object with the discriminator set to OK and a
   // null payload.  This causes an NPE when the client attempts to
   @Ignore
   public void testList() throws ServiceException, LappsException
   {
      DataSourceClient client = new DataSourceClient(DATASOURCE_URL, USER, PASS);
      Data data = client.list();
      assertTrue(data != null);
      assertTrue(Uri.ONE_PER_LINE.equals(data.getDiscriminator()));
      String[] index = data.getPayload().split("\n");
      assertTrue(index.length > 0);
      System.out.println("Index size is " + index.length);
   }
   */
}
