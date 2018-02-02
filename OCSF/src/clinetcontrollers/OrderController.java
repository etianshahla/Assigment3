package clinetcontrollers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.ClientUI;
import common.Constants.MyConstants;
import common.Entities.User;
import common.OurMessage.Message;

public class OrderController {

	public static void getProductsFromServer(User user)
	{    System.out.println(user.getUsername());
		Message messageToServer = new Message();
		messageToServer.setWhatToDo(MyConstants.GET_PRODUCTS);
	messageToServer.setData(user);
		System.out.println(messageToServer.getData().size());
		
		try
		{	ClientUI.getMyClient().openConnection();
			ClientUI.getMyInstance().getMyClient().handleMessageFromClientUI(messageToServer);
		}
		catch(IOException e)
		{	
			e.printStackTrace();
		}

	}
}
