package com.bakershop.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakershop.model.Cake;


public class CakeDAO {
    public List<Cake> getAllCakes() {
        List<Cake> list = new ArrayList<>();
        String sql = "select * from cake";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cake c = new Cake();
                c.setId(rs.getLong("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setPrice(rs.getDouble("price"));
                c.setStockQuantity(rs.getInt("stock_quantity"));
                c.setImageUrl(rs.getString("image_url"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Cake> getCakeByCategory(String catId) {
        List<Cake> list = new ArrayList<>();
        String sql = "select * from cake c where c.category_id = ?";


        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);) {
                
            ps.setString(1, catId);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cake c = new Cake();
                    c.setId(rs.getLong("id"));
                    c.setName(rs.getString("name"));
                    c.setDescription(rs.getString("description"));
                    c.setPrice(rs.getDouble("price"));
                    c.setStockQuantity(rs.getInt("stock_quantity"));
                    c.setImageUrl(rs.getString("image_url"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Cake> getCakeBySearch(String keyword) {
        List<Cake> list = new ArrayList<>();
        String sql = "select * from cake where name like ?";
        

        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
                ps.setString(1, "%" + keyword + "%");

                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Cake c = new Cake();
                        c.setId(rs.getLong("id"));
                        c.setName(rs.getString("name"));
                        c.setDescription(rs.getString("description"));
                        c.setPrice(rs.getDouble("price"));
                        c.setStockQuantity(rs.getInt("stock_quantity"));
                        c.setImageUrl(rs.getString("image_url"));
                        list.add(c);
                    }
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}