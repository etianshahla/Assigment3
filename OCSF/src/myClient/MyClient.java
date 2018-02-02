package myClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import application.OrderGUI;
import clinetcontrollers.OrderController;
import application.ClientUI;
import common.OurMessage.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import common.*;
import common.Constants.MyConstants;
import common.Entities.Item;
import common.Entities.OrdersView;
import common.Entities.User;
import ocsf.client.AbstractClient;

public class MyClient extends AbstractClient {
	public List<OrdersView> orders;
	public List<Item> items;
	Message message = new Message();
	/**
	 * clientUI - parameter of the client GUI.
	 */

	/**
	 * @param clientUI
	 * @throws IOException
	 */
	ClientUI clientUI;
	User user = new User();

	public MyClient() throws IOException {
		super(); // Call the superclass constructor

	}

	/**
	 * @param host
	 * @param port
	 * @param clientUI
	 * @throws IOException
	 */
	public MyClient(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor

		try {
			openConnection();
		} catch (IOException e) {
			System.out.println("Connection to server failed.");
		}
	}

	public void handleMessageFromServer(Object msg) {

		message = (Message) msg;
		ArrayList<Object> array = new ArrayList<>();
		String role = null;

		try {
			clientUI.getMyInstance().setMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (message.getWhatToDo()) {
		case (MyConstants.LOGIN_SUCCESS): {
			array = (ArrayList<Object>) message.getData();
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof User)
					user = (User) array.get(i);
			}
			try {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							if (user.getUserType().equals("client")) {

								ClientUI.getMyInstance().getLoginGUI().getStage().hide();
								OrderController.getProductsFromServer(user);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		case MyConstants.GET_PRODUCTS: {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(message.getData().size());
						for (int i = 0; i < message.getData().size(); i++) {

							if (message.getData().get(i) instanceof List<?>) {
								orders = (List<OrdersView>) message.getData().get(i);
								break;
							}
						}

						ClientUI.getOrderGUI().start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			break;
		}
		case MyConstants.GET_PRODUCTS_IN_CATLOG: {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(message.getData().size());
						for (int i = 0; i < message.getData().size(); i++) {

							if (message.getData().get(i) instanceof List<?>) {
								items = (List<Item>) message.getData().get(i);
								break;
							}
						}

						ClientUI.getCatlogGUI().start();
					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
break;
		}
		case MyConstants.ADD_ITEM_TO_ORDER_FROM_CATLOG: {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						OrderController.getProductsFromServer(user);
					ClientUI.getOrderGUI().start();
					
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
break;
		}

		}

	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void handleMessageFromClientUI(Message msg) throws IOException // comes from Controller
	{
		try {
			sendToServer(msg); // goes to server
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not send message to server.");
			throw e;
			// quit();
		}
	}

	public void handleMessageFromGuiRegistration(Message msg) throws IOException {
		try {
			sendToServer(msg);
		} catch (IOException e) {
			System.out.println("Could not send message to server.");
			throw e;
			// quit();
		}
	}

	public User getUser() {
		return user;
	}
}
