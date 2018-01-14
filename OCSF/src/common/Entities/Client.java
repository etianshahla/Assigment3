package common.Entities;

import java.io.Serializable;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String type;
	private String cridetCard;
	private String promotional;
	public Client(int id, String name, String type, String cridetCard, String promotional) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.cridetCard = cridetCard;
		this.promotional = promotional;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCridetCard() {
		return cridetCard;
	}
	public void setCridetCard(String cridetCard) {
		this.cridetCard = cridetCard;
	}
	public String getPromotional() {
		return promotional;
	}
	public void setPromotional(String promotional) {
		this.promotional = promotional;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
