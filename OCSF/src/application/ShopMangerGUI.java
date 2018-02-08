package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import clinetcontrollers.OrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShopMangerGUI implements Initializable {
	@FXML
	private Label name, id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			name.setText(ClientUI.getMyInstance().getMyClient().getUser().getUsername());
			id.setText(String.valueOf(ClientUI.getMyInstance().getMyClient().getUser().getId()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("ShopManager.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("shop manger");
		stage.show();
	}
  @FXML
	public void clickOnOpenAccount() throws IOException {
		OpenClientAccountGUI accountGUI = new OpenClientAccountGUI();
		accountGUI.start();
	}
  @FXML
  public void clickOpenBooking() throws Exception
  {
	  OrderController.getProductsFromServer(ClientUI.getMyClient().getUser());
		ClientUI.getOrderGUI().start();
  }
  @FXML
  public void clickUpdateCatlog() throws Exception
  {
	  ClientUI.getMyInstance().getOrderGUI().start();
		OrderController.getProductsFromServer(ClientUI.getMyInstance().getMyClient().getUser());
		ClientUI.getOrderGUI().start();
  }
}
