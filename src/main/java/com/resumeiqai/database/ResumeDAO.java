package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResumeDAO {

    // Save Resume Analysis
    public boolean saveResumeAnalysis(String fileName,
                                      int score,
                                      String recommendedRole,
                                      String skillsFound) {

        String sql = "INSERT INTO resume_analysis(file_name, resume_score, recommended_role, skills_found) VALUES (?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, fileName);
            statement.setInt(2, score);
            statement.setString(3, recommendedRole);
            statement.setString(4, skillsFound);

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    // Delete Resume Analysis
    public boolean deleteResumeAnalysis(int id) {

        String sql = "DELETE FROM resume_analysis WHERE id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            statement.close();
            connection.close();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    // Search Resume Analysis
    public ResultSet searchResumeAnalysis(String keyword) {

        try {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT * FROM resume_analysis WHERE file_name LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + keyword + "%");

            return statement.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }
}