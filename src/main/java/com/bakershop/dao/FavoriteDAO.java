package com.bakershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bakershop.model.Cake;

public class FavoriteDAO {
    public List<Cake> getCakesForUser(long userId) throws SQLException {
        String sql = "SELECT DISTINCT c.id, c.name, c.description, c.price, c.stock_quantity, "
                + "c.category_id, c.image_url "
                + "FROM favorite f JOIN cake c ON c.id = f.cake_id "
                + "WHERE f.user_id = ? ORDER BY c.id DESC";
        List<Cake> cakes = new ArrayList<>();

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cake cake = new Cake();
                    cake.setId(resultSet.getLong("id"));
                    cake.setName(resultSet.getString("name"));
                    cake.setDescription(resultSet.getString("description"));
                    cake.setPrice(resultSet.getDouble("price"));
                    cake.setStockQuantity(resultSet.getInt("stock_quantity"));
                    cake.setCategoryId(resultSet.getLong("category_id"));
                    cake.setImageUrl(resultSet.getString("image_url"));
                    cake.setFavorited(true);
                    cakes.add(cake);
                }
            }
        } catch (Exception exception) {
            if (exception instanceof SQLException) {
                throw (SQLException) exception;
            }
            throw new SQLException("Unable to load favorite cakes", exception);
        }
        return cakes;
    }

    public Set<Long> getCakeIdsForUser(long userId) throws SQLException {
        String sql = "SELECT cake_id FROM favorite WHERE user_id = ?";
        Set<Long> cakeIds = new HashSet<>();

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cakeIds.add(resultSet.getLong("cake_id"));
                }
            }
        } catch (Exception exception) {
            if (exception instanceof SQLException) {
                throw (SQLException) exception;
            }
            throw new SQLException("Unable to load favorites", exception);
        }
        return cakeIds;
    }

    public boolean toggle(long userId, long cakeId) {
        String findSql = "SELECT id FROM favorite WITH (UPDLOCK, HOLDLOCK) WHERE user_id = ? AND cake_id = ?";
        String deleteSql = "DELETE FROM favorite WHERE user_id = ? AND cake_id = ?";
        String insertSql = "INSERT INTO favorite (user_id, cake_id) VALUES (?, ?)";

        try (Connection connection = DBContext.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            try {
                boolean exists;
                try (PreparedStatement findStatement = connection.prepareStatement(findSql)) {
                    findStatement.setLong(1, userId);
                    findStatement.setLong(2, cakeId);
                    try (ResultSet resultSet = findStatement.executeQuery()) {
                        exists = resultSet.next();
                    }
                }

                if (exists) {
                    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {
                        deleteStatement.setLong(1, userId);
                        deleteStatement.setLong(2, cakeId);
                        deleteStatement.executeUpdate();
                    }
                } else {
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setLong(1, userId);
                        insertStatement.setLong(2, cakeId);
                        insertStatement.executeUpdate();
                    }
                }
                connection.commit();
                return !exists;
            } catch (Exception exception) {
                connection.rollback();
                throw exception;
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Unable to update favorite", exception);
        }
    }
}
