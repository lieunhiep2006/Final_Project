package com.bakershop.dao;

import com.bakershop.model.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VoucherDAO {
    
    public Voucher findUsableByCode(String code, double subtotal) {
        String sql = "SELECT id, code, discount_type, discount_value, min_order_amount, "
                + "max_discount_amount, usage_limit, start_date, end_date "
                + "from voucher where upper(code) = upper(?) "
                + "and start_date <= cast(GETDATE() as date) "
                + "and end_date >= cast(GETDATE() as date) "
                + "and (min_order_amount IS NULL OR min_order_amount <= ?) "
                + "and (usage_limit IS NULL OR usage_limit > 0)";
        try (Connection connection = DBContext.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, code);
            statement.setDouble(2, subtotal);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }
                Voucher voucher = new Voucher();
                voucher.setId(result.getLong("id"));
                voucher.setCode(result.getString("code"));
                voucher.setDiscountType(result.getString("discount_type"));
                voucher.setDiscountValue(result.getDouble("discount_value"));
                voucher.setMinOrderAmount(result.getDouble("min_order_amount"));
                double maxDiscount = result.getDouble("max_discount_amount");
                voucher.setMaxDiscountAmount(result.wasNull() ? null : maxDiscount);
                int usageLimit = result.getInt("usage_limit");
                voucher.setUsageLimit(result.wasNull() ? null : usageLimit);
                voucher.setStartDate(result.getDate("start_date"));
                voucher.setEndDate(result.getDate("end_date"));
                return voucher;
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to load voucher", exception);
        }
    }

    public List<Voucher> getAllVouchers() {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT id, code, discount_type, discount_value, min_order_amount, "
                   + "max_discount_amount, usage_limit, start_date, end_date FROM voucher ORDER BY id DESC";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(result.getLong("id"));
                voucher.setCode(result.getString("code"));
                voucher.setDiscountType(result.getString("discount_type"));
                voucher.setDiscountValue(result.getDouble("discount_value"));
                voucher.setMinOrderAmount(result.getDouble("min_order_amount"));
                double maxDiscount = result.getDouble("max_discount_amount");
                voucher.setMaxDiscountAmount(result.wasNull() ? null : maxDiscount);
                int usageLimit = result.getInt("usage_limit");
                voucher.setUsageLimit(result.wasNull() ? null : usageLimit);
                voucher.setStartDate(result.getDate("start_date"));
                voucher.setEndDate(result.getDate("end_date"));
                list.add(voucher);
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to load voucher list", exception);
        }
        return list;
    }

    public boolean addVoucher(Voucher voucher) {
        String sql = "INSERT INTO voucher (code, discount_type, discount_value, min_order_amount, max_discount_amount, usage_limit, start_date, end_date) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, voucher.getCode());
            statement.setString(2, voucher.getDiscountType());
            statement.setDouble(3, voucher.getDiscountValue());
            
            if (voucher.getMinOrderAmount() != null) statement.setDouble(4, voucher.getMinOrderAmount());
            else statement.setNull(4, java.sql.Types.DOUBLE);

            if (voucher.getMaxDiscountAmount() != null) statement.setDouble(5, voucher.getMaxDiscountAmount());
            else statement.setNull(5, java.sql.Types.DOUBLE);

            if (voucher.getUsageLimit() != null) statement.setInt(6, voucher.getUsageLimit());
            else statement.setNull(6, java.sql.Types.INTEGER);

            statement.setDate(7, voucher.getStartDate());
            statement.setDate(8, voucher.getEndDate());

            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteVoucher(long id) {
        String sql = "DELETE FROM voucher WHERE id = ?";
        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}