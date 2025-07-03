package com.auction.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/auctiondb";
    private static final String USER = "root";  // Change if needed
    private static final String PASSWORD = "Abhi@2004";  // Change if needed
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database Connection Failed!");
        }
        return connection;
    }

//    public static void main(String[] args) {
//        getConnection();
//    }
}
