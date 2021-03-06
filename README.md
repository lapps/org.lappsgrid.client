# org.lappsgrid.client

The `clients` module provides SOAP clients for accessing LAPPS web services.

### Build Status

[![Master Status](http://grid.anc.org:9080/travis/svg/lapps/org.lappsgrid.client?branch=master)](https://travis-ci.org/lapps/org.lappsgrid.client)
[![Develop Status](http://grid.anc.org:9080/travis/svg/lapps/org.lappsgrid.client?branch=develop)](https://travis-ci.org/lapps/org.lappsgrid.client)

### Maven

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.lappsgrid/client/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/org.lappsgrid/client)


```xml
<dependency>
    <groupId>org.lappsgrid</groupId>
    <artifactId>client</artifactId>
    <version>${see.above}</version>
</dependency>
```

## DataSourceClient

The `org.lappsgrid.client.DataSourceClient` class provides several helper methods
that parse the JSON returned by LAPPS web services into useful Java objects. To access
a DataSourceClient you will need the URL of the service as well as the username and
password for the service grid.


```
int size();
List<String> list();
List<String> list(int start, int end);
String get(String key);
void setToken(String token);
```

For example:
```
DataSourceClient client = new DataSourceClient(url, username, password);
client.setToken(my_oauth_token);
List<String> keys = client.list();
String text = client.get(keys.getAt(0)); 
```

