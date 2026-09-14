package com.resumeiqai.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobMatchDAO {

    // =====================================================
    // CREATE TABLE IF IT DOES NOT EXIST
    // =====================================================

    private void createTableIfNotExists() {

        String sql =
                "CREATE TABLE IF NOT EXISTS job_match_history (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "resume_file VARCHAR(255) NOT NULL, " +
                        "job_title VARCHAR(255), " +
                        "match_score INT, " +
                        "matched_keywords TEXT, " +
                        "missing_keywords TEXT, " +
                        "recommendation TEXT, " +
                        "match_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ")";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.executeUpdate();

            System.out.println(
                    "Job Match History table is ready."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Failed to create Job Match History table."
            );

            e.printStackTrace();
        }
    }

    // =====================================================
    // SAVE JOB MATCH
    // =====================================================

    public boolean saveJobMatch(
            String resumeFile,
            String jobTitle,
            int matchScore,
            String matchedKeywords,
            String missingKeywords,
            String recommendation) {

        createTableIfNotExists();

        String sql =
                "INSERT INTO job_match_history " +
                        "(resume_file, job_title, match_score, " +
                        "matched_keywords, missing_keywords, recommendation) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    resumeFile
            );

            statement.setString(
                    2,
                    jobTitle
            );

            statement.setInt(
                    3,
                    matchScore
            );

            statement.setString(
                    4,
                    matchedKeywords
            );

            statement.setString(
                    5,
                    missingKeywords
            );

            statement.setString(
                    6,
                    recommendation
            );

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to save job match."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // GET ALL JOB MATCHES
    // =====================================================

    public List<JobMatchRecord> getAllJobMatches() {

        createTableIfNotExists();

        List<JobMatchRecord> matches =
                new ArrayList<>();

        String sql =
                "SELECT * FROM job_match_history " +
                        "ORDER BY match_date DESC";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                JobMatchRecord record =
                        new JobMatchRecord(
                                resultSet.getInt("id"),
                                resultSet.getString("resume_file"),
                                resultSet.getString("job_title"),
                                resultSet.getInt("match_score"),
                                resultSet.getString("matched_keywords"),
                                resultSet.getString("missing_keywords"),
                                resultSet.getString("recommendation"),
                                resultSet.getTimestamp("match_date")
                        );

                matches.add(record);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve job match history."
            );

            e.printStackTrace();
        }

        return matches;
    }

    // =====================================================
    // DELETE JOB MATCH
    // =====================================================

    public boolean deleteJobMatch(int id) {

        createTableIfNotExists();

        String sql =
                "DELETE FROM job_match_history " +
                        "WHERE id = ?";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    id
            );

            int rows =
                    statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to delete job match."
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // TOTAL JOB MATCHES
    // =====================================================

    public int getTotalJobMatches() {

        createTableIfNotExists();

        String sql =
                "SELECT COUNT(*) FROM job_match_history";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get total job matches."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // AVERAGE JOB MATCH SCORE
    // =====================================================

    public double getAverageJobMatchScore() {

        createTableIfNotExists();

        String sql =
                "SELECT AVG(match_score) FROM job_match_history";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            if (resultSet.next()) {

                return resultSet.getDouble(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get average job match score."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // HIGHEST JOB MATCH SCORE
    // =====================================================

    public int getHighestJobMatchScore() {

        createTableIfNotExists();

        String sql =
                "SELECT MAX(match_score) FROM job_match_history";

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get highest job match score."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // LOW JOB MATCH COUNT (0 - 49)
    // =====================================================

    public int getLowJobMatchCount() {

        createTableIfNotExists();

        String sql =
                "SELECT COUNT(*) FROM job_match_history " +
                        "WHERE match_score BETWEEN 0 AND 49";

        return getJobMatchCount(sql);
    }

    // =====================================================
    // MEDIUM JOB MATCH COUNT (50 - 79)
    // =====================================================

    public int getMediumJobMatchCount() {

        createTableIfNotExists();

        String sql =
                "SELECT COUNT(*) FROM job_match_history " +
                        "WHERE match_score BETWEEN 50 AND 79";

        return getJobMatchCount(sql);
    }

    // =====================================================
    // HIGH JOB MATCH COUNT (80 - 100)
    // =====================================================

    public int getHighJobMatchCount() {

        createTableIfNotExists();

        String sql =
                "SELECT COUNT(*) FROM job_match_history " +
                        "WHERE match_score BETWEEN 80 AND 100";

        return getJobMatchCount(sql);
    }

    // =====================================================
    // REUSABLE JOB MATCH COUNT METHOD
    // =====================================================

    private int getJobMatchCount(
            String sql) {

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to get job match count."
            );

            e.printStackTrace();
        }

        return 0;
    }

    // =====================================================
    // RESULT MODEL
    // =====================================================

    public static class JobMatchRecord {

        private final int id;

        private final String resumeFile;

        private final String jobTitle;

        private final int matchScore;

        private final String matchedKeywords;

        private final String missingKeywords;

        private final String recommendation;

        private final java.sql.Timestamp matchDate;

        public JobMatchRecord(
                int id,
                String resumeFile,
                String jobTitle,
                int matchScore,
                String matchedKeywords,
                String missingKeywords,
                String recommendation,
                java.sql.Timestamp matchDate) {

            this.id = id;

            this.resumeFile =
                    resumeFile;

            this.jobTitle =
                    jobTitle;

            this.matchScore =
                    matchScore;

            this.matchedKeywords =
                    matchedKeywords;

            this.missingKeywords =
                    missingKeywords;

            this.recommendation =
                    recommendation;

            this.matchDate =
                    matchDate;
        }

        public int getId() {

            return id;
        }

        public String getResumeFile() {

            return resumeFile;
        }

        public String getJobTitle() {

            return jobTitle;
        }

        public int getMatchScore() {

            return matchScore;
        }

        public String getMatchedKeywords() {

            return matchedKeywords;
        }

        public String getMissingKeywords() {

            return missingKeywords;
        }

        public String getRecommendation() {

            return recommendation;
        }

        public java.sql.Timestamp getMatchDate() {

            return matchDate;
        }
    }
}