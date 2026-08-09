package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/resumeiqai";

    // MySQL Username
    private static final String USERNAME = "root";

    // MySQL Password
    // Replace YOUR_PASSWORD with your actual MySQL root password
    private static final String PASSWORD = "klrahul18";

    // Method to establish connection
    public static Connection getConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("✅ Database Connected Successfully!");

        } catch (SQLException e) {

            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();

        }

        return connection;
    }
}