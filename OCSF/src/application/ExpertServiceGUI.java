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

public class ExpertServiceGUI  implements Initializable{
@FXML
private Label name,id;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
try {
	name.setText(ClientUI.getMyInstance().getMyClient().getUser().getUsername());
	id.setText(String.valueOf(ClientUI.getMyInstance().getMyClient().getUser().getId()));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}		
	}
public void start() throws IOException
{
	Stage stage = new Stage();
	URL url = getClass().getResource("ExpertService.fxml");
	AnchorPane pane = FXMLLoader.load(url);
	Scene scene = new Scene(pane, 600, 600);
	stage.setScene(scene);
	stage.setTitle("shop manger");
	stage.show();
}
@FXML
public void clickOnBooking() throws Exception
{
	 OrderController.getProductsFromServer(ClientUI.getMyClient().getUser());
		ClientUI.getOrderGUI().start();
}

@FXML
public void clickOnSurveys() throws IOException
{
	Message message = new Message() ;
	message.setWhatToDo(MyConstants.GET_SURVEY_RESULTS);
	ClientUI.getMyInstance().getMyClient().sendToServer(message);
}
}
