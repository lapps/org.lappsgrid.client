package org.lappsgrid.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lappsgrid.api.WebService;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;

import javax.xml.rpc.ServiceException;

import java.util.Map;

import static org.junit.Assert.*;
import static org.lappsgrid.discriminator.Discriminators.Uri;

/**
 * @author Keith Suderman
 */
//@Ignore
public class WebServiceClientTest
{
   public static final String ROOT_URL = "http://grid.anc.org:8080/service_manager/invoker";
//   public static final String WEBSERVICE_URL = ROOT_URL + "/anc:test.webservice_1.0.0";
   public static final String WEBSERVICE_URL = ROOT_URL + "/anc:gate.tokenizer_2.0.0";
   public static final String USER = "tester";
   public static final String PASS = "tester";


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
   public void testConfigure()
   {
      String json = service.getMetadata();
      assertNotNull(json);
      Data data = Serializer.parse(json, Data.class);
      assertFalse(data.getPayload().toString(), Uri.ERROR.equals(data.getDiscriminator()));
      assertTrue("Invalid discriminator " + data.getDiscriminator(), Uri.META.equals(data.getDiscriminator()));
   }

/*
   @Test
   public void testExecute()
   {
      Data result = service.execute(null);
      assertNotNull(result);
      assertTrue(type(result) == Types.TEXT);
   }
   */
}
