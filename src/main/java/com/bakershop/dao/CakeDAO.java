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
                c.setCategoryId(rs.getLong("category_id"));
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
        + " group by c.id, c.name, c.description, c.price, c.stock_quantity, c.category_id, c.image_url"
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

    public void deleteCake(int id) {
        String sql = "DELETE FROM cake WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cake> searchCakes(String keyword) {
        return getCakeBySearch(keyword);
    }

    public boolean insertCake(Cake cake) {
        String sql = "INSERT INTO cake (name, description, price, stock_quantity, category_id, image_url) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cake.getName());
            ps.setString(2, cake.getDescription());
            ps.setDouble(3, cake.getPrice());
            ps.setInt(4, cake.getStockQuantity());
            ps.setLong(5, cake.getCategoryId());
            ps.setString(6, cake.getImageUrl() != null ? cake.getImageUrl() : "");
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateCake(Cake cake) {
        String sql = "UPDATE cake SET name = ?, description = ?, price = ?, stock_quantity = ?, category_id = ? WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cake.getName());
            ps.setString(2, cake.getDescription());
            ps.setDouble(3, cake.getPrice());
            ps.setInt(4, cake.getStockQuantity());
            ps.setLong(5, cake.getCategoryId());
            ps.setLong(6, cake.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countLowStockCakes() {
        String sql = "SELECT COUNT(*) FROM cake WHERE stock_quantity > 0 AND stock_quantity < 10";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countOutOfStockCakes() {
        String sql = "SELECT COUNT(*) FROM cake WHERE stock_quantity = 0";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}