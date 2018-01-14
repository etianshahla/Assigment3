package myClient;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import common.OurMessage.*;
import common.*;
import common.Constants.MyConstants;
import common.Entities.User;
import ocsf.client.AbstractClient;

public class MyClient extends AbstractClient {
	/**
	 * clientUI - parameter of the client GUI.
	 */
	
	/**
	 * @param clientUI
	 * @throws IOException
	 */
	public MyClient() throws IOException  
	{
		 super(); //Call the superclass constructor

	}
	
	/**
	 * @param host
	 * @param port
	 * @param clientUI
	 * @throws IOException
	 */
	public MyClient(String host,int port) throws IOException  
	{
		 super(host, port); //Call the superclass constructor
	
		 try
		 {
			 openConnection();
		 }
		 catch(IOException e)
		 {
			 System.out.println("Connection to server failed.");
		 }
	}
	
	public void handleMessageFromServer(Object msg) 
	{
		Message message = new Message();
		message = (Message)msg;
		ArrayList<Object> array = new ArrayList<>();
		String role = null;
		User user = new User();
		switch(message.getWhatToDo())
		{
			case(MyConstants.LOGIN_SUCCESS):
				array = (ArrayList<Object>) message.getData();
				for(int i=0 ; i<array.size();i++)
				{
					if(array.get(i) instanceof User)
						user = (User) array.get(i);
				}
		}
	
	}
	
	public void quit()
	{
	  try
	  {
	     closeConnection();
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
	  System.exit(0);
	}
	
	public void handleMessageFromClientUI(Message msg) throws IOException //comes from Controller
	{
		try
	    {
			sendToServer(msg); // goes to server
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	      System.out.println("Could not send message to server.");
	      throw e;
	     // quit();
	    }
	}
	
	public void handleMessageFromGuiRegistration(Message msg) throws IOException
	{
		try
	    {
	    	sendToServer(msg);
	    }
	    catch(IOException e)
	    {
	      System.out.println("Could not send message to server.");
	      throw e;
	     // quit();
	    }
	}
}
