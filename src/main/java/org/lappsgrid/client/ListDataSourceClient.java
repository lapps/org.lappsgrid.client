package org.lappsgrid.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lappsgrid.api.Data;
import org.lappsgrid.api.DataSource;
import org.lappsgrid.api.DataSourceException;
import org.lappsgrid.api.DataSourceIterator;
import org.lappsgrid.core.DataFactory;
import org.lappsgrid.core.ViewOptions;
import org.lappsgrid.discriminator.Types;

public class ListDataSourceClient implements ExtendedDataSource
{
	/** The list of document ID values this DataSource will iterate over. */
	protected List<String> docIdList;

	/** The DataSource to retrieve Data from. */
	protected DataSource dataSource;

	public ListDataSourceClient() {

	}

	public ListDataSourceClient(DataSource dataSource)
			throws DataSourceException {
		this.dataSource = dataSource;
		this.docIdList = new ArrayList<String>();
		Data result = dataSource.query(DataFactory.list());
		if (result.getDiscriminator() == Types.ERROR) {
			String message = result.getPayload();
			if (message == null || message.trim().equals("")) {
				message = "Unable to get key list from data source.";
			}
			throw new DataSourceException(message);
		}
		String payload = result.getPayload();
		if (payload == null || payload.trim().length() == 0) {
			throw new DataSourceException(
					"Data source returned empty key list.");
		}
		String[] keys = payload.split("\\s+");
		for (String key : keys) {
			docIdList.add(key);
		}
	}

	public ListDataSourceClient(DataSource dataSource, List<String> docIdList) {
		this.dataSource = dataSource;

		// Make a defensive copy of any collections passed to us.
		this.docIdList = new ArrayList<String>(docIdList);
	}

	public ListDataSourceClient(DataSource dataSource, String[] docIdArray) {
		this.dataSource = dataSource;
		this.docIdList = new ArrayList<String>(Arrays.asList(docIdArray));
	}

	public Data get(int index) {
		if (index >= docIdList.size()) {
			return DataFactory.error("Index out of bounds");
		}
		String key = docIdList.get(index);
		return dataSource.query(DataFactory.get(key));
	}

	protected void setDocIdList(List<String> list) {
		this.docIdList = new ArrayList<String>(list);
	}

	protected void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Data query(Data query) {
		return dataSource.query(query);
	}
	
	@Override
	public ExtendedDataSource subDataSource(Data query) {
	    Data list = query(query);
	    String[] idArr = list.getPayload().split("\\s+");
	    List<String> idList = new ArrayList<String>();
	    for (String id : idArr) {
	      idList.add(id);
	    }
	    return new ListDataSourceClient(dataSource, idList);
	}

	@Override
	public DataSourceIterator iterator(ViewOptions options) {
		//TODO decide options format or drop it
		return new ListDataSourceIterator(dataSource, docIdList);
	}

	@Override
	public Data get(ViewOptions options, String documentID) {
		return dataSource.query(DataFactory.get(documentID));
	}
}
