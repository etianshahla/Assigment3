package server.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.Constants.MyConstants;
import common.Entities.OrdersView;
import common.Entities.User;
import common.OurMessage.Message;
import ocsf.server.ConnectionToClient;

public class ServerOrderController {

	public static void getAllProducts(Connection connDB, ConnectionToClient client,User user) {
		Message messageToClient = new Message();
		List<OrdersView> data = new ArrayList<>();
		ResultSet rs = null;
		try {
             System.out.println(user.getId());
			PreparedStatement pst = connDB.prepareStatement("SELECT DISTINCT Orders.id,Orders.totalPrice from Orders,items,users WHERE items.id = Orders.item_id AND Orders.client_id  = "+user.getId()+"");
	
			rs = pst.executeQuery();
			while (rs.next()) {
		System.out.println(rs.getString(1));
		System.out.println(rs.getString(2));
		OrdersView ordersView = new OrdersView();		
		ordersView.setId(rs.getInt(1));
		ordersView.setPrice(rs.getFloat(2));
          data.add(ordersView);
			}
			messageToClient.setWhatToDo(MyConstants.GET_PRODUCTS);
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

}
