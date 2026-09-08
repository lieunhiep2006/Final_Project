package com.bakershop.model;

public class User {
	private String fullName;
	private String phoneNumber;
	private String password;
	private String address;
	private String role;

	public User() {
	}

	public User(String fullName, String phoneNumber, String password, String address, String role) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getRole() {
		return role;
	}
}
