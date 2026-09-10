package com.bakershop.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStream;
import java.util.Properties;

public class DBContext {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    static {
        try(InputStream input = DBContext.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            
            prop.load(input);
            driver = prop.getProperty("db.driver");
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

            Class.forName(driver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}
