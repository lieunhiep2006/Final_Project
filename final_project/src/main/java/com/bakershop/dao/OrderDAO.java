package com.bakershop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakershop.model.Order;

public class OrderDAO {
    public List<Order> getListOrderById(Long Id) {
        String sql = "Select * from order where user_id = ?";
        List<Order> list = new ArrayList<>();
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, Id);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getLong("id"));
                    o.setUserId(rs.getLong("user_id"));
                    o.setVoucherId(rs.getLong("voucher_id"));
                    o.setDeliveryAddress(rs.getString("delivery_address"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
