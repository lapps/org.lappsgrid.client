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
public interface DataSourceClient extends DataSource
{
   /**
    * Gets a subset of corpus selected by input query.
    * 
    * @param query
    *           the input query
    * @return a subset of annotated corpus
    */
   public DataSourceClient subDataSource(Data query);

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
