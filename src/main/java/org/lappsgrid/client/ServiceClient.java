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

import org.lappsgrid.api.ProcessingService;

import javax.xml.rpc.ServiceException;

/**
 * The ServiceClient does not add any functionality, it is simply provides
 * a way to instantiate the AbstractClient.
 *
 * @author Keith Suderman
 */
public class ServiceClient extends AbstractClient implements ProcessingService
{
   public ServiceClient(String url) throws ServiceException
   {
      super(url);
   }

   public ServiceClient(String endpoint, String user, String password) throws ServiceException
   {
		super(endpoint, user, password);
	}

}
