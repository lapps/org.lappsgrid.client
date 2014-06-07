/*-
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

import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.NoSuchDataSourceException;
import org.lappsgrid.core.ConnectionOptions;

/**
 * Basic facility to manage {@link DataSource}. 
 *
 * @author Di Wang
 */
public interface ConnectionManager {

  /**
   * Connect to a remote corpus
   *
   * @param connOptions the options that specify how to establish a connection to remote corpus. 
   * @return the annotated corpus
   * @throws NoSuchDataSourceException the no such corpus exception
   */
  public DataSource connect(ConnectionOptions connOptions) throws NoSuchDataSourceException;

}
