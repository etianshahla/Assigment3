package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ItemsGUI implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("Items.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane,600,600);
		stage.setScene(scene);
		stage.setTitle("Catlog");
		stage.show();
	}
}
