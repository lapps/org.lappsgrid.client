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

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.lappsgrid.api.WebService;

import javax.xml.rpc.ServiceException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
//import static org.lappsgrid.discriminator.Helpers.type;

/**
 * @author Keith Suderman
 */
@Ignore
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

   /*
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
      Data result = service.configure(null);
      assertNotNull(result);
      assertTrue(type(result) == Types.TEXT);
   }

   @Test
   public void testExecute()
   {
      Data result = service.execute(null);
      assertNotNull(result);
      assertTrue(type(result) == Types.TEXT);
   }
   */
}
