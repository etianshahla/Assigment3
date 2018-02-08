package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Constants.MyConstants;
import common.Entities.SurveyResults;
import common.OurMessage.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myClient.MyClient;

public class ServiceGUI implements Initializable {
	SurveyResults surveyResults = MyClient.surveyResults;
	@FXML
	private TextField question1Result, question2Result, question3Result, question4Result, question5Result,
			question6Result;
	@FXML
	private TextArea conclusion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("score 1" + surveyResults.getAvg_Q1());
		this.question1Result.setText(String.valueOf(surveyResults.getAvg_Q1()));
		this.question2Result.setText(String.valueOf(surveyResults.getAvg_Q2()));
		this.question3Result.setText(String.valueOf(surveyResults.getAvg_Q3()));
		this.question4Result.setText(String.valueOf(surveyResults.getAvg_Q4()));
		this.question5Result.setText(String.valueOf(surveyResults.getAvg_Q5()));
		this.question6Result.setText(String.valueOf(surveyResults.getAvg_Q6()));
		this.question1Result.setEditable(false);
		this.question2Result.setEditable(false);
		this.question3Result.setEditable(false);
		this.question4Result.setEditable(false);
		this.question5Result.setEditable(false);
		this.question6Result.setEditable(false);
	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("Service.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("Items");
		stage.show();
	}

	public SurveyResults getSurveyResults() {
		return surveyResults;
	}

	public void setSurveyResults(SurveyResults surveyResults) {
		this.surveyResults = surveyResults;
	}

	@FXML
	public void clickSaveButton() throws Exception {
		if (conclusion.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("please set your conclusion");
			alert.show();
		} else {
			surveyResults.setConclusions(conclusion.getText());
			Message message = new Message();
			message.setWhatToDo(MyConstants.SAVE_SURVEY_RESULT);
			message.setData(surveyResults);
			ClientUI.getMyClient().sendToServer(message);
		}
	}
}
