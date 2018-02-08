package server.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import common.Constants.MyConstants;
import common.Entities.Item;
import common.Entities.Order;
import common.Entities.OrdersView;
import common.Entities.User;
import common.OurMessage.Message;
import ocsf.server.ConnectionToClient;

public class serverCatlogController {

	private static Order order;

	public static void getAllProductsInCatlog(Connection connDB, ConnectionToClient client) {
		Message messageToClient = new Message();
		List<Item> data = new ArrayList<>();
		ResultSet rs = null;
		try {

			PreparedStatement pst = connDB.prepareStatement("SELECT * FROM items WHERE id_number_in_catlog = 1");

			rs = pst.executeQuery();
			while (rs.next()) {

				Item item = new Item();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(3));
				item.setPrice(rs.getFloat(4));
				item.setType(rs.getString(6));
				data.add(item);
			}
			messageToClient.setWhatToDo(MyConstants.GET_PRODUCTS_IN_CATLOG);
			messageToClient.setData(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void addItemToOrder(Connection connDB, ConnectionToClient client, Message message,
			ArrayList<Object> array) {
		Message messageToClient = new Message();
		System.out.println(message.getWhatToDo() + "   " + message.getData().size());
		List<Item> data = new ArrayList<>();
		try {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof Order) {
					order = (Order) array.get(i);
					break;
				}

			}
			System.out.println(order.getClientName());
			String query = "INSERT INTO Orders(  client_name ,  client_id ,  item_id ,  DateOfSupply ,  HowToCollect ,  address ,  reciverName ,  phoneNumber ,  quantity ,  totalPrice ) VALUES (?,?,?,?,?,?,?,?,?,?)";
			// statement.executeUpdate("INSERT INTO Orders( client_name, client_id, item_id)
			// VALUES
			// ("+order.getClientName()+","+order.getClientId()+","+order.getItemId()+"");
			PreparedStatement preparedStmt = connDB.prepareStatement(query);
			preparedStmt.setString(1, order.getClientName());
			preparedStmt.setInt(2, order.getClientId());
			preparedStmt.setInt(3, order.getItemId());
			preparedStmt.setString(4, order.getDateOfSupply());
			preparedStmt.setString(5, order.getHowToCollect());
			preparedStmt.setString(6, order.getAddress());
			preparedStmt.setString(7, order.getReciverName());
			preparedStmt.setString(8, order.getPhoneNumber());
			preparedStmt.setInt(9, order.getQuantity());
			preparedStmt.setFloat(10, order.getPrice());
			preparedStmt.execute();
			messageToClient.setWhatToDo(MyConstants.GET_PRODUCTS_IN_CATLOG);
			messageToClient.setData(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		messageToClient.setWhatToDo(MyConstants.ADD_ITEM_TO_ORDER_FROM_CATLOG);
		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void getAllProducts(Connection connDB, ConnectionToClient client) {
		Message messageToClient = new Message();
		List<Item> data = new ArrayList<>();
		ResultSet rs = null;
		try {

			PreparedStatement pst = connDB.prepareStatement("SELECT * FROM items");

			rs = pst.executeQuery();
			while (rs.next()) {

				Item item = new Item();
				item.setId(rs.getInt(1));
				item.setName(rs.getString(3));
				item.setPrice(rs.getFloat(4));
				item.setColor(rs.getString(5));
				item.setType(rs.getString(6));
				data.add(item);
			}
			messageToClient.setWhatToDo(MyConstants.GET_PRODUCTS_SELF_SELECTION);
			messageToClient.setData(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void addItemToCatlog(Connection connDB, ConnectionToClient client, ArrayList<Object> array) {
		Message messageToClient = new Message();
		Item item = new Item();
		ResultSet rs = null;
		try {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof Item) {
					item = (Item) array.get(i);
					break;
				}
			}
				PreparedStatement pst = connDB.prepareStatement(
						"INSERT INTO items(id_number_in_catlog, name, price, color, type) VALUES (?,?,?,?,?)");
				pst.setInt(1, 1);
				pst.setString(2, item.getName());
				pst.setFloat(3, item.getPrice());
				pst.setString(4, "Red");
				pst.setString(5, item.getType());
				 pst.execute();
				
					messageToClient.setWhatToDo(MyConstants.ADD_ITEM_TO_CATLOG_SUCCESS);
				

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void updateItemToCatlog(Connection connDB, ConnectionToClient client, ArrayList<Object> array) {
		Message messageToClient = new Message();
		Item item = new Item();
		ResultSet rs = null;
		try {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof Item) {
					item = (Item) array.get(i);
					break;
				}
			}//price, color, type
				PreparedStatement pst = connDB.prepareStatement(
						"UPDATE  items SET  name=?,price = ?,color=?,type=? WHERE id="+ item.getId());
				
				pst.setString(1, item.getName());
				pst.setFloat(2, item.getPrice());
				pst.setString(3, "Red");
				pst.setString(4, item.getType());
				 pst.execute();
				
					messageToClient.setWhatToDo(MyConstants.UPDATE_ITEM_IN_CATLOG_SUCCESS);
				

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
