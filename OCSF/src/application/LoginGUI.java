package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;

import clinetcontrollers.LoginController;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myClient.MyClient;

public class LoginGUI implements Initializable {
	public static MyClient clinet;
	
	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginButton;

	LoginController loginController = new LoginController();

	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("Login.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
	clinet = new MyClient("localhost", 5555);
	}

	private void handleButtonAction(ActionEvent event) throws IOException {
		// Button was clicked, do something...
		String requestedID = username.getText();
		String requestedPass = password.getText();
			if(requestedID.length() == 0 || requestedPass.length()== 0 )
			{
				 
			 	JOptionPane.showMessageDialog(null,"Invalid Input");
			}
			else
			{
				clinet.openConnection();
				loginController.handleUserRequestToLogin(requestedID, requestedPass);
			
			}
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loginButton.setOnAction(event -> {
			try {
				handleButtonAction(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
