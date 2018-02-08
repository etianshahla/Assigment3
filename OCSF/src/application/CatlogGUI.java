package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.Constants.MyConstants;
import common.Entities.Item;
import common.Entities.Order;
import common.Entities.User;
import common.OurMessage.Message;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myClient.MyClient;

public class CatlogGUI implements Initializable {
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private TextField price;
	@FXML
	private TextField type;
  private static  Order order = new Order();
	List<Item> items = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.items = ClientUI.getMyClient().items;
		List<String> names = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			names.add(items.get(i).getName());
		}

		comboBox.setItems(FXCollections.observableArrayList(names));
	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("Catlog.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("Catlog");
		stage.show();
	}

	@FXML
	public void setData() {
		for (int i = 0; i < items.size(); i++) {

			if (comboBox.getValue() == items.get(i).getName()) {
				System.out.println(items.get(i).getType());
				price.setText(String.valueOf(items.get(i).getPrice()));
				type.setText(items.get(i).getType());
				price.setEditable(false);
				type.setEditable(false);
			}
		}
	}
	@FXML
	public void selectItem() throws Exception
	{  
	User user = null;
	Message message = new Message();
	user =ClientUI.getMyInstance().getMyClient().getUser();
	System.out.println(user.getUsername());
		for (int i = 0; i < items.size(); i++) {
			if (comboBox.getValue().equals(items.get(i).getName())) {
				  order = new Order();
				 order.setClientId(user.getId());
				 order.setClientName(user.getUsername());
				 order.setItemId(items.get(i).getId());		
				 order.setItemPrice(items.get(i).getPrice());
				 break;
			}
		}
			MoreDetialsGUI moreDetialsGUI = new MoreDetialsGUI();
			moreDetialsGUI.setOrder(order);
			moreDetialsGUI.start();
			
			
		
	}
	public Order getOrder()
	{
		return order;
	}
}
