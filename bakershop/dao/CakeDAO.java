package com.bakershop.dao;

import com.bakershop.model.Cake;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CakeDAO {

    public List<Cake> getAllCakes() {

        List<Cake> list = new ArrayList<>();

        String sql = "SELECT id, name, description, price, "
                   + "stock_quantity, category_id "
                   + "FROM cake "
                   + "ORDER BY id";

        try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            System.out.println("=== KET NOI DATABASE THANH CONG ===");

            while (rs.next()) {

                Cake cake = new Cake();

                cake.setId(
                    rs.getInt("id")
                );

                cake.setName(
                    rs.getString("name")
                );

                cake.setDescription(
                    rs.getString("description")
                );

                cake.setPrice(
                    rs.getDouble("price")
                );

                cake.setStockQuantity(
                    rs.getInt("stock_quantity")
                );

                cake.setCategoryId(
                    rs.getInt("category_id")
                );

                list.add(cake);
            }

            System.out.println(
                "So luong cake lay duoc = " + list.size()
            );

        } catch (Exception e) {

            System.out.println(
                "=== LOI KHI LAY DANH SACH CAKE ==="
            );

            e.printStackTrace();
        }

        return list;
    }


    public Cake getCakeById(int id) {

        String sql = "SELECT id, name, description, price, "
                   + "stock_quantity, category_id "
                   + "FROM cake "
                   + "WHERE id = ?";

        try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Cake cake = new Cake();

                    cake.setId(
                        rs.getInt("id")
                    );

                    cake.setName(
                        rs.getString("name")
                    );

                    cake.setDescription(
                        rs.getString("description")
                    );

                    cake.setPrice(
                        rs.getDouble("price")
                    );

                    cake.setStockQuantity(
                        rs.getInt("stock_quantity")
                    );

                    cake.setCategoryId(
                        rs.getInt("category_id")
                    );

                    return cake;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    public boolean insertCake(Cake cake) {

    String sql =
            "INSERT INTO cake " +
            "(name, description, price, stock_quantity, category_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
    ) {

        ps.setString(1, cake.getName());
        ps.setString(2, cake.getDescription());
        ps.setDouble(3, cake.getPrice());
        ps.setInt(4, cake.getStockQuantity());
        ps.setInt(5, cake.getCategoryId());

        int result = ps.executeUpdate();

        System.out.println(
                "So dong INSERT = " + result
        );

        return result > 0;

    } catch (Exception e) {

        System.out.println(
                "=== LOI INSERT CAKE ==="
        );

        e.printStackTrace();

    }

    return false;
}



    public boolean updateCake(Cake cake) {

        String sql = "UPDATE cake SET "
                   + "name = ?, "
                   + "description = ?, "
                   + "price = ?, "
                   + "stock_quantity = ?, "
                   + "category_id = ? "
                   + "WHERE id = ?";

        try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(
                1,
                cake.getName()
            );

            ps.setString(
                2,
                cake.getDescription()
            );

            ps.setDouble(
                3,
                cake.getPrice()
            );

            ps.setInt(
                4,
                cake.getStockQuantity()
            );

            ps.setInt(
                5,
                cake.getCategoryId()
            );

            ps.setInt(
                6,
                cake.getId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    public boolean deleteCake(int id) {

        String sql = "DELETE FROM cake WHERE id = ?";

        try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    public List<Cake> searchCakes(String keyword) {
        List<Cake> list = new ArrayList<>();

        String sql = "SELECT id, name, description, price, "
                   + "stock_quantity, category_id "
                   + "FROM cake "
                   + "WHERE name LIKE ? OR description LIKE ? "
                   + "ORDER BY id";

        try (
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Cake cake = new Cake();

                    cake.setId(
                        rs.getInt("id")
                    );

                    cake.setName(
                        rs.getString("name")
                    );

                    cake.setDescription(
                        rs.getString("description")
                    );

                    cake.setPrice(
                        rs.getDouble("price")
                    );

                    cake.setStockQuantity(
                        rs.getInt("stock_quantity")
                    );

                    cake.setCategoryId(
                        rs.getInt("category_id")
                    );

                    list.add(cake);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public int countLowStockCakes() {
    String sql = "SELECT COUNT(*) FROM cake "
               + "WHERE stock_quantity > 0 AND stock_quantity <= 5";

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

    public int countOutOfStockCakes() {
    String sql = "SELECT COUNT(*) FROM cake "
               + "WHERE stock_quantity = 0";

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

}