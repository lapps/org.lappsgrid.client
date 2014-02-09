package org.lappsgrid.client;

import org.anc.soap.client.SoapClient;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.LappsException;
import org.lappsgrid.core.DataFactory;

import javax.xml.rpc.ServiceException;

import org.junit.*;
import org.lappsgrid.discriminator.Types;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
public class GigawordTest
{
//   private static final String NAMESPACE = "http://langrid.nict.go.jp/ws_1_2/";
//   private static final String ENDPOINT = "http://grid.ldc.upenn.edu:8080/doc_service/services/DocumentDataSource";
   public static final String url = "http://grid.ldc.upenn.edu:8080/doc_service/services/DocumentDataSource";

   public static final String username = "operator";
   public static final String password = "operator";

   public GigawordTest()
   {

   }

   @Test
   public void testList() throws ServiceException
   {
      DataSource client = new DataSourceClient(url, username, password);
      Data data = client.query(DataFactory.list());
      assertTrue(data != null);
      assertTrue(data.getDiscriminator() != Types.ERROR);

      String[] index = data.getPayload().split("\\s+");
      assertTrue(index.length > 1);
      System.out.println("Index size is " + index.length);
   }


   @Test
   public void testGet() throws ServiceException
   {
      DataSource client = new DataSourceClient(url, username, password);
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

   @Test
   public void testGetAll() throws ServiceException, LappsException
   {
      DataSourceClient client = new DataSourceClient(url, username, password);
      String[] index = client.list();
      for (String key : index)
      {
         Data data = client.get(key);
         assertTrue(data.getDiscriminator() != Types.ERROR);
      }
      System.out.println("Fetched all data from example Gigaword service.");
   }
}

