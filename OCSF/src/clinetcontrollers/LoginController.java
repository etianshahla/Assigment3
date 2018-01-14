package clinetcontrollers;

import java.io.IOException;

import application.LoginGUI;
import common.Constants.MyConstants;
import common.Entities.User;
import common.OurMessage.Message;


public class LoginController {
	
	/**
	 * A method that checks if a user exists in dataBase
	 * @param requestedUserID - the used id
	 * @param requestedPassword - the user password
	 * message : a message to send to server 
	 */
	public void handleUserRequestToLogin(String requestedUserID, String requestedPassword)
	{
		Message messageToServer = new Message();
		/**
		 *  Take the string 'message' and turn it to a User object 
		 */
			////// Take the string 'message' and turn it to a User object  /////////////////////////// 
		User userToLogin = new User();
		
		userToLogin.setUsername(requestedUserID); 
		userToLogin.setPassword(requestedPassword);
			
		messageToServer.setWhatToDo(MyConstants.CHECK_USER_EXIST);
		messageToServer.setData(userToLogin);
		
		/**
		 *  send the user to server in order to check if exists
		 */
			////// send the user to server in order to check if exists ////////////////////////////// 
		try
		{
			LoginGUI.clinet.handleMessageFromClientUI(messageToServer);
		}
		catch(IOException e)
		{	
			e.printStackTrace();
		}
	}
	
	public void handleUserRequestToSignOut(String idToSignOut)
	{
		Message messageToServer = new Message();
			
		messageToServer.setWhatToDo(MyConstants.SIGN_OUT);
		messageToServer.setData(idToSignOut);
		
		try
		{
			LoginGUI.clinet.handleMessageFromClientUI(messageToServer);
		}
		catch(IOException e)
		{	
			e.printStackTrace();
		}
	}

}
