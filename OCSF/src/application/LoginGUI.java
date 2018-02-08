package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;

import clinetcontrollers.LoginController;
import common.Constants.MyConstants;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import myClient.MyClient;

public class LoginGUI implements Initializable {
public static ClientUI clientUI;
	public static MyClient clinet;
	
	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginButton;
	private static LoginGUI myInstance = null;
     private Stage stage;

	public Stage getStage() {
		return stage;
	}
    
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	LoginController loginController = new LoginController();
    /**
     * 
     * @throws Exception
     */
	public void start() throws Exception {
		URL url = getClass().getResource("Login.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane,600,600);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
	}
      /**
       * click on login button 
       * @param event
       * @throws Exception
       */
	private void handleButtonAction(ActionEvent event) throws Exception {
		// Button was clicked, do something...
		String requestedID = username.getText();
		String requestedPass = password.getText();
			if(requestedID.length() == 0 || requestedPass.length()== 0 )
			{
				 
			 	JOptionPane.showMessageDialog(null,"Invalid Input");
			}
			else
			{   clinet = ClientUI.getMyInstance().getMyClient();
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public static LoginGUI getInstance()
	{
		if(myInstance == null)
			myInstance = new LoginGUI();
		return myInstance;
	}

	public Button getBtn() {
	loginButton = new Button("Login");
		return loginButton;
	}


}
