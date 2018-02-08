package server.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Constants.MyConstants;
import common.Entities.Client;
import common.Entities.ClientSurvey;
import common.Entities.Item;
import common.Entities.SurveyResults;
import common.Entities.User;
import common.OurMessage.Message;
import ocsf.server.ConnectionToClient;

public class serverClientsController {

	public static void getAllClients(Connection connDB, ConnectionToClient clientConnection) {
		Message messageToClient = new Message();
		List<Client> data = new ArrayList<>();
		ResultSet rs = null;
		try {

			PreparedStatement pst = connDB.prepareStatement("SELECT * FROM Clients where ");

			rs = pst.executeQuery();
			while (rs.next()) {

				Client client = new Client();
				client.setId(rs.getInt(1));
				client.setName(rs.getString(2));
				client.setCridetCard(rs.getString(4));
				client.setType(rs.getString(3));
				client.setPromotional(rs.getString(5));
				data.add(client);
			}
			messageToClient.setWhatToDo(MyConstants.GET_ALL_CLIENT_DATA);
			messageToClient.setData(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) clientConnection).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void updateClient(Connection connDB, ConnectionToClient client, Message message,
			ArrayList<Object> array) throws SQLException {
		Client updateClient = null;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof Client) {
				System.out.println("helloo");
				updateClient = (Client) array.get(i);
			}
		}
		String query = "UPDATE  Clients SET  client_name=?,client_type = ?,creditCard=? WHERE id="
				+ updateClient.getId();
		//
		PreparedStatement preparedStmt = connDB.prepareStatement(query);
		preparedStmt.setString(1, updateClient.getName());
		preparedStmt.setString(2, updateClient.getType());
		preparedStmt.setString(3, updateClient.getCridetCard());

