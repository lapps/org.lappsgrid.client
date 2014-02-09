package org.lappsgrid.client;

/**
 * A class to hold information about a particular server on the LAPPS grid.
 *
 * @author Keith Suderman
 */
public class Server
{
   private String url;
   private String user;
   private String password;

   public Server(String url, String user, String password)
   {
      this.url = url;
      this.user = user;
      this.password = password;
   }

   public String getUrl()
   {
      return url;
   }

   public String getUser()
   {
      return user;
   }

   public String getPassword()
   {
      return password;
   }
}
