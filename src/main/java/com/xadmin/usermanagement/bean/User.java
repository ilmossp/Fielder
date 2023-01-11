package com.xadmin.usermanagement.bean;

public class User {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String email;
	private String password;
	private String type;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String email, String password, String type) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	
	
	
	
}
