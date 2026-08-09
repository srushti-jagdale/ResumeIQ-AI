package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    // ==============================
    // Total Resumes
    // ==============================

    public int getTotalResumes() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM resume_analysis";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==============================
    // Highest Resume Score
    // ==============================

    public int getHighestScore() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT MAX(resume_score) FROM resume_analysis";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==============================
    // Average Resume Score
    // ==============================

    public double getAverageScore() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT AVG(resume_score) FROM resume_analysis";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getDouble(1);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==============================
    // Most Recommended Role
    // ==============================

    public String getTopRole() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql =
                    "SELECT recommended_role, COUNT(*) AS total " +
                            "FROM resume_analysis " +
                            "GROUP BY recommended_role " +
                            "ORDER BY total DESC " +
                            "LIMIT 1";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getString("recommended_role");

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "No Data";

    }

    // ==============================
    // Low Score Count (0 - 49)
    // ==============================

    public int getLowScoreCount() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM resume_analysis WHERE resume_score BETWEEN 0 AND 49";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==============================
    // Medium Score Count (50 - 79)
    // ==============================

    public int getMediumScoreCount() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM resume_analysis WHERE resume_score BETWEEN 50 AND 79";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==============================
    // High Score Count (80 - 100)
    // ==============================

    public int getHighScoreCount() {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM resume_analysis WHERE resume_score BETWEEN 80 AND 100";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

}