package com.bakershop.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bakershop.model.User;

public class UserDAO {
	private static final Map<String, User> USERS = new ConcurrentHashMap<>();
    
	public boolean checkPhoneExists(String phoneNumber) {
		return phoneNumber != null && USERS.containsKey(phoneNumber);
	}

	public boolean register(User user) {
		if (user == null || user.getPhoneNumber() == null || user.getPhoneNumber().trim().isEmpty()) {
			return false;
		}
		return USERS.putIfAbsent(user.getPhoneNumber(), user) == null;
	}

	public User login(String phoneNumber, String password) {
		User user = phoneNumber == null ? null : USERS.get(phoneNumber);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}
