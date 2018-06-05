package application.model;

import java.util.Date;

public class User{
	String username;
	byte[] hashedPassword;
	String plaintextPassword;
	String firstname;
	String lastname;
	String phoneNumber;
	Date birthdate;
	int privilege; // 0 for user, 1 for employee

	public User(String username, int privilege){
		this.username = username;
		this.privilege = privilege;
	}
	
	public String getUser() {
		return username;
	}
	
	public int getPrivilege() {
		return privilege;
	}
}

