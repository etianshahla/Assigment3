package application;

import java.io.IOException;

import common.OurMessage.Message;
import javafx.event.ActionEvent;
import myClient.MyClient;

public class ClientUI {
	private static ClientUI clientUI = null;
	Message message = new Message();
private static CatlogGUI catlogGUI ;
	private static MyClient myClient;
	private static ItemsGUI itemsGUI;
	private ActionEvent event;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public static LoginGUI getLoginGUI() {
		return LoginGUI.getInstance();
	}
 
	public static OrderGUI getOrderGUI() {
		return OrderGUI.getInstance();
	}

	public static MyClient getMyClient() {
		return myClient;
	}


	public ClientUI() throws IOException {

	
		myClient = new MyClient("localhost", 5555);

	}

	public static ClientUI getMyInstance() throws IOException {
	if (clientUI ==null) {
		clientUI = new ClientUI();
	}
		return clientUI;
	}
	public ActionEvent getEvent() {
		return event;
		
	}

	public void setEvent(ActionEvent event) {
		this.event = event;
	}
public static CatlogGUI getCatlogGUI()
{
	if (catlogGUI == null) {
		catlogGUI = new CatlogGUI();
	}
	return catlogGUI;
}
public static ItemsGUI getItemsGUI()
{
	if (itemsGUI == null) {
		itemsGUI = new ItemsGUI();
	}
	return itemsGUI;
}

public static UpdateCustomerInformationGUI getpdateClients()
{
return new UpdateCustomerInformationGUI();
}
}
