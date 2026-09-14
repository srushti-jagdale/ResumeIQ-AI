package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResumeDAO {

    // =====================================================
    // SAVE RESUME ANALYSIS
    // =====================================================

    public boolean saveResumeAnalysis(
            String fileName,
            int score,
            int atsScore,
            String recommendedRole,
            String skillsFound) {

        String sql =
                "INSERT INTO resume_analysis " +
                        "(file_name, resume_score, ats_score, recommended_role, skills_found) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, fileName);
            statement.setInt(2, score);
            statement.setInt(3, atsScore);
            statement.setString(4, recommendedRole);
            statement.setString(5, skillsFound);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "❌ Failed to save resume analysis."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DELETE RESUME ANALYSIS
    // =====================================================

    public boolean deleteResumeAnalysis(int id) {

        String sql =
                "DELETE FROM resume_analysis WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "❌ Failed to delete resume analysis."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // SEARCH RESUME ANALYSIS
    // =====================================================

    public List<String[]> searchResumeAnalysis(
            String keyword) {

        List<String[]> results =
                new ArrayList<>();

        String sql =
                "SELECT id, file_name, resume_score, " +
                        "recommended_role, analysis_date " +
                        "FROM resume_analysis " +
                        "WHERE file_name LIKE ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    "%" + keyword + "%"
            );

            try (
                    ResultSet rs =
                            statement.executeQuery()
            ) {

                while (rs.next()) {

                    String[] row = {

                            String.valueOf(
                                    rs.getInt("id")
                            ),

                            rs.getString(
                                    "file_name"
                            ),

                            String.valueOf(
                                    rs.getInt(
                                            "resume_score"
                                    )
                            ),

                            rs.getString(
                                    "recommended_role"
                            ),

                            String.valueOf(
                                    rs.getTimestamp(
                                            "analysis_date"
                                    )
                            )
                    };

                    results.add(row);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "❌ Failed to search resume analysis."
            );

            e.printStackTrace();
        }

        return results;
    }

    // =====================================================
    // GET COMPLETE RESUME ANALYSIS BY ID
    // =====================================================

    public String[] getResumeAnalysisById(int id) {

        String sql =
                "SELECT id, file_name, resume_score, " +
                        "ats_score, recommended_role, " +
                        "skills_found, analysis_date " +
                        "FROM resume_analysis " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            try (
                    ResultSet rs =
                            statement.executeQuery()
            ) {

                if (rs.next()) {

                    return new String[]{

                            // 0 - ID
                            String.valueOf(
                                    rs.getInt("id")
                            ),

                            // 1 - File Name
                            rs.getString(
                                    "file_name"
                            ),

                            // 2 - Resume Score
                            String.valueOf(
                                    rs.getInt(
                                            "resume_score"
                                    )
                            ),

                            // 3 - ATS Score
                            String.valueOf(
                                    rs.getInt(
                                            "ats_score"
                                    )
                            ),

                            // 4 - Recommended Role
                            rs.getString(
                                    "recommended_role"
                            ),

                            // 5 - Skills Found
                            rs.getString(
                                    "skills_found"
                            ),

                            // 6 - Analysis Date
                            String.valueOf(
                                    rs.getTimestamp(
                                            "analysis_date"
                                    )
                            )
                    };
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "❌ Failed to retrieve resume analysis."
            );

            e.printStackTrace();
        }

        return null;
    }
}