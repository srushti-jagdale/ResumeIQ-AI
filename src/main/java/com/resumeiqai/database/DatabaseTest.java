package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {

    public static void main(String[] args) {

        System.out.println("===== DATABASE TEST =====");

        try {

            // Get database connection
            Connection connection =
                    DBConnection.getConnection();

            // Check connection
            if (connection == null) {

                System.out.println(
                        "❌ Database connection failed!"
                );

                return;
            }

            System.out.println(
                    "✅ Database connection successful!"
            );

            // Create statement
            Statement statement =
                    connection.createStatement();

            // Retrieve existing records
            String sql =
                    "SELECT * FROM resume_analysis";

            ResultSet result =
                    statement.executeQuery(sql);

            System.out.println(
                    "\n===== RESUME ANALYSIS RECORDS ====="
            );

            // Display records
            while (result.next()) {

                System.out.println(
                        "ID: "
                                + result.getInt("id")
                );

                System.out.println(
                        "File Name: "
                                + result.getString(
                                "file_name"
                        )
                );

                System.out.println(
                        "Resume Score: "
                                + result.getInt(
                                "resume_score"
                        )
                );

                System.out.println(
                        "Recommended Role: "
                                + result.getString(
                                "recommended_role"
                        )
                );

                System.out.println(
                        "Skills Found: "
                                + result.getString(
                                "skills_found"
                        )
                );

                System.out.println(
                        "Analysis Date: "
                                + result.getTimestamp(
                                "analysis_date"
                        )
                );

                System.out.println(
                        "--------------------------------"
                );
            }

            // Close resources
            result.close();
            statement.close();
            connection.close();

            System.out.println(
                    "\n✅ Database test completed successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "\n❌ Database test failed!"
            );

            e.printStackTrace();
        }
    }
}