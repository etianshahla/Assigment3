package common.Entities;

public class Complaint {
private int id;
private String clientName;
private String compensation;
private String complaintTime;
private String description;
private int orderId;
private String timeToResponse;
private String response;
public Complaint(int id, String clientName, String compensation, String complaintTime, String description, int orderId,
		String timeToResponse, String response) {
	super();
	this.id = id;
	this.clientName = clientName;
	this.compensation = compensation;
	this.complaintTime = complaintTime;
	this.description = description;
	this.orderId = orderId;
	this.timeToResponse = timeToResponse;
	this.response = response;
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
public String getCompensation() {
	return compensation;
}
public void setCompensation(String compensation) {
	this.compensation = compensation;
}
public String getComplaintTime() {
	return complaintTime;
}
public void setComplaintTime(String complaintTime) {
	this.complaintTime = complaintTime;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public String getTimeToResponse() {
	return timeToResponse;
}
public void setTimeToResponse(String timeToResponse) {
	this.timeToResponse = timeToResponse;
}
public String getResponse() {
	return response;
}
public void setResponse(String response) {
	this.response = response;
}


}
