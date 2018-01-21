package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import clinetcontrollers.OrderController;
import common.Constants.MyConstants;
import common.OurMessage.Message;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myClient.MyClient;

public class OrderGUI extends Application  implements Initializable {
public static OrderGUI myInstance;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void start(List<Object> data) throws Exception {
	
		List<String> orders = null;
		for (int i = 0; i < data.size(); i++) {
			 if (data.get(i) instanceof List<?>) {
				orders = (List<String>) data.get(i);
				break;
			}
		}
	
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("order.fxml"));
		Scene scene = new Scene(root,600,700);
		stage.setScene(scene);
		stage.setTitle("Order");
		Message message = new Message();
		
		stage.show();
	
	}
	
	public static OrderGUI getInstance() {
		if(myInstance == null)
			myInstance = new OrderGUI();
		return myInstance;
		}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
	}

}
