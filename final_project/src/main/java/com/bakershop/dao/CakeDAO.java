package com.bakershop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.bakershop.model.Cake;

public class CakeDAO {
    public List<Cake> getAllCakesWithThumbnail() {
        List<Cake> list = new ArrayList<>();
        String sql = "select c.id, c.name, c.price, i.image_url " + "from cake c " +"JOIN image i ON i.cake_id = c.id " + "WHERE i.is_thumbnail = 1";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cake c = new Cake();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getDouble("price"));
                c.setImageUrl(rs.getString("image_url"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
