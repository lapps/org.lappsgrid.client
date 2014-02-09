package org.lappsgrid.client;

import org.junit.*;
import static org.junit.Assert.*;

import org.lappsgrid.api.Data;
import org.lappsgrid.api.LappsException;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.DiscriminatorRegistry;
import org.lappsgrid.discriminator.Types;

import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class DataSourceClientTest
{
   public static final String ROOT_URL = "http://grid.anc.org:8080/service_manager/invoker";
   public static final String DATASOURCE_URL = ROOT_URL + "/anc:test.datasource_1.0.0";

   public static final String USER = "temporary";
   public static final String PASS = "temporary";

   public DataSourceClientTest()
   {

   }

   @Test
   public void testQuery() throws ServiceException
   {
      DataSourceClient client = new DataSourceClient(DATASOURCE_URL, USER, PASS);
      Data get = DataFactory.get("foo");
      Data result = client.query(get);
      assertTrue(result.getDiscriminator() == Types.OK);

      Data list = DataFactory.list();
      result = client.query(list);
      assertTrue(result.getDiscriminator() == Types.OK);
   }

   @Test
   public void testGet() throws ServiceException
   {
      DataSourceClient client = new DataSourceClient(DATASOURCE_URL, USER, PASS);
      Data result = client.get("foo");
      assertTrue(result.getDiscriminator() == Types.OK);
   }

   // This test will always fails as the TestDataSource service's query method
   // always just returns a Data object with the discriminator set to OK and a
   // null payload.  This causes an NPE when the client attempts to
   @Ignore
   public void testList() throws ServiceException, LappsException
   {
      DataSourceClient client = new DataSourceClient(DATASOURCE_URL, USER, PASS);
      String[] index = client.list();
      assertTrue(index != null);
      System.out.println("Index size is " + index.length);
   }
}
