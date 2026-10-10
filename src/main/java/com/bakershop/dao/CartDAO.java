package com.bakershop.dao;

import com.bakershop.model.Cake;
import com.bakershop.model.Cart;
import com.bakershop.model.CartItem;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {


    public Cart getOrCreateCartByUserId(long userId) {
        String selectSql = "SELECT id FROM [cart] WHERE user_id = ?";
        String insertSql = "INSERT INTO [cart] (user_id) VALUES (?)";

        try (Connection conn = DBContext.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        long cartId = rs.getLong("id");
                        Cart cart = new Cart(cartId, userId);
                        cart.setItems(getCartItems(cartId, conn));
                        return cart;
                    }
                }
            }


            try (PreparedStatement psInsert = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                psInsert.setLong(1, userId);
                psInsert.executeUpdate();
                try (ResultSet generatedKeys = psInsert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long cartId = generatedKeys.getLong(1);
                        return new Cart(cartId, userId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CartItem> getCartItems(long cartId, Connection conn) throws Exception {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT ci.*, c.name, c.description, c.price, c.stock_quantity, c.image_url " +
                     "FROM [cart_items] ci " +
                     "JOIN [cake] c ON ci.cake_id = c.id " +
                     "WHERE ci.cart_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cartId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItem item = new CartItem();
                    item.setId(rs.getLong("id"));
                    item.setCartId(rs.getLong("cart_id"));
                    item.setCakeId(rs.getLong("cake_id"));
                    item.setSize(rs.getString("size"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setNote(rs.getString("note"));

                    Cake cake = new Cake();
                    cake.setId(rs.getLong("cake_id"));
                    cake.setName(rs.getString("name"));
                    cake.setDescription(rs.getString("description"));
                    cake.setPrice(rs.getDouble("price"));
                    cake.setStockQuantity(rs.getInt("stock_quantity"));
                    cake.setImageUrl(rs.getString("image_url"));

                    item.setCake(cake);
                    items.add(item);
                }
            }
        }
        return items;
    }

    public void addOrUpdateItem(long cartId, long cakeId, String size, int quantity, String note) {
        String checkSql = "SELECT id, quantity FROM [cart_items] WHERE cart_id = ? AND cake_id = ? AND size = ?";
        String updateSql = "UPDATE [cart_items] SET quantity = quantity + ?, note = ? WHERE id = ?";
        String insertSql = "INSERT INTO [cart_items] (cart_id, cake_id, size, quantity, note) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBContext.getConnection()) {
            try (PreparedStatement psCheck = conn.prepareStatement(checkSql)) {
                psCheck.setLong(1, cartId);
                psCheck.setLong(2, cakeId);
                psCheck.setString(3, size);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next()) {
                        long itemId = rs.getLong("id");
                        try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                            psUpdate.setInt(1, quantity);
                            psUpdate.setString(2, note);
                            psUpdate.setLong(3, itemId);
                            psUpdate.executeUpdate();
                        }
                        return;
                    }
                }
            }

            try (PreparedStatement psInsert = conn.prepareStatement(insertSql)) {
                psInsert.setLong(1, cartId);
                psInsert.setLong(2, cakeId);
                psInsert.setString(3, size);
                psInsert.setInt(4, quantity);
                psInsert.setString(5, note);
                psInsert.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCartItem(Long itemId, int quantity, String note, String size) {
    String sql = "UPDATE cart_items SET quantity = ?, note = ?, size = ? WHERE id = ?";
    try (Connection conn = DBContext.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, quantity);
        ps.setString(2, note);
        ps.setString(3, size);
        ps.setLong(4, itemId);
        
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void removeItem(long itemId) {
        String sql = "DELETE FROM [cart_items] WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, itemId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCart(long cartId) {
        String sql = "DELETE FROM [cart_items] WHERE cart_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, cartId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCartCount(long userId) {
    String sql = "SELECT SUM(ci.quantity) FROM [cart_items] ci " +
                 "JOIN [cart] c ON ci.cart_id = c.id WHERE c.user_id = ?";
    try (Connection conn = DBContext.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setLong(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); 
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
}