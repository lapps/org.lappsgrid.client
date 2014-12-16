package org.lappsgrid.client;

import org.junit.Ignore;
import org.junit.Test;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.LappsException;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.discriminator.DiscriminatorRegistry;
import org.lappsgrid.discriminator.Types;
import org.lappsgrid.discriminator.Uri;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.assertTrue;

/**
 * @author Keith Suderman
 */
@Ignore
public class DataSourceClientTest
{
   public static final String ROOT_URL = "http://grid.anc.org:8080/service_manager/invoker";
   public static final String DATASOURCE_URL = ROOT_URL + "/anc:test.datasource_1.0.0";

   public static final String USER = "temporary";
   public static final String PASS = "temporary";

   public DataSourceClientTest()
   {

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
