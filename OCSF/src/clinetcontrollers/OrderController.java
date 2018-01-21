package clinetcontrollers;

import java.io.IOException;
import java.util.List;

import application.ClientUI;
import common.Constants.MyConstants;
import common.OurMessage.Message;

public class OrderController {

	public static void getProductsFromServer()
	{
		Message messageToServer = new Message();
		messageToServer.setWhatToDo(MyConstants.GET_PRODUCTS);
	   System.out.println(messageToServer.getWhatToDo());
		
		/**
		 *  send the user to server in order to check if exists
		 */
			////// send the user to server in order to check if exists ////////////////////////////// 
		try
		{ClientUI.getMyInstance().getMyClient().openConnection();
			ClientUI.getMyInstance().getMyClient().handleMessageFromClientUI(messageToServer);
		}
		catch(IOException e)
		{	
			e.printStackTrace();
		}

	}
}
