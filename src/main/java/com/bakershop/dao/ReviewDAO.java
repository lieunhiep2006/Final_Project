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
		String sql = "select r.id, r.user_id, r.cake_id, r.rating, r.comment, r.image_url, u.full_name "
				+ "from review r join [user] u on u.id = r.user_id "
				+ "where r.cake_id = ? order by r.id DESC";
		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, cakeId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					reviews.add(map(result));
				}
			}
		} catch (Exception exception) {
			throw new IllegalStateException("Unable to load reviews", exception);
		}
		return reviews;
	}
    public List<Review> getAllReviews(String starFilter) {
		List<Review> reviews = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
			"SELECT r.id, r.user_id, r.cake_id, r.rating, r.comment, r.image_url, u.full_name " +
			"FROM review r JOIN [user] u ON u.id = r.user_id WHERE 1=1"
		);

		if (starFilter != null && !starFilter.isEmpty() && !starFilter.equals("all")) {
			sql.append(" AND r.rating = ?");
		}
		sql.append(" ORDER BY r.id DESC");

		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql.toString())) {
			
			if (starFilter != null && !starFilter.isEmpty() && !starFilter.equals("all")) {
				statement.setDouble(1, Double.parseDouble(starFilter));
			}

			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					reviews.add(map(result));
				}
			}
		} catch (Exception exception) {
			throw new IllegalStateException("Unable to load all reviews", exception);
		}
		return reviews;
	}

	public void deleteReview(Long id) {
		String sql = "DELETE FROM review WHERE id = ?";
		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Unable to delete review", exception);
		}
	}	
	public void create(Review review) {
		String sql = "INSERT INTO review (user_id, cake_id, rating, comment, image_url) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DBContext.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, review.getUserId());
			statement.setLong(2, review.getCakeId());
			statement.setDouble(3, review.getRating());
			statement.setString(4, review.getComment());
			statement.setString(5, review.getImageUrl());
			statement.executeUpdate();
		} catch (Exception exception) {
			throw new IllegalStateException("Unable to save review", exception);
		}
	}

	private Review map(ResultSet result) throws SQLException {
		Review review = new Review();
		review.setId(result.getLong("id"));
		review.setUserId(result.getLong("user_id"));
		review.setCakeId(result.getLong("cake_id"));
		review.setReviewerName(result.getString("full_name"));
		review.setRating(result.getDouble("rating"));
		review.setComment(result.getString("comment"));
		review.setImageUrl(result.getString("image_url"));
		return review;
	}
}
