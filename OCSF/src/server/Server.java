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
import server.controllers.ServerOrderController;
import server.controllers.serverCatlogController;
import server.controllers.serverClientsController;

public class Server extends AbstractServer {
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	public Connection connDB;
	private Message messageForClient = new Message();
	private ServerUI serverUI;
static 	User user = new User();

	public Server(ServerUI serverui) {
		super();
		this.serverUI = serverui;
		serverUI.setVisible(true);
	}

	public void initDBConnection(String dbName, String user, String pass) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connDB = DriverManager.getConnection("jdbc:mysql://localhost:3307/" + dbName, user, pass);
			System.out.println("SQL connection succeed");
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client)// comes from "sendToServer" in myClient
	{
		try {
			ArrayList<Object> array = new ArrayList<>();
			array = (ArrayList<Object>) ((Message) msg).getData();
			System.out.println("array size"+ array.size());
				Message message = new Message(((Message) msg).getWhatToDo(), array);
		message.setData(array);
				switch (message.getWhatToDo()) {
			case (MyConstants.CHECK_USER_EXIST): {

				for (int i = 0; i < array.size(); i++) {
					if (array.get(i) instanceof User) {
						user = (User) array.get(i);
						break;
					}
				}
				ServerLoginController.checkIfUserExist(connDB, client, user);
				break;
			}

			case (MyConstants.GET_PRODUCTS): {
				
				for (int i = 0; i < array.size(); i++) {
					if (array.get(i) instanceof User) {
						user = (User) array.get(i);
						break;
					}
				}
				ServerOrderController.getAllProducts(connDB, client, user);
				break;
			}

			case (MyConstants.GET_PRODUCTS_IN_CATLOG): {
				serverCatlogController.getAllProductsInCatlog(connDB,client);
			break;
			}
			case MyConstants.ADD_ITEM_TO_ORDER_FROM_CATLOG:
			{    System.out.println(message.getData().size());
				serverCatlogController.addItemToOrder(connDB,client,message,array);
				break;
			}
			case MyConstants.GET_ALL_CLIENT_DATA:
			{   
				serverClientsController.getAllClients(connDB,client);
				break;
			}
			case MyConstants.UPDATE_CLIENT:
			{   
				serverClientsController.updateClient(connDB,client,message,array);
				break;
			}
			case MyConstants.GET_PRODUCTS_SELF_SELECTION:
			{
				serverCatlogController.getAllProducts(connDB,client);
				break;
			}
			case MyConstants.CHECK_CLIENT_EXIST:
			{
				serverClientsController.checkIfClinetExist(connDB,client,array);
				break;
			}
			case MyConstants.ADD_ITEM_TO_CATLOG:
			{		serverCatlogController.addItemToCatlog(connDB,client,array);
			break;
			}
				case MyConstants.UPDATE_ITEM_TO_CATLOG:
				{
					serverCatlogController.updateItemToCatlog(connDB,client,array);
					break;
				}
				case MyConstants.ADD_CLIENT_SURVEY:
				{
					serverClientsController.addClientSurvey(connDB,client,array);
					break;
				}
				case MyConstants.ADD_CLIENT:
				{
					serverClientsController.addClient(connDB,client,array);
					break;
				}
				case MyConstants.GET_SURVEY_RESULTS:
				{
				serverClientsController.getSurveyResults(connDB,client);
				break;
				}
				case MyConstants.SAVE_SURVEY_RESULT:
				{
				serverClientsController.saveSurveyResults(connDB,client,array);
				break;
				}
				}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}