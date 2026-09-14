package com.resumeiqai.service;

public class ResumeSuggestionGenerator {

    public String generateSuggestions(
            String resumeText,
            String careerArea) {

        String text = resumeText.toLowerCase();

        StringBuilder suggestions =
                new StringBuilder();

        switch (careerArea) {

            case "Data Analytics":

                if (!text.contains("python")) {
                    suggestions.append(
                            "• Strengthen Python for data analysis.\n"
                    );
                }

                if (!text.contains("pandas")) {
                    suggestions.append(
                            "• Learn Pandas for data manipulation.\n"
                    );
                }

                if (!text.contains("sql")) {
                    suggestions.append(
                            "• Practice SQL and database querying.\n"
                    );
                }

                if (!text.contains("power bi")
                        && !text.contains("tableau")) {

                    suggestions.append(
                            "• Learn Power BI or Tableau for data visualization.\n"
                    );
                }

                if (!text.contains("statistics")) {
                    suggestions.append(
                            "• Strengthen statistics fundamentals.\n"
                    );
                }

                if (!text.contains("project")) {
                    suggestions.append(
                            "• Build practical data analytics projects.\n"
                    );
                }

                break;

            case "Java Backend Development":

                if (!text.contains("spring boot")) {
                    suggestions.append(
                            "• Learn Spring Boot for backend development.\n"
                    );
                }

                if (!text.contains("git")) {
                    suggestions.append(
                            "• Learn Git for version control.\n"
                    );
                }

                if (!text.contains("docker")) {
                    suggestions.append(
                            "• Learn Docker for application deployment.\n"
                    );
                }

                if (!text.contains("rest api")
                        && !text.contains("rest")) {

                    suggestions.append(
                            "• Practice building REST APIs.\n"
                    );
                }

                if (!text.contains("project")) {
                    suggestions.append(
                            "• Add practical Java backend projects.\n"
                    );
                }

                break;

            case "Frontend Development":

                if (!text.contains("javascript")) {
                    suggestions.append(
                            "• Strengthen JavaScript fundamentals.\n"
                    );
                }

                if (!text.contains("react")) {
                    suggestions.append(
                            "• Learn React for modern frontend development.\n"
                    );
                }

                if (!text.contains("responsive")) {
                    suggestions.append(
                            "• Practice responsive web design.\n"
                    );
                }

                if (!text.contains("project")) {
                    suggestions.append(
                            "• Build frontend projects for your portfolio.\n"
                    );
                }

                break;

            case "Python / AI":

                if (!text.contains("python")) {
                    suggestions.append(
                            "• Strengthen Python programming.\n"
                    );
                }

                if (!text.contains("pandas")) {
                    suggestions.append(
                            "• Learn Pandas for data processing.\n"
                    );
                }

                if (!text.contains("machine learning")) {
                    suggestions.append(
                            "• Learn machine learning fundamentals.\n"
                    );
                }

                if (!text.contains("project")) {
                    suggestions.append(
                            "• Build practical AI or Python projects.\n"
                    );
                }

                break;

            case "Database Development":

                if (!text.contains("sql")) {
                    suggestions.append(
                            "• Strengthen SQL querying skills.\n"
                    );
                }

                if (!text.contains("mysql")
                        && !text.contains("postgresql")) {

                    suggestions.append(
                            "• Practice with MySQL or PostgreSQL.\n"
                    );
                }

                if (!text.contains("database design")) {
                    suggestions.append(
                            "• Learn database design and normalization.\n"
                    );
                }

                if (!text.contains("project")) {
                    suggestions.append(
                            "• Build practical database projects.\n"
                    );
                }

                break;

            default:

                if (!text.contains("git")) {
                    suggestions.append(
                            "• Learn Git for version control.\n"
                    );
                }

                if (!text.contains("project")) {
                    suggestions.append(
                            "• Add practical projects to your resume.\n"
                    );
                }

                break;
        }

        // General suggestions

        if (!text.contains("certification")) {
            suggestions.append(
                    "• Consider adding relevant technical certifications.\n"
            );
        }

        return suggestions.toString();
    }
}