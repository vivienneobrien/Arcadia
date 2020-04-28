package util;

import java.io.Serializable;

/**
 * User object encapsulates all data associated with a single
 * user.
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String email;
	private String biography;

	
	//-----------------------------------------
	// Two constructors; one for login, one for
	// account creation. 
	//-----------------------------------------
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String first_name, String last_name, String username, String password, String email, String biography) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.biography = biography;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
}