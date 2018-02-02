package server.controllers;


	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;

	import common.Constants.*;
	import common.Entities.*;
	import ocsf.server.*;
	import common.OurMessage.*;;

	public class ServerLoginController 
	{
		/**
		 * to check if the user exists in the DB
		 * @param conn
		 * @param client
		 * @param user
		 * @throws Exception
		 */
		public static void checkIfUserExist(Connection conn, ConnectionToClient client, User user) throws Exception
		{
			Message messageToClient = new Message();
			ArrayList<Object> array = new ArrayList<>();
			User userInDB = new User();
			String role = null;
			int status = 0;

			ResultSet rs = null;
			Statement stmt;
		
			
			try
			{
				
				stmt = conn.createStatement();
			  PreparedStatement	pst = conn.prepareStatement("SELECT * FROM users");
			  rs = pst.executeQuery();
				
				while(rs.next())
				{     userInDB.setId(rs.getInt(1));
					  userInDB.setUsername(rs.getString(2));
						userInDB.setPassword(rs.getString(3));
						userInDB.setUserType(rs.getString(4));
						if (userInDB.getUsername().equals(user.getUsername())  && userInDB.getPassword().equals(user.getPassword())) {
							messageToClient.setWhatToDo(MyConstants.LOGIN_SUCCESS);
							System.out.println("logged in");
							messageToClient.setData(userInDB);
							break;
						}
				}
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
		
			////// send the message back to the client ////////////////////////////
			try
			{
				((ConnectionToClient) client).sendToClient(messageToClient);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}
	
		
		//********************************************************
		
		public static void signOut(Connection conn, ConnectionToClient client, Object data) throws Exception
		{
			Message messageToClient = new Message();
			ArrayList<Object> array = new ArrayList<>();
			String id = null;

			Statement stmt;
			
			array = (ArrayList<Object>) data;
			id = (String) array.get(0);
			
			try
			{
				stmt = conn.createStatement();
				PreparedStatement ps = conn.prepareStatement("UPDATE user SET status=(?) where id=(?)");

				ps.setInt(1, 0);
				ps.setString(2, id);
				ps.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			////// send the message back to the client ////////////////////////////
			try
			{
				messageToClient.setWhatToDo(MyConstants.SIGN_OUT_SUCCESS);
				((ConnectionToClient) client).sendToClient(messageToClient);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}	
		}
}
