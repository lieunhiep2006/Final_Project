package com.bakershop;

import com.bakershop.dao.CakeDAO;
import com.bakershop.model.Cake;
import java.util.List;

public class TestCakeDAO {

    public static void main(String[] args) {

        CakeDAO dao = new CakeDAO();

        List<Cake> cakes = dao.getAllCakes();

        System.out.println("So luong cake = " + cakes.size());

        for (Cake cake : cakes) {
            System.out.println(
                cake.getId() + " | " +
                cake.getName() + " | " +
                cake.getPrice() + " | " +
                cake.getStockQuantity()
            );
        }
    }
}