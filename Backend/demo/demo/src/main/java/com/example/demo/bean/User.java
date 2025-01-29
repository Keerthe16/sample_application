package com.example.demo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String id;
	private String email;
	private String password;
	private String username;
	private String contactNumber;
	private String role; 
	private String status; 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User() {
		
	}
	public User(String id, String email, String password, String username,  String contactNumber,
			String role, String status) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.contactNumber = contactNumber;
		this.role = role;
		this.status = status;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
