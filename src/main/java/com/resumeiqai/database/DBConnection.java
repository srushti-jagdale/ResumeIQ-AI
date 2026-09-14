package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // ==============================
    // Database Configuration
    // ==============================

    private static final String URL =
            "jdbc:mysql://localhost:3306/resumeiqai";

    private static final String USERNAME =
            "root";

    private static final String PASSWORD =
            "klrahul18";

    // ==============================
    // Establish Database Connection
    // ==============================

    public static Connection getConnection() {

        try {

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USERNAME,
                            PASSWORD
                    );

            System.out.println(
                    "✅ Database Connected Successfully!"
            );

            return connection;

        } catch (SQLException e) {

            System.out.println(
                    "❌ Database Connection Failed!"
            );

            e.printStackTrace();

            return null;
        }
    }
}