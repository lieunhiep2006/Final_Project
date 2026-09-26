package com.bakershop.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakershop.model.Cake;


public class CakeDAO {
    public Cake findById(int id) {
        return getCakeById((long) id);
    }

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
        String sql = "select * from cake where category_id = ?";


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
                    c.setCategoryId(rs.getLong("category_id"));
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
                        c.setCategoryId(rs.getLong("category_id"));
                        c.setImageUrl(rs.getString("image_url"));
                        list.add(c);
                    }
                }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Cake> getTop8Cakes() {
        List<Cake> list = new ArrayList<>();

        String sql = "select top 8 c.id, c.name, c.description, c.price, c.stock_quantity, c.category_id, c.image_url, Sum(oi.quantity) as total_sold"
        + " from cake c, order_items oi"
        + " where c.id = oi.cake_id"
        + " group by  c.id, c.name, c.description, c.price, c.stock_quantity, c.category_id, c.image_url"
        + " order by total_sold desc";

        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Cake c = new Cake();
                c.setId(rs.getLong("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setPrice(rs.getDouble("price"));
                c.setStockQuantity(rs.getInt("stock_quantity"));
                c.setCategoryId(rs.getLong("category_id"));
                c.setImageUrl(rs.getString("image_url"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Cake getCakeById(Long id) {
        String sql = "select * from cake where id = ?";
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Cake c = new Cake();
                    c.setId(rs.getLong("id"));
                    c.setName(rs.getString("name"));
                    c.setDescription(rs.getString("description"));
                    c.setPrice(rs.getDouble("price"));
                    c.setStockQuantity(rs.getInt("stock_quantity"));
                    c.setCategoryId(rs.getLong("category_id"));
                    c.setImageUrl(rs.getString("image_url"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}