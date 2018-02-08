package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.Constants.MyConstants;
import common.Entities.Item;
import common.OurMessage.Message;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import myClient.MyClient;

public class UpdateCatlogGUI implements Initializable {
	@FXML
	private ComboBox<String> itemsList=new ComboBox<>();
	List<Item> items;
	@FXML
	private TextField itemName;
	@FXML
	private TextField price;
	@FXML
	private TextField type;
@FXML
private Button closeButton;
	private int id;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.getMyClient();
		this.items = MyClient.items;
		System.out.println("items size" + items.size());
		List<String> names = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			names.add(items.get(i).getName());
		}

		itemsList.setItems(FXCollections.observableArrayList(names));

	}

	public void start() throws IOException {
		Stage stage = new Stage();
		URL url = getClass().getResource("UpdateCatalog.fxml");
		AnchorPane pane = FXMLLoader.load(url);
		Scene scene = new Scene(pane, 600, 600);
		stage.setScene(scene);
		stage.setTitle("update catlog");
		stage.show();

	}

	@FXML
	public void selectItem() {
		for (int i = 0; i < items.size(); i++) {

			if (itemsList.getValue() == items.get(i).getName()) {
				System.out.println(items.get(i).getType());
				itemName.setText(items.get(i).getName());
				id = items.get(i).getId();
				price.setText(String.valueOf(items.get(i).getPrice()));
				type.setText(items.get(i).getType());

			}
		}
	}

	@FXML
	public void clickAddButton() throws IOException {
		if (itemsList.getValue()==null) {

			Item item = new Item();
			item.setName(itemName.getText());
			item.setPrice(Float.parseFloat(price.getText()));
			item.setType(type.getText());
			Message message = new Message();
			message.setData(item);
			message.setWhatToDo(MyConstants.ADD_ITEM_TO_CATLOG);
			ClientUI.getMyInstance().getMyClient().sendToServer(message);

		}
	}

	@FXML
	public void clickSaveButton() throws IOException {
		if (itemsList.getValue()!=null) {

			Item item = new Item();
			item.setName(itemName.getText());
			item.setPrice(Float.parseFloat(price.getText()));
			item.setType(type.getText());
			item.setId(id);
			Message message = new Message();
			message.setData(item);
			message.setWhatToDo(MyConstants.UPDATE_ITEM_TO_CATLOG);
			ClientUI.getMyInstance().getMyClient().sendToServer(message);

		}
	}
	@FXML
	public void clickOnCancelButton() {
		  Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();	
		    }
}
