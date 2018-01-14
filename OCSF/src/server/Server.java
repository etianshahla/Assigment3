package server;

import java.awt.Window;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import common.Constants.MyConstants;
import common.Entities.User;
import common.OurMessage.Message;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import server.controllers.ServerLoginController;



public class Server extends AbstractServer
{
	  /**
	   * The default port to listen on.
	   */
	  final public static int DEFAULT_PORT = 5555;
	  public Connection connDB;
	  private Message messageForClient = new Message();
	private ServerUI serverUI;
	  
	  public Server(ServerUI serverui)
	  {
		  super();
		  this.serverUI = serverui;
          serverUI.setVisible(true);	
	  }
	  
	  public void initDBConnection(String dbName,String user, String pass) throws Exception
	  {
		  try 
		  {
			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  connDB = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, user, pass);
			  System.out.println("SQL connection succeed");
		  }
		  catch(Exception ex)
		  {
			  throw ex;
		  }
	  }
	  
	  public void handleMessageFromClient(Object msg, ConnectionToClient client)//comes from "sendToServer" in myClient
	  {
		  try
		  {
			  ArrayList<Object> array = new ArrayList<>();
			  array = (ArrayList<Object>) ((Message)msg).getData();
			  Message message = new Message(((Message)msg).getWhatToDo(), array);

			  switch(message.getWhatToDo())
			  {
			  		case(MyConstants.CHECK_USER_EXIST):
			  			User user = new User();
			  			for(int i=0 ; i<array.size();i++)
			  			{
			  				if(array.get(i) instanceof User)
			  				{
			  					user = (User) array.get(i);
			  					break;
			  				}
			  			}
			  			ServerLoginController.checkIfUserExist(connDB, client, user);
			  			break;
			  			
			  		
			  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }


}