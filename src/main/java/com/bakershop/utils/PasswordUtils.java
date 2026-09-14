package com.bakershop.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
	private PasswordUtils() { }

	public static String hash(String password) {
		try {
			byte[] bytes = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder result = new StringBuilder(bytes.length * 2);
			for (byte value : bytes) {
				result.append(String.format("%02x", value));
			}
			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 is unavailable", e);
		}
	}

	public static boolean matches(String password, String storedValue) {
		if (password == null || storedValue == null) {
			return false;
		}
		byte[] candidate = password.getBytes(StandardCharsets.UTF_8);
		byte[] stored = storedValue.getBytes(StandardCharsets.UTF_8);
		return MessageDigest.isEqual(hash(password).getBytes(StandardCharsets.UTF_8), stored)
				|| MessageDigest.isEqual(candidate, stored);
	}
}
