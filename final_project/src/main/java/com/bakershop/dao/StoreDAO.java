package com.bakershop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bakershop.model.Store;


public class StoreDAO {

    public List<Store> getAllStores() {
        List<Store> list = new ArrayList<>();
        String sql = "SELECT id, name, address, phone_number, opening_hours FROM store";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Store s = new Store();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getString("phone_number"));
                s.setOpeningHours(rs.getString("opening_hours"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}