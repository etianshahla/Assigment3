package myClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import application.OrderGUI;
import application.ServiceGUI;
import application.ShopMangerGUI;
import application.SurveyGUI;
import application.SystemAdministratorGUI;
import application.UpdateCatlogGUI;
import application.UpdateCustomerInformationGUI;
import clinetcontrollers.OrderController;
import application.ClientUI;
import application.EmployeeGUI;
import application.ExpertServiceGUI;
import application.ItemsGUI;
import common.OurMessage.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import common.*;
import common.Constants.MyConstants;
import common.Entities.Client;
import common.Entities.Item;
import common.Entities.OrdersView;
import common.Entities.SurveyResults;
import common.Entities.User;
import ocsf.client.AbstractClient;

public class MyClient extends AbstractClient {
	public List<OrdersView> orders = new ArrayList<>();
	public static List<Item> items = new ArrayList<>();
	public List<Client> clients = new ArrayList<>();
public static 	SurveyResults surveyResults= new SurveyResults();

	Message message = new Message();
	/**
	 * clientUI - parameter of the client GUI.
	 */

	/**
	 * @param clientUI
	 * @throws IOException
	 */
	ClientUI clientUI;
	private User user = new User();

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
		try {
			clientUI.getMyInstance().setMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (message.getWhatToDo()) {
		case (MyConstants.LOGIN_SUCCESS): {
			array = (ArrayList<Object>) message.getData();
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof User)
					user = (User) array.get(i);
				break;
			}

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						if (user.getUserType().equals("client")) {
							ClientUI.getMyInstance().getLoginGUI().getStage().hide();
							OrderController.getProductsFromServer(user);
							ClientUI.getOrderGUI().start();

						}
						if (user.getUserType().equals("Administrator")) {
							SystemAdministratorGUI systemAdministratorGUI = new SystemAdministratorGUI();
							systemAdministratorGUI.start();
						}
						if (user.getUserType().equals("employee")) {
							EmployeeGUI employeeGUI = new EmployeeGUI();
							employeeGUI.start();
						}
						if (user.getUserType().equals("shop manger")) {
							ShopMangerGUI shopMangerGUI = new ShopMangerGUI();
							shopMangerGUI.start();
						}
						if (user.getUserType().equals("expert service")) {
							ExpertServiceGUI expertServiceGUI = new ExpertServiceGUI();
							expertServiceGUI.start();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;

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
						
						for (int i = 0; i < message.getData().size(); i++) {

							if (message.getData().get(i) instanceof List<?>) {
								items = (List<Item>) message.getData().get(i);
								break;
							}
						}
						if (user.getUserType().equals("client")) {
							ClientUI.getCatlogGUI().start();
						}
						if (user.getUserType().equals("employee")) {
							UpdateCatlogGUI updateCatlogGUI = new UpdateCatlogGUI();
							updateCatlogGUI.start();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.GET_PRODUCTS_SELF_SELECTION: {
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

						ClientUI.getMyInstance().getItemsGUI().start();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.GET_ALL_CLIENT_DATA: {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("messege size" + message.getData().size());
						for (int i = 0; i < message.getData().size(); i++) {
							if (message.getData().get(i) instanceof List<?>) {
								System.out.println("hiiiiii");
								clients = (List<Client>) message.getData().get(i);
								System.out.println("client size  " + clients.size());
								break;
							}
						}
						if (user.getUserType().equals("employee"))
						{
							SurveyGUI surveyGUI = new SurveyGUI();
							surveyGUI.start();
						}
						else if (user.getUserType().equals("Administrator")) {
						UpdateCustomerInformationGUI updateCustomerInformationGUI = new UpdateCustomerInformationGUI();
						updateCustomerInformationGUI.start();
						}
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
		case MyConstants.UPDATE_CLIENT: {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("user updated");
						alert.show();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.CLIENT_EXIST: {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("the order was sold and sent to the client ");
						alert.show();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.ADD_ITEM_TO_CATLOG_SUCCESS: {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("the item was add to the catlog  ");
						alert.show();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.UPDATE_ITEM_IN_CATLOG_SUCCESS: {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("the item was updated to the catlog  ");
						alert.show();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.ADD_CLIENT_SURVEY_SUCCESS: {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("the survey was added ");
						alert.show();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.ADD_CLIENT_SUCCESS: {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("the client was add successfully was added ");
						alert.show();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.GET_SURVEY_RESULTS: {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("survey"+ message.getData().size());
					    for (int i = 0; i < message.getData().size(); i++) {
							if (message.getData().get(i) instanceof SurveyResults) {
							surveyResults = (SurveyResults) message.getData().get(i);
							break;
							}
					    }
						ServiceGUI serviceGUI = new ServiceGUI();
						serviceGUI.start();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			break;
		}
		case MyConstants.SAVE_SURVEY_RESULT_SUCCESS: {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message Here...");
						alert.setContentText("the save of the survey conclusion success");
						alert.show();

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
