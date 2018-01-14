package common.Entities;

import java.io.Serializable;

/**
 * User - the class of the user in myFuel System.
 */
public class User implements Serializable {

	protected String username;
	protected String id;
	protected String password;
	protected String userType;

	public User() {
		super();
	}

	public User(String username, String id, String password, String userType) {
		super();
		this.username = username;
		this.id = id;
		this.password = password;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}