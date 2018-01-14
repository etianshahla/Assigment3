package application;

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
		LoginGUI aFrame = new LoginGUI();
		aFrame.start(arg0);
	}

}
