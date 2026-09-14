package com.resumeiqai.service;

public class SkillAnalyzer {

    public String[] getSkillsForCareer(String careerArea) {

        switch (careerArea) {

            case "Data Analytics":
                return new String[]{
                        "Python",
                        "Pandas",
                        "NumPy",
                        "SQL",
                        "Excel",
                        "Power BI",
                        "Tableau",
                        "Statistics",
                        "Data Visualization",
                        "Data Analysis"
                };

            case "Java Backend Development":
                return new String[]{
                        "Java",
                        "Spring Boot",
                        "SQL",
                        "MySQL",
                        "JDBC",
                        "REST API",
                        "Git",
                        "Docker",
                        "Maven",
                        "OOP"
                };

            case "Frontend Development":
                return new String[]{
                        "HTML",
                        "CSS",
                        "JavaScript",
                        "React",
                        "Git",
                        "Bootstrap",
                        "Responsive Design",
                        "REST API",
                        "UI Design",
                        "Web Development"
                };
            case "Full Stack Development":
                return new String[]{
                        "Java",
                        "Spring Boot",
                        "REST API",
                        "MySQL",
                        "SQL",
                        "JDBC",
                        "HTML",
                        "CSS",
                        "JavaScript",
                        "React",
                        "Git",
                        "Docker",
                        "Maven",
                        "Bootstrap",
                        "Responsive Design"
                };
            case "Python / AI":
                return new String[]{
                        "Python",
                        "NumPy",
                        "Pandas",
                        "Machine Learning",
                        "Scikit-learn",
                        "TensorFlow",
                        "SQL",
                        "Data Analysis",
                        "Git",
                        "Statistics"
                };

            case "Database Development":
                return new String[]{
                        "SQL",
                        "MySQL",
                        "PostgreSQL",
                        "Database Design",
                        "JDBC",
                        "Database Management",
                        "Git",
                        "Python",
                        "Data Modeling",
                        "Stored Procedures"
                };

            default:
                return new String[]{
                        "Java",
                        "Python",
                        "SQL",
                        "MySQL",
                        "Git",
                        "HTML",
                        "CSS",
                        "JavaScript",
                        "Docker",
                        "Spring Boot"
                };
        }
    }

    public String getSkillsFound(
            String resumeText,
            String[] skills) {

        StringBuilder result = new StringBuilder();

        String text = resumeText.toLowerCase();

        for (String skill : skills) {

            if (text.contains(skill.toLowerCase())) {

                result.append("✔ ")
                        .append(skill)
                        .append("\n");
            }
        }

        return result.toString();
    }

    public String getMissingSkills(
            String resumeText,
            String[] skills) {

        StringBuilder result = new StringBuilder();

        String text = resumeText.toLowerCase();

        for (String skill : skills) {

            if (!text.contains(skill.toLowerCase())) {

                result.append("✘ ")
                        .append(skill)
                        .append("\n");
            }
        }

        return result.toString();
    }

    public int countFoundSkills(
            String resumeText,
            String[] skills) {

        int count = 0;

        String text = resumeText.toLowerCase();

        for (String skill : skills) {

            if (text.contains(skill.toLowerCase())) {
                count++;
            }
        }

        return count;
    }
}