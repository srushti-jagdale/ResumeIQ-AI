package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardDAO {

    // =====================================================
    // TOTAL RESUMES
    // =====================================================

    public int getTotalResumes() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get total resumes."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // HIGHEST RESUME SCORE
    // =====================================================

    public int getHighestScore() {

        String sql =
                "SELECT MAX(resume_score) FROM resume_analysis";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get highest score."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // AVERAGE RESUME SCORE
    // =====================================================

    public double getAverageScore() {

        String sql =
                "SELECT AVG(resume_score) FROM resume_analysis";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getDouble(1);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get average score."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // MOST RECOMMENDED ROLE
    // =====================================================

    public String getTopRole() {

        String sql =
                "SELECT recommended_role, COUNT(*) AS total " +
                        "FROM resume_analysis " +
                        "GROUP BY recommended_role " +
                        "ORDER BY total DESC " +
                        "LIMIT 1";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getString(
                        "recommended_role"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get top role."
            );

            e.printStackTrace();
        }

        return "No Data";
    }

    // =====================================================
    // LOW RESUME SCORE COUNT (0 - 49)
    // =====================================================

    public int getLowScoreCount() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis " +
                        "WHERE resume_score BETWEEN 0 AND 49";

        return getCount(sql);
    }

    // =====================================================
    // MEDIUM RESUME SCORE COUNT (50 - 79)
    // =====================================================

    public int getMediumScoreCount() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis " +
                        "WHERE resume_score BETWEEN 50 AND 79";

        return getCount(sql);
    }

    // =====================================================
    // HIGH RESUME SCORE COUNT (80 - 100)
    // =====================================================

    public int getHighScoreCount() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis " +
                        "WHERE resume_score BETWEEN 80 AND 100";

        return getCount(sql);
    }

    // =====================================================
    // AVERAGE ATS SCORE
    // =====================================================

    public double getAverageATSScore() {

        String sql =
                "SELECT AVG(ats_score) FROM resume_analysis";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getDouble(1);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get average ATS score."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // HIGHEST ATS SCORE
    // =====================================================

    public int getHighestATSScore() {

        String sql =
                "SELECT MAX(ats_score) FROM resume_analysis";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get highest ATS score."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // LOW ATS SCORE COUNT (0 - 49)
    // =====================================================

    public int getLowATSScoreCount() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis " +
                        "WHERE ats_score BETWEEN 0 AND 49";

        return getCount(sql);
    }

    // =====================================================
    // MEDIUM ATS SCORE COUNT (50 - 79)
    // =====================================================

    public int getMediumATSScoreCount() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis " +
                        "WHERE ats_score BETWEEN 50 AND 79";

        return getCount(sql);
    }

    // =====================================================
    // HIGH ATS SCORE COUNT (80 - 100)
    // =====================================================

    public int getHighATSScoreCount() {

        String sql =
                "SELECT COUNT(*) FROM resume_analysis " +
                        "WHERE ats_score BETWEEN 80 AND 100";

        return getCount(sql);
    }

    // =====================================================
    // ROLE DISTRIBUTION
    // =====================================================

    public Map<String, Integer> getRoleDistribution() {

        Map<String, Integer> roleDistribution =
                new LinkedHashMap<>();

        String sql =
                "SELECT recommended_role, COUNT(*) AS total " +
                        "FROM resume_analysis " +
                        "WHERE recommended_role IS NOT NULL " +
                        "AND recommended_role <> '' " +
                        "GROUP BY recommended_role " +
                        "ORDER BY total DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            while (rs.next()) {

                String role =
                        rs.getString(
                                "recommended_role"
                        );

                int count =
                        rs.getInt(
                                "total"
                        );

                roleDistribution.put(
                        role,
                        count
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get role distribution."
            );

            e.printStackTrace();
        }

        return roleDistribution;
    }

    // =====================================================
    // REUSABLE COUNT METHOD
    // =====================================================

    private int getCount(String sql) {

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        statement.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            System.out.println(
                    "Failed to get count."
            );

            e.printStackTrace();
        }

        return 0;
    }
}