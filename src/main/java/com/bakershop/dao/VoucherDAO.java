package com.bakershop.dao;

import com.bakershop.model.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
