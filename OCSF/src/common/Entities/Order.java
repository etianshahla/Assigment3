package common.Entities;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String clientName;
	private int clientId;
	private int itemId;
	private String dateOfSupply;
	private String howToCollect;
	private float courierPrice;
	private String address;
	private String reciverName;
	private String phoneNumber;
	private int quantity;
	private float price;
	private float itemPrice;

	public float getItemPrice() {
		return itemPrice;
	}

	public Order(int id, String clientName, int clientId, int itemId, String dateOfSupply, String howToCollect,
			float courierPrice, String address, String reciverName, String phoneNumber, int quantity, float price) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.clientId = clientId;
		this.itemId = itemId;
		this.dateOfSupply = dateOfSupply;
		this.howToCollect = howToCollect;
		this.courierPrice = courierPrice;
		this.address = address;
		this.reciverName = reciverName;
		this.phoneNumber = phoneNumber;
		this.quantity = quantity;
		this.price = price;
	}

	public Order() {

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

	public String getDateOfSupply() {
		return dateOfSupply;
	}

	public void setDateOfSupply(String string) {
		this.dateOfSupply = string;
	}

	public String getHowToCollect() {
		return howToCollect;
	}

	public void setHowToCollect(String howToCollect) {
		this.howToCollect = howToCollect;
	}

	public float getCourierPrice() {
		return courierPrice;
	}

	public void setCourierPrice(float courierPrice) {
		this.courierPrice = courierPrice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReciverName() {
		return reciverName;
	}

	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setItemPrice(float price2) {
this.itemPrice=price2;		
	}

}
