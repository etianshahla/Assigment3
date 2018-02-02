package application;

import java.util.ArrayList;

import common.Constants.MyConstants;
import common.Entities.User;
import javafx.application.Application;

import javafx.stage.Stage;
 

public class Main extends Application{
	public static void main(String args[]) throws Exception 
	{ 
		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception 
	{
		ClientUI.getMyInstance().getLoginGUI().setStage(arg0);
		ClientUI.getMyInstance().getLoginGUI().start();	 
	}

}
