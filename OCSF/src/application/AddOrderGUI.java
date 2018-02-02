package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Constants.MyConstants;
import common.OurMessage.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddOrderGUI implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void start() throws Exception {
		Stage stage = new Stage();
		URL url = getClass().getResource("addOrder.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("AddOrder");
		stage.show();

	}

	@FXML
	public void openCatlog() throws IOException {
		Message message = new Message();
		message.setWhatToDo(MyConstants.GET_PRODUCTS_IN_CATLOG);
		ClientUI.getMyClient().sendToServer(message);
		
		CatlogGUI catlogGUI = new CatlogGUI();
		catlogGUI.start();

	}
	
	@FXML
	public void openItems() throws IOException
	{
		ItemsGUI itemsGUI = new ItemsGUI();
		itemsGUI.start();
	}
	
}
