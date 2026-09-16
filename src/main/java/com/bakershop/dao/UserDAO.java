package com.bakershop.dao;

import com.bakershop.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private static final String USER_COLUMNS = "id, full_name, phone_number, password_hash, address, role";

	public User findByPhoneNumber(String phoneNumber) {
		String sql = "SELECT " + USER_COLUMNS + " FROM [User] WHERE phone_number = ?";
		try (Connection connection = DBContext.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, phoneNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return mapUser(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Unable to find user", e);
		}
		return null;
	}

	public boolean create(User user) {
		String sql = "INSERT INTO [User] (full_name, phone_number, password_hash, address, role) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DBContext.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getPhoneNumber());
			statement.setString(3, user.getPasswordHash());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getRole());
			return statement.executeUpdate() == 1;
		} catch (SQLException e) {
			if (e.getErrorCode() == 2627 || e.getErrorCode() == 2601) {
				return false;
			}
			throw new RuntimeException("Unable to create user", e);
		}
	}

	public boolean updateProfile(User user) {
		String sql = "UPDATE [User] SET full_name = ?, address = ? WHERE id = ?";
		try (Connection connection = DBContext.getConnection();
			 PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getAddress());
			statement.setInt(3, user.getId());
			return statement.executeUpdate() == 1;
		} catch (SQLException e) {
			throw new RuntimeException("Unable to update user profile", e);
		}
	}

	private User mapUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setFullName(resultSet.getString("full_name"));
		user.setPhoneNumber(resultSet.getString("phone_number"));
		user.setPasswordHash(resultSet.getString("password_hash"));
		user.setAddress(resultSet.getString("address"));
		user.setRole(resultSet.getString("role"));
		return user;
	}
}
