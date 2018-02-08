package common.Entities;

import java.io.Serializable;

public class SurveyResults implements Serializable{

	private int id;
	private String conclusions;
	private int avg_Q1;
	private int avg_Q2;
	private int avg_Q3;
	private int avg_Q4;
	private int avg_Q5;
	private int avg_Q6;
	public SurveyResults(int id, String conclusions, int avg_Q1, int avg_Q2, int avg_Q3, int avg_Q4, int avg_Q5,
			int avg_Q6) {
		super();
		this.id = id;
		this.conclusions = conclusions;
		this.avg_Q1 = avg_Q1;
		this.avg_Q2 = avg_Q2;
		this.avg_Q3 = avg_Q3;
		this.avg_Q4 = avg_Q4;
		this.avg_Q5 = avg_Q5;
		this.avg_Q6 = avg_Q6;
	}
	public SurveyResults() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConclusions() {
		return conclusions;
	}
	public void setConclusions(String conclusions) {
		this.conclusions = conclusions;
	}
	public int getAvg_Q1() {
		return avg_Q1;
	}
	public void setAvg_Q1(int avg_Q1) {
		this.avg_Q1 = avg_Q1;
	}
	public int getAvg_Q2() {
		return avg_Q2;
	}
	public void setAvg_Q2(int avg_Q2) {
		this.avg_Q2 = avg_Q2;
	}
	public int getAvg_Q3() {
		return avg_Q3;
	}
	public void setAvg_Q3(int avg_Q3) {
		this.avg_Q3 = avg_Q3;
	}
	public int getAvg_Q4() {
		return avg_Q4;
	}
	public void setAvg_Q4(int avg_Q4) {
		this.avg_Q4 = avg_Q4;
	}
	public int getAvg_Q5() {
		return avg_Q5;
	}
	public void setAvg_Q5(int avg_Q5) {
		this.avg_Q5 = avg_Q5;
	}
	public int getAvg_Q6() {
		return avg_Q6;
	}
	public void setAvg_Q6(int avg_Q6) {
		this.avg_Q6 = avg_Q6;
	}
	
	
	



}