		// execute the preparedstatement
		preparedStmt.execute();
		Message messageToClient = new Message();
		messageToClient.setWhatToDo(MyConstants.UPDATE_CLIENT);

		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void checkIfClinetExist(Connection connDB, ConnectionToClient client, ArrayList<Object> array)
			throws SQLException {
		Client updateClient = null;
		Message messageToClient = new Message();

		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) instanceof Client) {
				System.out.println("helloo");
				updateClient = (Client) array.get(i);
			}
		}
		String query = "SELECT * FROM Clients";
		//
		PreparedStatement preparedStmt = connDB.prepareStatement(query);

		// execute the preparedstatement
		ResultSet rs = preparedStmt.executeQuery();
		while (rs.next()) {
			if (rs.getString(2).equals(updateClient.getName()) && rs.getString(4).equals(updateClient.getCridetCard())
					&& rs.getString(7).equals(updateClient.getCvv())) {
				messageToClient.setWhatToDo(MyConstants.CLIENT_EXIST);
			}
		}

		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void addClientSurvey(Connection connDB, ConnectionToClient client, ArrayList<Object> array) {
		Message messageToClient = new Message();
		ClientSurvey clientSurvey = null;
		ResultSet rs = null;
		try {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof ClientSurvey) {
					clientSurvey = (ClientSurvey) array.get(i);
					break;
				}
			}
			PreparedStatement pst = connDB.prepareStatement(
					"INSERT INTO  Client_Survey (client_id ,  client_name ,Q1_SCORE,  Q2_Score ,  Q3_Score ,  Q4_Score ,  Q5_Score ,  Q6_Score ) VALUES (?,?,?,?,?,?,?,?)");
			pst.setInt(1, clientSurvey.getClient_id());
			pst.setString(2, clientSurvey.getClientName());
			pst.setInt(3, clientSurvey.getScoreQuestion1());
			pst.setInt(4, clientSurvey.getScoreQuestion2());
			pst.setInt(5, clientSurvey.getScoreQuestion3());
			pst.setInt(6, clientSurvey.getScoreQuestion4());
			pst.setInt(7, clientSurvey.getScoreQuestion5());
			pst.setInt(8, clientSurvey.getScoreQuestion6());
			pst.execute();
			messageToClient.setWhatToDo(MyConstants.ADD_CLIENT_SURVEY_SUCCESS);

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

	public static void addClient(Connection connDB, ConnectionToClient client, ArrayList<Object> array) {
		Message messageToClient = new Message();
		Client clientToAdd = null;
		User userToADD = null;
		try {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof Client) {
					clientToAdd = (Client) array.get(i);

				} else if (array.get(i) instanceof User) {
					userToADD = (User) array.get(i);
				}
			}
			PreparedStatement pst = connDB.prepareStatement(
					"INSERT INTO  Clients (client_name ,  client_type ,  creditCard ,  cardType ,  cvv,Promotional ) VALUES (?,?,?,?,?,?)");
			pst.setString(1, clientToAdd.getName());
			pst.setString(3, clientToAdd.getCridetCard());
			pst.setString(2, clientToAdd.getType());
			pst.setString(4, "visa");
			pst.setString(5, clientToAdd.getCvv());
			pst.setString(6, "0");
			pst.execute();

			PreparedStatement pst2 = connDB
					.prepareStatement("INSERT INTO users(username, password, type) VALUES (?,?,?)");
			pst2.setString(1, userToADD.getUsername());
			pst2.setString(2, userToADD.getPassword());
			pst2.setString(3, userToADD.getUserType());
			pst2.execute();

			messageToClient.setWhatToDo(MyConstants.ADD_CLIENT_SUCCESS);

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

	public static void getSurveyResults(Connection connDB, ConnectionToClient client) {
		Message messageToClient = new Message();
		SurveyResults surveyResults = new SurveyResults();
		
		try {
		PreparedStatement pst = connDB.prepareStatement(
					"SELECT AVG(Q1_SCORE), AVG(Q2_Score), AVG(Q3_Score), AVG(Q4_Score), AVG(Q5_Score), AVG(Q6_Score) FROM Client_Survey\n" + 
					"");
			
          ResultSet rs =   pst.executeQuery();
          while (rs.next()) {
			surveyResults.setAvg_Q1(rs.getInt(1));
			surveyResults.setAvg_Q2(rs.getInt(2));
			surveyResults.setAvg_Q3(rs.getInt(3));
			surveyResults.setAvg_Q4(rs.getInt(4));
			surveyResults.setAvg_Q5(rs.getInt(5));
			surveyResults.setAvg_Q6(rs.getInt(6));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		messageToClient.setWhatToDo(MyConstants.GET_SURVEY_RESULTS);
messageToClient.setData(surveyResults);
		////// send the message back to the client ////////////////////////////
		try {
			((ConnectionToClient) client).sendToClient(messageToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveSurveyResults(Connection connDB, ConnectionToClient client, ArrayList<Object> array) {
		Message messageToClient = new Message();
		SurveyResults surveyResults = null;
		User userToADD = null;
		try {
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) instanceof SurveyResults) {
					surveyResults= (SurveyResults) array.get(i);

				} else if (array.get(i) instanceof User) {
					userToADD = (User) array.get(i);
				}
			}
			PreparedStatement pst = connDB.prepareStatement(
					"INSERT INTO  Survey_Results (Conclusions ,  avg_Q1 ,  avg_Q2 ,  avg_Q3 ,  avg_Q4 ,  avg_Q5 ,  avg_Q6 ) VALUES (?,?,?,?,?,?,?)");
			pst.setString(1, surveyResults.getConclusions());
			pst.setInt(2, surveyResults.getAvg_Q1());
			pst.setInt(3, surveyResults.getAvg_Q2());
			pst.setInt(4, surveyResults.getAvg_Q3());
			pst.setInt(5, surveyResults.getAvg_Q4());
			pst.setInt(6, surveyResults.getAvg_Q5());
			pst.setInt(7, surveyResults.getAvg_Q6());
			pst.execute();
			messageToClient.setWhatToDo(MyConstants.SAVE_SURVEY_RESULT_SUCCESS);

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
