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

import org.junit.Ignore;

/**
 * @author Keith Suderman
 */
@Ignore
public class GigawordTest
{
//   private static final String NAMESPACE = "http://langrid.nict.go.jp/ws_1_2/";
//   private static final String ENDPOINT = "http://grid.ldc.upenn.edu:8080/doc_service/services/DocumentDataSource";
   public static final String url = "http://grid.ldc.upenn.edu:8080/doc_service/services/DocumentDataSource";

   public static final String username = "operator";
   public static final String password = "operator";

   /*
   public GigawordTest()
   {

   }

   @Test
   public void testList() throws ServiceException
   {
      DataSource client = new DataSourceClient(url, username, password);
      Data data = client.query(DataFactory.list());
      assertTrue(data != null);
      assertTrue(type(data) != Types.ERROR);

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
      assertTrue(type(data) != Types.ERROR);

      String[] index = data.getPayload().split("\\s+");
      assertTrue(index.length > 1);
      data = client.query(DataFactory.get(index[0]));
      assertTrue(data != null);
      assertTrue(type(data) != Types.ERROR);
      System.out.println(data.getPayload());
   }

   @Test
   public void testGetAll() throws ServiceException, LappsException
   {
      DataSourceClient client = new DataSourceClient(url, username, password);
      Data data = client.list();
      assertTrue(type(data) == Types.ONE_PER_LINE);
      String[] index = data.getPayload().split("\n");
      for (String key : index)
      {
         data = client.get(key);
         assertTrue(type(data) != Types.ERROR);
      }
      System.out.println("Fetched all data from example Gigaword service.");
   }
   */
}

