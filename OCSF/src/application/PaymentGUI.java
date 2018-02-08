package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import common.NumberTextField;
import common.Constants.MyConstants;
import common.Entities.Client;
import common.Entities.OrdersView;
import common.OurMessage.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PaymentGUI implements Initializable {
	@FXML
	private Label amountToPay;
	@FXML
	private CheckBox cachCkeckBox;

	@FXML
	private CheckBox visaCheckBox;

	@FXML
	private NumberTextField cardNumber;

	@FXML
	private TextField cvv;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<OrdersView> orders = null;
		try {
			orders = ClientUI.getMyInstance().getOrderGUI().getOrders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float sum = 0;
		System.out.println(orders.size());
		for (int i = 0; i < orders.size(); i++) {
			sum += orders.get(i).getPrice();
		}
		amountToPay.setText(String.valueOf(sum));
	}

	public void start() throws Exception {
		Stage stage = new Stage();
		URL url = getClass().getResource("payment.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("payment");
		stage.show();

	}

	@FXML
	public void clickOkButton() throws IOException {
		Message message = new Message();
		Client client = new Client();
		if (visaCheckBox.isSelected()) {
			client.setCridetCard(cardNumber.getText());
			client.setName(ClientUI.getMyInstance().getMyClient().getUser().getUsername());
			client.setCvv(cvv.getText());

			message.setWhatToDo(MyConstants.CHECK_CLIENT_EXIST);
			message.setData(client);
			
			try {
				ClientUI.getMyInstance().getMyClient().sendToServer(message);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (cachCkeckBox.isSelected()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message Here...");
			alert.setContentText("please go to one of the employees on the store for payment");
			alert.show();
		}
	}
}
