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

public class EmployeeGUI implements Initializable{
	@FXML
	private Label name;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	   try {
		name.setText(ClientUI.getMyInstance().getMyClient().getUser().getUsername());
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}
public void start() throws IOException
{
	Stage stage = new Stage();
	URL url = getClass().getResource("Employee.fxml");
	AnchorPane pane = FXMLLoader.load(url);
	Scene scene = new Scene(pane,600,600);
	stage.setScene(scene);
	stage.setTitle("employee");
	stage.show();

}

@FXML
public void clickOnBooking() throws IOException, Exception{
	
 ClientUI.getMyInstance().getOrderGUI().start();
	OrderController.getProductsFromServer(ClientUI.getMyInstance().getMyClient().getUser());
	ClientUI.getOrderGUI().start();

}
@FXML
public void clickOnComplaints()
{
	
}
@FXML
public void clickOnUpdateCatlog() throws IOException
{
	Message message = new Message();
	message.setWhatToDo(MyConstants.GET_PRODUCTS_IN_CATLOG);
	ClientUI.getMyClient().sendToServer(message);
	}
@FXML
public void clickOnSurevy() throws IOException
{
	Message message= new Message();
	message.setWhatToDo(MyConstants.GET_ALL_CLIENT_DATA);
		ClientUI.getMyClient().sendToServer(message);
}
}