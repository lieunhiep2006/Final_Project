package com.bakershop.dao;

import com.bakershop.model.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
	public List<Review> findByCakeId(int cakeId) {
		List<Review> reviews = new ArrayList<>();
		String sql = "SELECT r.id, r.user_id, r.cake_id, r.rating, r.comment, u.full_name "
				+ "FROM review r JOIN [user] u ON u.id = r.user_id "
				+ "WHERE r.cake_id = ? ORDER BY r.id DESC";
		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, cakeId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					reviews.add(map(result));
				}
			}
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to load reviews", exception);
		}
		return reviews;
	}

	public void create(Review review) {
		String sql = "INSERT INTO review (user_id, cake_id, rating, comment) VALUES (?, ?, ?, ?)";
		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, review.getUserId());
			statement.setInt(2, review.getCakeId());
			statement.setDouble(3, review.getRating());
			statement.setString(4, review.getComment());
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw new IllegalStateException("Unable to save review", exception);
		}
	}

	private Review map(ResultSet result) throws SQLException {
		Review review = new Review();
		review.setId(result.getLong("id"));
		review.setUserId(result.getLong("user_id"));
		review.setCakeId(result.getInt("cake_id"));
		review.setReviewerName(result.getString("full_name"));
		review.setRating(result.getDouble("rating"));
		review.setComment(result.getString("comment"));
		return review;
	}
}
