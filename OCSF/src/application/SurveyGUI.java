package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.NumberTextField;
import common.Constants.MyConstants;
import common.Entities.Client;
import common.Entities.ClientSurvey;
import common.Entities.SurveyResults;
import common.OurMessage.Message;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SurveyGUI implements Initializable {
	@FXML
	private ComboBox<String> clientNames;
	private List<Client> clients = new ArrayList<>();
	@FXML
	private TextField id;
	@FXML
	private NumberTextField question1Value, question2Value, question3Value, question4Value, question5Value,
			question6Value;
@FXML
private Button cancelButton;
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
		Stage stage = new Stage();
		URL url = getClass().getResource("Survey.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("employee");
		stage.show();
	}

	@FXML
	public void selectClient() {
		for (int i = 0; i < clients.size(); i++) {

			if (clientNames.getValue() == clients.get(i).getName()) {
				System.out.println(clients.get(i).getType());
				id.setText(String.valueOf(clients.get(i).getId()));

			}
		}
	}

	@FXML
	public void clickSaveButton() throws IOException {
		ClientSurvey clientSurvey = new ClientSurvey();
		if (checkValueBetweenRange(question1Value.getText()) && checkValueBetweenRange(question2Value.getText())
				&& checkValueBetweenRange(question3Value.getText()) && checkValueBetweenRange(question4Value.getText())
				&& checkValueBetweenRange(question5Value.getText())
				&& checkValueBetweenRange(question6Value.getText())) {
			clientSurvey.setClientName(clientNames.getValue());
			clientSurvey.setClient_id(Integer.parseInt(id.getText()));
			clientSurvey.setScoreQuestion1(Integer.parseInt(question1Value.getText()));
			clientSurvey.setScoreQuestion2(Integer.parseInt(question2Value.getText()));
			clientSurvey.setScoreQuestion3(Integer.parseInt(question3Value.getText()));
			clientSurvey.setScoreQuestion4(Integer.parseInt(question4Value.getText()));
			clientSurvey.setScoreQuestion5(Integer.parseInt(question5Value.getText()));
			clientSurvey.setScoreQuestion6(Integer.parseInt(question6Value.getText()));
			Message message = new Message();
			message.setWhatToDo(MyConstants.ADD_CLIENT_SURVEY);
			message.setData(clientSurvey);
			ClientUI.getMyClient().sendToServer(message);
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Message Here...");
			alert.setContentText("one or more of the question score is invalid please fix them ");
			alert.show();
		}

	}

	public boolean checkValueBetweenRange(String value) {
		int number = Integer.parseInt(value);
		return number >= 1 && number <= 10;

	}
	@FXML
	public void clickCancelButton()
	{
		Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();	
	    
	}
}
