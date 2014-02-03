package org.lappsgrid.client;

import org.anc.soap.client.SoapClient;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.client.datasource.*;
import org.lappsgrid.core.DataFactory;

import javax.xml.rpc.ServiceException;
import javax.xml.ws.Service;

import org.junit.*;
import org.lappsgrid.discriminator.Types;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
public class GigawordTest
{
   private static final String NAMESPACE = "http://langrid.nict.go.jp/ws_1_2/";
   private static final String ENDPOINT = "http://grid.ldc.upenn.edu:8081/lang_grid/services/SimpleUGetService";
   public static final String url = "http://grid.ldc.upenn.edu:8081/lang_grid/services/SimpleUGetService";
   public static final String username = "operator";
   public static final String password = "operator";

   public GigawordTest()
   {

   }

   @Test
   public void soapTest() throws ServiceException, RemoteException
   {
      SoapClient client = new SoapClient(NAMESPACE, ENDPOINT);
      client.setCredentials(username, password);
      Data[] args = new Data[] { DataFactory.list() };
      Object result = client.invoke("query", args);
      assertTrue(result != null);
      System.out.println("Result is a " + result.getClass().getName());
   }

   @Ignore
   public void testList() throws ServiceException
   {
      DataSource client = new org.lappsgrid.client.datasource.DataSourceClient(url, username, password);
      Data data = client.query(DataFactory.list());
      assertTrue(data != null);
      assertTrue(data.getDiscriminator() != Types.ERROR);

      String[] index = data.getPayload().split("\\s+");
      assertTrue(index.length > 1);
   }


   @Ignore
   public void testGet() throws ServiceException
   {
      DataSource client = new org.lappsgrid.client.datasource.DataSourceClient(url, username, password);
      Data data = client.query(DataFactory.list());
      assertTrue(data != null);
      assertTrue(data.getDiscriminator() != Types.ERROR);

      String[] index = data.getPayload().split("\\s+");
      assertTrue(index.length > 1);
      data = client.query(DataFactory.get(index[0]));
      assertTrue(data != null);
      assertTrue(data.getDiscriminator() != Types.ERROR);
      System.out.println(data.getPayload());
   }
}
