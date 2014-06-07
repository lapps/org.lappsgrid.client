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

import org.lappsgrid.api.*;
import org.lappsgrid.core.ViewOptions;

/**
 * Represents a ranked list of documents with annotations.
 * 
 * The implementation of this interface may store document id (and/or spans)
 * itself and/or caching with {@link DataSourceCachingStrategy}.
 * 
 * @author Di Wang
 * @author Keith Suderman
 */
public interface ExtendedDataSource extends DataSource
{
   /**
    * Gets a subset of corpus selected by input query.
    * 
    * @param query
    *           the input query
    * @return a subset of annotated corpus
    */
   public ExtendedDataSource subDataSource(Data query);

   /**
    * Gets the document stream reader of current corpus.
    * 
    * @param options
    *           the options that specify how to present each document such as
    *           returning "doc id", "doc content", "annotation", "annotation
    *           spans", etc.
    * @return the document stream reader
    */
   public DataSourceIterator iterator(ViewOptions options);

   /**
    * Gets one document by documentID.
    * 
    * @param options
    *           the options that specify how to present each document such as
    *           returning "doc id", "doc content", "annotation", "annotation
    *           spans", etc.
    * @param documentID
    *           the document id
    * @return the document
    */
   public Data get(ViewOptions options, String documentID);

}
