package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.NumberTextField;
import common.Constants.MyConstants;
import common.Entities.Client;
import common.Entities.User;
import common.OurMessage.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OpenClientAccountGUI implements Initializable {
	@FXML
	private TextField customerName;
	@FXML
	private NumberTextField cridetNumber, cvv;
	@FXML
	private PasswordField password;

	@FXML
	private Button cancelButton;
	@FXML
	private CheckBox monthly, yearly;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("OpenClientAccount.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("shop manger");
		stage.show();
	}

	@FXML
	public void clickOnSave() {
		if (customerName.getText().equals("") || cridetNumber.getText().equals("") || password.getText().equals("")
				|| cvv.getText().equals("") || (monthly.isSelected() == false && yearly.isSelected() == false)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message Here...");
			alert.setContentText("please fill all the data to add the user ");
			alert.show();

		} else if (monthly.isSelected() && yearly.isSelected()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message Here...");
			alert.setContentText("please select monthly or yearly type not the both ");
			alert.show();
		} else {
			User user = new User();
			Client client = new Client();
			client.setName(customerName.getText());
			client.setCridetCard(cridetNumber.getText());
			client.setCvv(cvv.getText());
			if (yearly.isSelected()) {
				client.setType("yearly");
			}
			if (monthly.isSelected()) {
				client.setType("monthly");
			}
			user.setUsername(customerName.getText());
			user.setPassword(password.getText());
			user.setUserType("client");

			Message message = new Message();
			message.getData().add(client);
			message.getData().add(user);
			message.setWhatToDo(MyConstants.ADD_CLIENT);

			try {
				ClientUI.getMyClient().sendToServer(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void clickOnCancelButton() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
