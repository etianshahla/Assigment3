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
import common.Entities.User;
import common.OurMessage.Message;
import ocsf.server.ConnectionToClient;

public class ServerOrderController {

	public static void getAllProducts(Connection connDB, ConnectionToClient client) {
		Message messageToClient = new Message();
		List<String> data = new ArrayList<>();
		ResultSet rs = null;

		try {

			PreparedStatement pst = connDB.prepareStatement("SELECT * FROM Items");
	
			rs = pst.executeQuery();
			while (rs.next()) {
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				System.out.println(data.size());

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
