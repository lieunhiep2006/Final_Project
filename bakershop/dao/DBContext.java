package com.bakershop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;

public class DBContext {

    private static final Properties properties = new Properties();

    static {
    try {
        InputStream input = DBContext.class
                .getClassLoader()
                .getResourceAsStream("db.properties");

        properties.load(input);

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        System.out.println("=== SQL SERVER DRIVER DA LOAD ===");

    } catch (Exception e) {
        System.out.println("=== LOI LOAD DRIVER ===");
        e.printStackTrace();
    }
}

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }
}