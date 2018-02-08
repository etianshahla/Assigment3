package common.Entities;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int id_number_inCatlog;
    private float price;
    private String type;	
    private String color;
    
   


	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Item(int id, String name, int id_number_inCatlog, float price) {
		super();
		this.id = id;
		this.name = name;
		this.id_number_inCatlog = id_number_inCatlog;
		this.price = price;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getId_number_inCatlog() {
		return id_number_inCatlog;
	}



	public void setId_number_inCatlog(int id_number_inCatlog) {
		this.id_number_inCatlog = id_number_inCatlog;
	}



	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setType(String type) {
	   this.type = 	type;
	}
	 public String getType() {
			return type;
		}
    
    
    
	
}
