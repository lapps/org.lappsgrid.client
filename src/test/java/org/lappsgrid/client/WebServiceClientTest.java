package org.lappsgrid.client;

import org.junit.*;
import org.lappsgrid.api.Data;
import org.lappsgrid.api.WebService;
import org.lappsgrid.discriminator.Types;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.*;

/**
 * @author Keith Suderman
 */
public class WebServiceClientTest
{
   public static final String ROOT_URL = "http://grid.anc.org:8080/service_manager/invoker";
   public static final String WEBSERVICE_URL = ROOT_URL + "/anc:test.webservice_1.0.0";
   public static final String USER = "temporary";
   public static final String PASS = "temporary";


   protected WebService service;

   public WebServiceClientTest()
   {

   }

   @Before
   public void setup() throws ServiceException
   {
      service = new ServiceClient(WEBSERVICE_URL, USER, PASS);
   }

   @After
   public void tearDown()
   {
      service = null;
   }

   @Test
   public void testRequires()
   {
      long[] requires = service.requires();
      assertNotNull(requires);
      assertTrue(requires.length == 1);
      assertTrue(requires[0] == Types.TEXT);
   }

   @Test
   public void testProduces()
   {
      long[] produces = service.produces();
      assertNotNull(produces);
      assertTrue(produces.length == 1);
      assertTrue(produces[0] == Types.TEXT);
   }

   @Test
   public void testConfigure()
   {
      Data result = service.configure(null);
      assertNotNull(result);
      assertTrue(result.getDiscriminator() == Types.TEXT);
   }

   @Test
   public void testExecute()
   {
      Data result = service.execute(null);
      assertNotNull(result);
      assertTrue(result.getDiscriminator() == Types.TEXT);
   }
}
