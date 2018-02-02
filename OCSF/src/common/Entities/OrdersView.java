package common.Entities;

import java.io.Serializable;

public class OrdersView  implements Serializable{
	private static final long serialVersionUID = 1L;
private int id;
private float price;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}


}
