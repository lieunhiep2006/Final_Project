package com.bakershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bakershop.model.User;
import com.bakershop.utils.PasswordUtils;

public class UserDAO {
    public boolean updateProfile(User user) {
        String sql = "UPDATE [User] SET full_name = ?, phone_number = ?, address = ? WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhoneNumber());
            ps.setString(3, user.getAddress());
            ps.setLong(4, user.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateUser(Long Id, String fullName, String phoneNumber, String address) {
        String sql = "Update [User] set full_name = ?, phone_number = ?, address = ? where id = ?";
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, phoneNumber);
            ps.setString(3, address);
            ps.setLong(4, Id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(Long Id) {
        String sql = "select * from [User] where id = ?";
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, Id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("full_name"),
                            rs.getString("phone_number"),
                            rs.getString("password_hash"),
                            rs.getString("address"),
                            rs.getString("role")
                        );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        String sql = "select * from [user] u where u.phone_number = ?";
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, phoneNumber);
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        return new User(
                            rs.getLong("id"),
                            rs.getString("full_name"),
                            rs.getString("phone_number"),
                            rs.getString("password_hash"),
                            rs.getString("address"),
                            rs.getString("role")
                        );
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User login(String phoneNumber, String password) {
        User user = getUserByPhoneNumber(phoneNumber);
        if(user != null && PasswordUtils.checkPassword(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }

    public boolean register(User user) {
        String sql = "Insert into [User] (full_name, phone_number, password_hash, address, role) values (?,?,?,?, 'CUSTOMER')";
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhoneNumber());
            ps.setString(3, PasswordUtils.hashPassword(user.getPasswordHash()));
            ps.setString(4, user.getAddress());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePasswordByPhone(String phoneNumber, String newPassword) {
        String hashedPassword = PasswordUtils.hashPassword(newPassword);
        String sql = "UPDATE [User] SET password_hash = ? WHERE phone_number = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hashedPassword);
            ps.setString(2, phoneNumber);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}