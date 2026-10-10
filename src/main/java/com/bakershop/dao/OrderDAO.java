package com.bakershop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakershop.model.CartItem;
import com.bakershop.model.Order;
import com.bakershop.model.OrderItem;
import com.bakershop.model.Voucher;

public class OrderDAO {
    public List<Order> findByUserId(Long userId) {
        return getListOrderById(userId);
    }

    public long createOrder(Long userId, int storeId, String address, String phone,
            List<CartItem> cartItems, Voucher voucher, double subtotal, double discount, double total) {
        String orderSql = "INSERT INTO [order] (user_id, voucher_id, store_id, delivery_address, delivery_phone, subtotal, discount_amount, shipping_fee, total_amount, status, payment_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'PENDING', 'UNPAID')";
        String itemSql = "INSERT INTO order_items (order_id, cake_id, size, quantity, price, note) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement orderStatement = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderStatement.setLong(1, userId);
                if (voucher == null || voucher.getId() == null) {
                    orderStatement.setNull(2, Types.BIGINT);
                } else {
                    orderStatement.setLong(2, voucher.getId());
                }
                orderStatement.setInt(3, storeId);
                orderStatement.setString(4, address);
                orderStatement.setString(5, phone);
                orderStatement.setDouble(6, subtotal);
                orderStatement.setDouble(7, discount);
                orderStatement.setDouble(8, 0);
                orderStatement.setDouble(9, total);
                orderStatement.executeUpdate();
                try (ResultSet keys = orderStatement.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Order ID was not generated");
                    }
                    long orderId = keys.getLong(1);
                    try (PreparedStatement itemStatement = conn.prepareStatement(itemSql)) {
                        for (CartItem item : cartItems) {
                            itemStatement.setLong(1, orderId);
                            itemStatement.setLong(2, item.getCakeId());
                            itemStatement.setString(3, item.getSize());
                            itemStatement.setInt(4, item.getQuantity());
                            itemStatement.setDouble(5, item.getCake().getPrice());
                            itemStatement.setString(6, item.getNote());
                            itemStatement.addBatch();
                        }
                        itemStatement.executeBatch();
                    }
                    conn.commit();
                    return orderId;
                }
            } catch (Exception exception) {
                conn.rollback();
                throw exception;
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to create order", exception);
        }
    }

    public List<Order> getListOrderById(Long userId) {
        String orderSql = "select top 100 o.id, o.status, o.payment_status, o.delivery_address, "
            + "o.total_amount, s.name as store_name, v.code as voucher_code "
            + "from [order] o "
            + "left join store s on o.store_id = s.id "
            + "left join voucher v on o.voucher_id = v.id "
            + "where o.user_id = ? "
            + "order by o.id desc";

        List<Order> list = new ArrayList<>();
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(orderSql)) {

            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getLong("id"));
                    o.setUserId(userId);
                    o.setStatus(rs.getString("status"));
                    o.setPaymentStatus(rs.getString("payment_status"));
                    o.setDeliveryAddress(rs.getString("delivery_address"));
                    o.setTotalAmount(rs.getDouble("total_amount"));
                    o.setStoreName(rs.getString("store_name"));
                    o.setVoucherCode(rs.getString("voucher_code"));
                    o.setItems(getItemsForOrder(conn, o.getId()));
                    list.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<OrderItem> getItemsForOrder(Connection conn, long orderId) throws SQLException {
        String itemSql = "select c.name as cake_name, oi.quantity, oi.price "
            + "from order_items oi "
            + "join cake c on oi.cake_id = c.id "
            + "where oi.order_id = ?";

        List<OrderItem> items = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(itemSql)) {
            ps.setLong(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setCakeName(rs.getString("cake_name"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getDouble("price"));
                    items.add(item);
                }
            }
        }
        return items;
    }

    public List<Order> getAllOrders() {
        String orderSql = "select o.id, o.user_id, o.status, o.payment_status, o.delivery_address, "
            + "o.total_amount, s.name as store_name, v.code as voucher_code, "
            + "u.full_name as customer_name "
            + "from [order] o "
            + "left join [user] u on u.id = o.user_id "
            + "left join store s on o.store_id = s.id "
            + "left join voucher v on o.voucher_id = v.id "
            + "order by o.id desc";

        List<Order> list = new ArrayList<>();
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(orderSql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getLong("id"));
                o.setUserId(rs.getLong("user_id"));
                o.setStatus(rs.getString("status"));
                o.setPaymentStatus(rs.getString("payment_status"));
                o.setDeliveryAddress(rs.getString("delivery_address"));
                o.setTotalAmount(rs.getDouble("total_amount"));
                o.setStoreName(rs.getString("store_name"));
                o.setVoucherCode(rs.getString("voucher_code"));
                o.setItems(getItemsForOrder(conn, o.getId()));
                o.setCustomerName(rs.getString("customer_name"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateStatus(int orderId, String status) {
        String sql = "UPDATE [order] SET status = ? WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countPendingOrders() {
        String sql = "SELECT COUNT(*) FROM [order] WHERE status = 'Pending'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countCompletedOrders() {
        String sql = "SELECT COUNT(*) FROM [order] WHERE status = 'Completed'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(total_amount) FROM [order] WHERE status != 'Cancelled'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getPaidRevenue() {
        String sql = "SELECT SUM(total_amount) FROM [order] WHERE payment_status = 'PAID'";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getRevenueByStatus(String status) {
        String sql = "SELECT SUM(total_amount) FROM [order] WHERE status = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}