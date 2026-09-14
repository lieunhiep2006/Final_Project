package com.bakershop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bakershop.model.Category;

public class CategoryDAO {
    public List<Category> categoryList() {
        List<Category> list = new ArrayList<>();

        String sql = "select * from category";
        
        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getLong("id"));
                    c.setName(rs.getString("name"));
                    c.setDescription(rs.getString("description"));
                    list.add(c);
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
