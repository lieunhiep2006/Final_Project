package com.bakershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bakershop.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreDAO {
    public List<Store> getStores() {
        List<Store> list = new ArrayList<>();
        String sql = "select * from store";

        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Store s = new Store();
                    s.setId(rs.getLong("id"));
                    s.setName(rs.getString("name"));
                    s.setAddress(rs.getString("address"));
                    s.setPhoneNumber(rs.getString("phone_number"));
                    s.setOpeningHours(rs.getString("opening_hours"));
                    s.setImageUrl(rs.getString("image_url"));
                    list.add(s);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
