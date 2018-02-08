package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import common.Constants.MyConstants;
import common.Entities.Order;
import common.OurMessage.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MoreDetialsGUI implements Initializable {
	private Order order ;
	@FXML
	private DatePicker dateOfSupply = new DatePicker();

	@FXML
	private CheckBox independent = new CheckBox();

	@FXML
	private CheckBox courier = new CheckBox();

	@FXML
	private Label courierPrice;

	@FXML
	private TextField address = new TextField();

	@FXML
	private TextField receiverName = new TextField();

	@FXML
	private TextField phoneNumber = new TextField();

	@FXML
	private TextField quantity = new TextField();

	@FXML
	private Label totbalPrice;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.order=new Order();
		
	}

	public void start() throws Exception

	{

			
		Stage stage = new Stage();
		URL url = getClass().getResource("MoreDetails.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("More Details");
		stage.show();
		order = ClientUI.getCatlogGUI().getOrder();
	}

	@SuppressWarnings("unlikely-arg-type")
	@FXML
	public void clickOnAddButton() throws IOException {
        Order order = ClientUI.getMyInstance().getCatlogGUI().getOrder();
         System.out.println(order.getClientName());
        if (order.getClientName()==null) {
  order=      	ClientUI.getMyInstance().getItemsGUI().getOrder();
		}
        Order orderToAdd = new Order();
       
		if (courier.isSelected()) {
			courierPrice.setText("100");
			if (address.getText().equals("") || receiverName.getText().equals("") || phoneNumber.getText().equals("")
					|| quantity.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("one of the argument doesn't set please set all mising filed");
				alert.show();
				return;
			}
			System.out.println(order.getClientId() +"   "+ order.getClientName() + "  " + order.getItemId());
		    orderToAdd.setClientId(order.getClientId());
		    orderToAdd.setClientName(order.getClientName());
		    orderToAdd.setItemId(order.getItemId());
			orderToAdd.setHowToCollect("courier");
			orderToAdd.setAddress(address.getText());
			orderToAdd.setCourierPrice(Float.parseFloat(courierPrice.getText()));
			orderToAdd.setReciverName(receiverName.getText());
			orderToAdd.setPhoneNumber(phoneNumber.getText());
			order.setPrice(Float.parseFloat(courierPrice.getText())+ (Float.parseFloat(quantity.getText()) * order.getItemPrice()));
		} else if (independent.isSelected()) {
			order.setHowToCollect("independent");
			courierPrice.setText("0");
			 orderToAdd.setClientId(order.getClientId());
			    orderToAdd.setClientName(order.getClientName());
			    orderToAdd.setItemId(order.getItemId());
				orderToAdd.setHowToCollect("independent");
				orderToAdd.setAddress(address.getText());
				orderToAdd.setCourierPrice(Float.parseFloat(courierPrice.getText()));
				orderToAdd.setReciverName(receiverName.getText());
				orderToAdd.setPhoneNumber(phoneNumber.getText());
				orderToAdd.setPrice(Float.parseFloat(courierPrice.getText())+ (Float.parseFloat(quantity.getText()) * order.getItemPrice()));
		}
		orderToAdd.setDateOfSupply(dateOfSupply.getEditor().getText());
		orderToAdd.setQuantity(Integer.parseInt(quantity.getText()));
		Message message = new Message();
		message.setWhatToDo(MyConstants.ADD_ITEM_TO_ORDER_FROM_CATLOG);
		message.setData(orderToAdd);
		try {
			ClientUI.getMyInstance().getMyClient().openConnection();
			ClientUI.getMyInstance().getMyClient().handleMessageFromClientUI(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Order setOrder(Order order2) {
		return order2;
	}

}
