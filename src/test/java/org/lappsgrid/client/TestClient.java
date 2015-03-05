package org.lappsgrid.client;

import javax.xml.rpc.ServiceException;

/**
 * @author Keith Suderman
 */
public class TestClient extends AbstractSoapClient
{
	public TestClient() throws ServiceException
	{
//		super("http://anc.org", "http://localhost:9080/GateConverter/1.0.0-SNAPSHOT/services/GateToJson");
		super("http://localhost:9080/GateConverter/1.0.0-SNAPSHOT/services/GateToJson", "http://localhost:9080/GateConverter/1.0.0-SNAPSHOT/services/GateToJson");
	}
}
