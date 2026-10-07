package com.bakershop;

import com.bakershop.dao.DBContext;
import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection conn = DBContext.getConnection();

            if (conn != null) {
                System.out.println("KET NOI DATABASE THANH CONG!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
