package common.Entities;

import java.io.Serializable;

public class ClientSurvey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String clientName;
	private int client_id;
	private int scoreQuestion1;
	private int scoreQuestion2;
	private int scoreQuestion3;
	private int scoreQuestion4;
	private int scoreQuestion5;
	private int scoreQuestion6;
	public ClientSurvey(int id, String clientName, int client_id, int scoreQuestion1, int scoreQuestion2,
			int scoreQuestion3, int scoreQuestion4, int scoreQuestion5, int scoreQuestion6) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.client_id = client_id;
		this.scoreQuestion1 = scoreQuestion1;
		this.scoreQuestion2 = scoreQuestion2;
		this.scoreQuestion3 = scoreQuestion3;
		this.scoreQuestion4 = scoreQuestion4;
		this.scoreQuestion5 = scoreQuestion5;
		this.scoreQuestion6 = scoreQuestion6;
	}
	public ClientSurvey()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getScoreQuestion1() {
		return scoreQuestion1;
	}
	public void setScoreQuestion1(int scoreQuestion1) {
		this.scoreQuestion1 = scoreQuestion1;
	}
	public int getScoreQuestion2() {
		return scoreQuestion2;
	}
	public void setScoreQuestion2(int scoreQuestion2) {
		this.scoreQuestion2 = scoreQuestion2;
	}
	public int getScoreQuestion3() {
		return scoreQuestion3;
	}
	public void setScoreQuestion3(int scoreQuestion3) {
		this.scoreQuestion3 = scoreQuestion3;
	}
	public int getScoreQuestion4() {
		return scoreQuestion4;
	}
	public void setScoreQuestion4(int scoreQuestion4) {
		this.scoreQuestion4 = scoreQuestion4;
	}
	public int getScoreQuestion5() {
		return scoreQuestion5;
	}
	public void setScoreQuestion5(int scoreQuestion5) {
		this.scoreQuestion5 = scoreQuestion5;
	}
	public int getScoreQuestion6() {
		return scoreQuestion6;
	}
	public void setScoreQuestion6(int scoreQuestion6) {
		this.scoreQuestion6 = scoreQuestion6;
	}
	
	
}
