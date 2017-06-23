# org.lappsgrid.client

The `clients` module provides SOAP clients for accessing LAPPS web services.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.lappsgrid/client/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/org.lappsgrid/client)

### Maven Coordinates

```xml
<dependency>
    <groupId>org.lappsgrid</groupId>
    <artifactId>client</artifactId>
    <version>2.0.4</version>
</dependency>
```

## DataSourceClient

The `org.lappsgrid.client.DataSourceClient` class provides several helper methods
that parse the JSON returned by LAPPS web services into useful Java objects. To access
a DataSourceClient you will need the URL of the service as well as the username and
password for the service grid.


```java
int size();
List<String> list();
List<String> list(int start, int end);
String get(String key);
void setToken(String token);
```

For example:
```java
DataSourceClient client = new DataSourceClient(url, username, password);
client.setToken(my_oauth_token);
List<String> keys = client.list();
String text = client.get(keys.getAt(0)); 
```

