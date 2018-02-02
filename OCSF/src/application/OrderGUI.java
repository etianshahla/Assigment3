package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clinetcontrollers.OrderController;
import common.Constants.MyConstants;
import common.Entities.OrdersView;
import common.OurMessage.Message;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myClient.MyClient;

public class OrderGUI extends Application implements Initializable {
	public static OrderGUI myInstance;
	
	@FXML	private TableView<OrdersView> tableView;
	   @FXML private TableColumn<OrdersView, String> id;
	    @FXML private TableColumn<OrdersView, String> price;

	
	List<OrdersView> orders = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        try {
			this.orders = ClientUI.getMyInstance().getMyClient().orders;
		} catch (IOException e) {
			e.printStackTrace();
		}
        id.setCellValueFactory(new PropertyValueFactory<OrdersView, String>("id"));
        price.setCellValueFactory(new PropertyValueFactory<OrdersView, String>("price"));
        tableView.getItems().setAll(orders);
	}

	public void start() throws Exception {	
		Stage stage = new Stage();
		URL url = getClass().getResource("order.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane,600,600);
		stage.setScene(scene);
		stage.setTitle("Order");
		stage.show();


	}

	public static OrderGUI getInstance() {
		if (myInstance == null)
			myInstance = new OrderGUI();
		return myInstance;
		
	}
	public List<OrdersView> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersView> orders) {
		this.orders = orders;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void addButtonClick() throws Exception
	{
			AddOrderGUI addOrderGUI = new AddOrderGUI();
			addOrderGUI.start();
	}
}
