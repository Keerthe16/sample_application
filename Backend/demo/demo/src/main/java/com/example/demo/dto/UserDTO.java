package com.example.demo.dto;



public class UserDTO {
    private String id;
    private String password;
    private String email;
    private String username;
    private String contactNumber;
    private String role; 
    private String status; 

    public UserDTO() {
    }

    public UserDTO(String id, String email, String username, String contactNumber, String role, String status) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.contactNumber = contactNumber;
        this.role = role;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

	public String getPassword() {
	
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
}

