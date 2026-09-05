package com.bakershop.dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DBContext {
    private static Properties props = new Properties();
    static {
        try (InputStream input = DBContext.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("db.properties not found in classpath");
            }
            props.load(input);
            Class.forName(props.getProperty("db.driver"));

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load DB configuration", e);
        }
    }

    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(props.getProperty("db.url"));
    }
}