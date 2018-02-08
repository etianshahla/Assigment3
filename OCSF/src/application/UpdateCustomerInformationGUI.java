package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.Constants.MyConstants;
import common.Entities.Client;
import common.OurMessage.Message;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateCustomerInformationGUI implements Initializable {
	@FXML
	private ComboBox<String> clientNames;
	private List<Client> clients;
	@FXML
	private Label clientId;
	@FXML
	private TextField creditCard;
	@FXML
	private CheckBox monthly;
	@FXML
	private CheckBox yearly;
	@FXML
	private TextField clientNameText;
	Stage stage;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clients = ClientUI.getMyClient().clients;
		List<String> names = new ArrayList<>();
		for (int i = 0; i < clients.size(); i++) {
			System.out.println(clients.get(i).getName());
			names.add(clients.get(i).getName());
		}
		clientNames.setItems(FXCollections.observableArrayList(names));
	}

	public void start() throws IOException {
		 stage = new Stage();
		URL url = getClass().getResource("UpdateCustomerInformation.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("Update Customer Information");
		stage.show();
	}

	@FXML
	public void setData() {
		for (int i = 0; i < clients.size(); i++) {

			if (clientNames.getValue() == clients.get(i).getName()) {
				this.clientId.setText(String.valueOf(clients.get(i).getId()));
				this.creditCard.setText(clients.get(i).getCridetCard());
				this.clientNameText.setText(clients.get(i).getName());
				if (clients.get(i).getType().equals("monthly")) {
					monthly.setSelected(true);
				}
				if (clients.get(i).getType().equals("yearly")) {
					yearly.setSelected(true);
				}
			}
		}
	}
	
	@FXML
	public void clickOnSave()
	{
			Client clientUpdate = new Client();
			clientUpdate.setName(this.clientNameText.getText());
			clientUpdate.setId(Integer.parseInt(this.clientId.getText()));
			clientUpdate.setCridetCard(this.creditCard.getText());
			if (monthly.isSelected() && yearly.isSelected() == false ) {
				clientUpdate.setType("monthly");
			}
			if (yearly.isSelected() && monthly.isSelected()==false) {
				clientUpdate.setType("yearly");
			}
			if (yearly.isSelected() && monthly.isSelected()) {
				System.out.println("error");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message Here...");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("please select monthly or yearly you cant choose the both");
				alert.show();
			}
			Message message = new Message();
			message.setWhatToDo(MyConstants.UPDATE_CLIENT);
			message.setData(clientUpdate);
			try
			{				ClientUI.getMyInstance().getMyClient().handleMessageFromClientUI(message);
			}
			catch(IOException e)
			{	
				e.printStackTrace();
			}	
	}
	@FXML
	public void clickOnCancel()
	{
	stage.close();
	}
}
