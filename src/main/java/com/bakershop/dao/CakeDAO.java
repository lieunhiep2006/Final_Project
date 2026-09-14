package com.bakershop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bakershop.model.Cake;

public class CakeDAO {
    public List<Cake> getAllCakes() {
        List<Cake> list = new ArrayList<>();
        String sql = "select id, name, description, price, stock_quantity, category_id from cake";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cake c = new Cake();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setPrice(rs.getDouble("price"));
                c.setStockQuantity(rs.getInt("stock_quantity"));
                c.setCategoryId(rs.getInt("category_id"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Cake findById(int id) {
        String sql = "select id, name, description, price, stock_quantity, category_id from cake WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cake cake = new Cake();
                    cake.setId(rs.getInt("id"));
                    cake.setName(rs.getString("name"));
                    cake.setDescription(rs.getString("description"));
                    cake.setPrice(rs.getDouble("price"));
                    cake.setStockQuantity(rs.getInt("stock_quantity"));
                    cake.setCategoryId(rs.getInt("category_id"));
                    return cake;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find cake", e);
        }
        return null;
    }
}