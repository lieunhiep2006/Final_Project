package com.bakershop.model;

public class User {
	private int id;
	private String fullName;
	private String phoneNumber;
	private String passwordHash;
	private String address;
	private String role;

	public int getId() { 
		return id; 
	}
	public void setId(int id) { 
		this.id = id; 
	}
	public String getFullName() { 
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber; 
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber; 
	}
	public String getPasswordHash() { 
		return passwordHash; 
	}
	public void setPasswordHash(String passwordHash) { 
		this.passwordHash = passwordHash; 
	}
	public String getAddress() { 
		return address; 
	}
	public void setAddress(String address) { 
		this.address = address; 
	}
	public String getRole() { 
		return role; 
	}
	public void setRole(String role) { 
		this.role = role; 
	}
}
