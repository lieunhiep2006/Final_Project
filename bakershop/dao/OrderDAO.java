package com.bakershop.dao;

import com.bakershop.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> getAllOrders() {

    List<Order> list = new ArrayList<>();

    String sql =
            "SELECT o.id, o.user_id, " +
            "u.full_name AS customer_name, " +
            "o.voucher_id, o.store_id, " +
            "o.delivery_address, o.delivery_phone, o.delivery_time, " +
            "o.subtotal, o.discount_amount, o.shipping_fee, o.total_amount, " +
            "o.status, o.payment_status " +
            "FROM [order] o " +
            "LEFT JOIN [user] u ON o.user_id = u.id " +
            "ORDER BY o.id DESC";

    try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {

            Order order = new Order();

            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));

            order.setCustomerName(
                    rs.getString("customer_name")
            );

            int voucherId = rs.getInt("voucher_id");
            if (!rs.wasNull()) {
                order.setVoucherId(voucherId);
            }

            int storeId = rs.getInt("store_id");
            if (!rs.wasNull()) {
                order.setStoreId(storeId);
            }

            order.setDeliveryAddress(
                    rs.getString("delivery_address")
            );

            order.setDeliveryPhone(
                    rs.getString("delivery_phone")
            );

            order.setDeliveryTime(
                    rs.getTimestamp("delivery_time")
            );

            order.setSubtotal(
                    rs.getDouble("subtotal")
            );

            order.setDiscountAmount(
                    rs.getDouble("discount_amount")
            );

            order.setShippingFee(
                    rs.getDouble("shipping_fee")
            );

            order.setTotalAmount(
                    rs.getDouble("total_amount")
            );

            order.setStatus(
                    rs.getString("status")
            );

            order.setPaymentStatus(
                    rs.getString("payment_status")
            );

            list.add(order);
        }

    } catch (Exception e) {

        System.out.println(
                "=== LOI KHI LAY DANH SACH ORDER ==="
        );

        e.printStackTrace();
    }

    return list;
}


    public boolean updateStatus(int id, String status) {

        String sql =
                "UPDATE [order] " +
                "SET status = ? " +
                "WHERE id = ?";

        try (
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    public int countPendingOrders() {

        String sql = "SELECT COUNT(*) FROM [order] WHERE status = 'Pending'";

        try {
                Connection conn = DBContext.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

        if (rs.next()) {
                return rs.getInt(1);
        }
        } catch (Exception e) {
                e.printStackTrace();
        }

        return 0;
        }

        public double getTotalRevenue() {

    String sql =
            "SELECT ISNULL(SUM(total_amount), 0) " +
            "FROM [order]";

    try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            return rs.getDouble(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

public double getPaidRevenue() {

    String sql =
            "SELECT ISNULL(SUM(total_amount), 0) " +
            "FROM [order] " +
            "WHERE payment_status = 'Paid'";

    try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            return rs.getDouble(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

public int countCompletedOrders() {

    String sql =
            "SELECT COUNT(*) " +
            "FROM [order] " +
            "WHERE status = 'Completed'";

    try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
    ) {

        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

public double getRevenueByStatus(String status) {

    String sql =
            "SELECT ISNULL(SUM(total_amount), 0) " +
            "FROM [order] " +
            "WHERE status = ?";

    try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setString(1, status);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}
}