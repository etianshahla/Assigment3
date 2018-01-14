package common.Entities;

public class Order {

	private int id;
	private String clientName;
	private int clientId;
	private int itemId;
	public Order(int id, String clientName, int clientId, int itemId) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.clientId = clientId;
		this.itemId = itemId;
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
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
}
