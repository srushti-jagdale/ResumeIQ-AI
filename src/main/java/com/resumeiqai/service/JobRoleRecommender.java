package com.resumeiqai.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JobRoleRecommender {

    private final JobMatchCalculator matchCalculator;

    public JobRoleRecommender() {
        matchCalculator = new JobMatchCalculator();
    }

    // Small class to store role name + match percentage
    private static class RoleMatch {

        String role;
        int match;

        RoleMatch(String role, int match) {
            this.role = role;
            this.match = match;
        }
    }

    public String recommendRoles(String resumeText) {

        List<RoleMatch> roleMatches =
                new ArrayList<>();

        // =====================================================
        // DATA ANALYST
        // =====================================================

        String[] dataAnalystSkills = {
                "Python",
                "Pandas",
                "SQL",
                "Power BI",
                "Excel",
                "Statistics",
                "Data Visualization"
        };

        int dataAnalystMatch =
                matchCalculator.calculateMatch(
                        resumeText,
                        dataAnalystSkills
                );

        if (dataAnalystMatch > 0) {

            roleMatches.add(
                    new RoleMatch(
                            "Data Analyst",
                            dataAnalystMatch
                    )
            );
        }

        // =====================================================
        // PYTHON DEVELOPER
        // =====================================================

        String[] pythonSkills = {
                "Python",
                "Git",
                "SQL",
                "Pandas",
                "NumPy",
                "REST API",
                "Machine Learning"
        };

        int pythonMatch =
                matchCalculator.calculateMatch(
                        resumeText,
                        pythonSkills
                );

        if (pythonMatch > 0) {

            roleMatches.add(
                    new RoleMatch(
                            "Python Developer",
                            pythonMatch
                    )
            );
        }

        // =====================================================
        // JAVA BACKEND DEVELOPER
        // =====================================================

        String[] javaSkills = {
                "Java",
                "Spring Boot",
                "MySQL",
                "SQL",
                "Git",
                "REST API",
                "Maven",
                "Docker"
        };

        int javaMatch =
                matchCalculator.calculateMatch(
                        resumeText,
                        javaSkills
                );

        if (javaMatch > 0) {

            roleMatches.add(
                    new RoleMatch(
                            "Java Backend Developer",
                            javaMatch
                    )
            );
        }
// =====================================================
// FULL STACK DEVELOPER
// =====================================================

        String[] fullStackSkills = {
                "Java",
                "Spring Boot",
                "REST API",
                "MySQL",
                "SQL",
                "HTML",
                "CSS",
                "JavaScript",
                "React",
                "Git",
                "Docker",
                "Maven"
        };

        int fullStackMatch =
                matchCalculator.calculateMatch(
                        resumeText,
                        fullStackSkills
                );

        if (fullStackMatch > 0) {

            roleMatches.add(
                    new RoleMatch(
                            "Full Stack Developer",
                            fullStackMatch
                    )
            );
        }
        // =====================================================
        // FRONTEND DEVELOPER
        // =====================================================

        String[] frontendSkills = {
                "HTML",
                "CSS",
                "JavaScript",
                "React",
                "Git",
                "Bootstrap",
                "Responsive Design"
        };

        int frontendMatch =
                matchCalculator.calculateMatch(
                        resumeText,
                        frontendSkills
                );

        if (frontendMatch > 0) {

            roleMatches.add(
                    new RoleMatch(
                            "Frontend Developer",
                            frontendMatch
                    )
            );
        }

        // =====================================================
        // DATABASE DEVELOPER
        // =====================================================

        String[] databaseSkills = {
                "SQL",
                "MySQL",
                "PostgreSQL",
                "Database Design",
                "JDBC",
                "Database Management",
                "Stored Procedures"
        };

        int databaseMatch =
                matchCalculator.calculateMatch(
                        resumeText,
                        databaseSkills
                );

        if (databaseMatch > 0) {

            roleMatches.add(
                    new RoleMatch(
                            "Database Developer",
                            databaseMatch
                    )
            );
        }

        // =====================================================
        // SORT ROLES BY MATCH PERCENTAGE
        // Highest match first
        // =====================================================

        roleMatches.sort(
                Comparator
                        .comparingInt(
                                (RoleMatch role) -> role.match
                        )
                        .reversed()
                        .thenComparingInt(
                                role -> {
                                    if (role.role.equals("Full Stack Developer")) {
                                        return 1;
                                    }

                                    if (role.role.equals("Java Backend Developer")) {
                                        return 2;
                                    }

                                    if (role.role.equals("Frontend Developer")) {
                                        return 3;
                                    }

                                    return 4;
                                }
                        )
        );

        // =====================================================
        // BUILD RESULT
        // =====================================================

        StringBuilder roles =
                new StringBuilder();

        if (roleMatches.isEmpty()) {

            roles.append(
                    "No suitable job role found.\n"
            );

            return roles.toString();
        }



        int position = 1;

        for (RoleMatch role : roleMatches) {

            String medal;

            if (position == 1) {
                medal = "🥇";
            } else if (position == 2) {
                medal = "🥈";
            } else if (position == 3) {
                medal = "🥉";
            } else {
                medal = "💼";
            }

            roles.append(medal)
                    .append(" ")
                    .append(role.role)
                    .append(" — ")
                    .append(role.match)
                    .append("% Match\n");

            position++;
        }

        return roles.toString();
    }
}