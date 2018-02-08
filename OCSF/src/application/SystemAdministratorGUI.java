package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clinetcontrollers.OrderController;
import common.Constants.MyConstants;
import common.OurMessage.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SystemAdministratorGUI implements Initializable {
	@FXML
	private Label name;
	@FXML
	private Label id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.id.setText(String.valueOf(ClientUI.getMyClient().getUser().getId()));
		this.name.setText(ClientUI.getMyClient().getUser().getUsername());
	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("systemAdministrator.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("System Administrator");
		stage.show();
	}

	@FXML
	public void openBooking() throws Exception {
		OrderController.getProductsFromServer(ClientUI.getMyClient().getUser());
		ClientUI.getOrderGUI().start();
	}

	@FXML
	public void openUpdateClient() throws IOException {
	Message message= new Message();
	message.setWhatToDo(MyConstants.GET_ALL_CLIENT_DATA);
		ClientUI.getMyClient().sendToServer(message);
		
	}
	@FXML
	public void openUpdatePermisssions() throws IOException
	{
		CancelOrUpdatePermissionsGUI cancelOrUpdatePermissionsGUI = new CancelOrUpdatePermissionsGUI();
		cancelOrUpdatePermissionsGUI.start();
	}
}
