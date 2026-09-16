package com.bakershop.dao;

import com.bakershop.model.CartItem;
import com.bakershop.model.Order;
import com.bakershop.model.OrderItem;
import com.bakershop.model.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderDAO {
	public List<Order> findByUserId(long userId) {
		Map<Long, Order> orders = new LinkedHashMap<>();
		String sql = "SELECT o.id, o.user_id, o.delivery_address, o.delivery_phone, o.delivery_time, "
				+ "o.subtotal, o.discount_amount, o.shipping_fee, o.total_amount, o.status, o.payment_status, "
				+ "s.name AS store_name, v.code AS voucher_code, "
				+ "oi.id AS item_id, oi.cake_id, c.name AS cake_name, oi.size, oi.quantity, oi.price, oi.note "
				+ "FROM [order] o JOIN store s ON s.id = o.store_id "
				+ "LEFT JOIN voucher v ON v.id = o.voucher_id "
				+ "LEFT JOIN order_items oi ON oi.order_id = o.id "
				+ "LEFT JOIN cake c ON c.id = oi.cake_id "
				+ "WHERE o.user_id = ? ORDER BY o.id DESC, oi.id ASC";
		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, userId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					long orderId = result.getLong("id");
					Order order = orders.get(orderId);
					if (order == null) {
						order = mapOrder(result);
						orders.put(orderId, order);
					}
					if (result.getObject("item_id") != null) {
						order.getItems().add(mapItem(result));
					}
				}
			}
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to load order history", exception);
		}
		return new ArrayList<>(orders.values());
	}

	private Order mapOrder(ResultSet result) throws SQLException {
		Order order = new Order();
		order.setId(result.getLong("id"));
		order.setUserId(result.getLong("user_id"));
		order.setDeliveryAddress(result.getString("delivery_address"));
		order.setDeliveryPhone(result.getString("delivery_phone"));
		order.setDeliveryTime(result.getTimestamp("delivery_time"));
		order.setSubtotal(result.getDouble("subtotal"));
		order.setDiscountAmount(result.getDouble("discount_amount"));
		order.setShippingFee(result.getDouble("shipping_fee"));
		order.setTotalAmount(result.getDouble("total_amount"));
		order.setStatus(result.getString("status"));
		order.setPaymentStatus(result.getString("payment_status"));
		order.setStoreName(result.getString("store_name"));
		order.setVoucherCode(result.getString("voucher_code"));
		return order;
	}

	private OrderItem mapItem(ResultSet result) throws SQLException {
		OrderItem item = new OrderItem();
		item.setId(result.getLong("item_id"));
		item.setCakeId(result.getInt("cake_id"));
		item.setCakeName(result.getString("cake_name"));
		item.setSize(result.getString("size"));
		item.setQuantity(result.getInt("quantity"));
		item.setPrice(result.getDouble("price"));
		item.setNote(result.getString("note"));
		return item;
	}

	public long createOrder(int userId, int storeId, String address, String phone,
			List<CartItem> cartItems, Voucher voucher, double subtotal,
			double discount, double total) {
		String orderSql = "insert into [order] (user_id, voucher_id, store_id, delivery_address, "
				+ "delivery_phone, subtotal, discount_amount, shipping_fee, total_amount, status, payment_status) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, 'PENDING', 'UNPAID')";
		String itemSql = "INSERT INTO order_items (order_id, cake_id, size, quantity, price, note) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection connection = DBContext.getConnection()) {
			connection.setAutoCommit(false);
			try (PreparedStatement orderStatement = connection.prepareStatement(orderSql, new String[] { "id" });
					PreparedStatement itemStatement = connection.prepareStatement(itemSql)) {
				orderStatement.setInt(1, userId);
				if (voucher == null) orderStatement.setNull(2, java.sql.Types.BIGINT);
				else orderStatement.setLong(2, voucher.getId());
				orderStatement.setInt(3, storeId);
				orderStatement.setString(4, address);
				orderStatement.setString(5, phone);
				orderStatement.setDouble(6, subtotal);
				orderStatement.setDouble(7, discount);
				orderStatement.setDouble(8, 0);
				orderStatement.setDouble(9, total);
				orderStatement.executeUpdate();
				long orderId;
				try (ResultSet keys = orderStatement.getGeneratedKeys()) {
					if (!keys.next()) throw new SQLException("Order id was not generated");
					orderId = keys.getLong(1);
				}
				for (CartItem item : cartItems) {
					itemStatement.setLong(1, orderId);
					itemStatement.setInt(2, item.getCake().getId());
					itemStatement.setString(3, "STANDARD");
					itemStatement.setInt(4, item.getQuantity());
					itemStatement.setDouble(5, item.getCake().getPrice());
					itemStatement.setNull(6, java.sql.Types.NVARCHAR);
					itemStatement.addBatch();
				}
				itemStatement.executeBatch();
				connection.commit();
				return orderId;
			} catch (SQLException exception) {
				connection.rollback();
				throw exception;
			}
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to create order", exception);
		}
	}
}
