package com.resumeiqai.service;

public class ResumeFeedbackGenerator {

    public String generateFeedback(String resumeText) {

        StringBuilder feedback = new StringBuilder();

        if (resumeText == null) {
            resumeText = "";
        }

        String text = resumeText.toLowerCase();

        // =====================================================
        // DETECT CAREER PATH
        // =====================================================

        boolean javaPath =
                text.contains("java")
                        && (text.contains("spring boot")
                        || text.contains("backend")
                        || text.contains("rest api"));

        boolean dataPath =
                text.contains("python")
                        && (text.contains("pandas")
                        || text.contains("data analyst")
                        || text.contains("data analysis")
                        || text.contains("power bi")
                        || text.contains("data visualization"));

        boolean frontendPath =
                text.contains("html")
                        && text.contains("css")
                        && (text.contains("javascript")
                        || text.contains("react"));

        boolean fullStackPath =
                text.contains("java")
                        && text.contains("spring boot")
                        && text.contains("html")
                        && text.contains("css")
                        && text.contains("javascript");

        // =====================================================
        // HEADER
        // =====================================================

        feedback.append("\n==============================\n");
        feedback.append("      AI Career Feedback\n");
        feedback.append("==============================\n\n");

        // =====================================================
        // RESUME STRENGTHS
        // =====================================================

        feedback.append("💪 Resume Strengths:\n");

        if (javaPath) {

            feedback.append(
                    "✔ Strong Java backend foundation.\n"
            );

            if (text.contains("spring boot")) {
                feedback.append(
                        "✔ Spring Boot knowledge detected.\n"
                );
            }

            if (text.contains("rest api")) {
                feedback.append(
                        "✔ REST API experience detected.\n"
                );
            }

        } else if (dataPath) {

            feedback.append(
                    "✔ Strong Python and data analysis foundation.\n"
            );

            if (text.contains("pandas")) {
                feedback.append(
                        "✔ Pandas/data manipulation skills detected.\n"
                );
            }

            if (text.contains("sql")
                    || text.contains("mysql")) {

                feedback.append(
                        "✔ Database and SQL skills detected.\n"
                );
            }

            if (text.contains("power bi")) {

                feedback.append(
                        "✔ Data visualization/Power BI experience detected.\n"
                );
            }

        } else if (frontendPath) {

            feedback.append(
                    "✔ Strong frontend development foundation.\n"
            );

            if (text.contains("javascript")) {
                feedback.append(
                        "✔ JavaScript skills detected.\n"
                );
            }

            if (text.contains("react")) {
                feedback.append(
                        "✔ React development skills detected.\n"
                );
            }

        } else if (fullStackPath) {

            feedback.append(
                    "✔ Full-stack development skills detected.\n"
            );

        } else {

            if (text.contains("java")) {
                feedback.append(
                        "✔ Java knowledge detected.\n"
                );
            }

            if (text.contains("python")) {
                feedback.append(
                        "✔ Python knowledge detected.\n"
                );
            }

            if (text.contains("sql")
                    || text.contains("mysql")) {

                feedback.append(
                        "✔ Database skills detected.\n"
                );
            }

            if (text.contains("git")) {

                feedback.append(
                        "✔ Version control knowledge detected.\n"
                );
            }

        }

        feedback.append("\n");

        // =====================================================
        // RESUME WEAKNESSES
        // =====================================================

        feedback.append("⚠ Resume Weaknesses:\n");

        if (javaPath) {

            if (!text.contains("git")) {
                feedback.append(
                        "✘ Git not found.\n"
                );
            }

            if (!text.contains("docker")) {
                feedback.append(
                        "✘ Docker not found.\n"
                );
            }

            if (!text.contains("rest api")) {
                feedback.append(
                        "✘ REST API experience not found.\n"
                );
            }

        } else if (dataPath) {

            if (!text.contains("pandas")) {
                feedback.append(
                        "✘ Pandas not found.\n"
                );
            }

            if (!text.contains("power bi")) {
                feedback.append(
                        "✘ Power BI not found.\n"
                );
            }

            if (!text.contains("statistics")) {
                feedback.append(
                        "✘ Statistics knowledge not found.\n"
                );
            }

        } else if (frontendPath) {

            if (!text.contains("javascript")) {
                feedback.append(
                        "✘ JavaScript not found.\n"
                );
            }

            if (!text.contains("react")) {
                feedback.append(
                        "✘ React not found.\n"
                );
            }

        } else {

            if (!text.contains("git")) {
                feedback.append(
                        "✘ Git not found.\n"
                );
            }

            if (!text.contains("docker")) {
                feedback.append(
                        "✘ Docker not found.\n"
                );
            }

        }

        feedback.append("\n");

        // =====================================================
        // CAREER READINESS
        // =====================================================

        feedback.append("🚀 Career Readiness:\n");

        if (fullStackPath) {

            feedback.append(
                    "Intermediate Full Stack Java Developer.\n"
            );

            feedback.append(
                    "Suitable for internships and junior developer roles.\n"
            );

        } else if (javaPath) {

            feedback.append(
                    "Intermediate Java Backend Developer.\n"
            );

            feedback.append(
                    "Suitable for internships and junior backend roles.\n"
            );

        } else if (dataPath) {

            feedback.append(
                    "Intermediate Data Analytics candidate.\n"
            );

            feedback.append(
                    "Suitable for data analyst internships and entry-level roles.\n"
            );

        } else if (frontendPath) {

            feedback.append(
                    "Intermediate Frontend Developer candidate.\n"
            );

            feedback.append(
                    "Suitable for frontend internships and junior roles.\n"
            );

        } else {

            feedback.append(
                    "Beginner Level.\n"
            );

            feedback.append(
                    "Needs more practical projects and industry-relevant skills.\n"
            );
        }

        feedback.append("\n");

        // =====================================================
        // PERSONALIZED LEARNING ROADMAP
        // =====================================================

        feedback.append(
                "📚 Personalized Learning Roadmap:\n"
        );

        if (dataPath) {

            feedback.append(
                    "1. Strengthen Python and Pandas\n"
            );

            feedback.append(
                    "2. Practice advanced SQL and database querying\n"
            );

            feedback.append(
                    "3. Improve Power BI and data visualization skills\n"
            );

            feedback.append(
                    "4. Build 2–3 real-world data analytics projects\n"
            );

            feedback.append(
                    "5. Create a portfolio showcasing your analysis projects\n"
            );

            feedback.append(
                    "Estimated Time: 6–8 Weeks\n"
            );

        } else if (fullStackPath) {

            feedback.append(
                    "1. Strengthen Java and Spring Boot\n"
            );

            feedback.append(
                    "2. Build REST APIs\n"
            );

            feedback.append(
                    "3. Improve JavaScript and React\n"
            );

            feedback.append(
                    "4. Learn Git and Docker\n"
            );

            feedback.append(
                    "5. Build and deploy a full-stack project\n"
            );

            feedback.append(
                    "Estimated Time: 8–10 Weeks\n"
            );

        } else if (javaPath) {

            feedback.append(
                    "1. Strengthen Java fundamentals\n"
            );

            feedback.append(
                    "2. Build REST APIs using Spring Boot\n"
            );

            feedback.append(
                    "3. Practice MySQL and database integration\n"
            );

            feedback.append(
                    "4. Learn Git and Docker\n"
            );

            feedback.append(
                    "5. Build and deploy a Java backend project\n"
            );

            feedback.append(
                    "Estimated Time: 6–8 Weeks\n"
            );

        } else if (frontendPath) {

            feedback.append(
                    "1. Strengthen HTML and CSS\n"
            );

            feedback.append(
                    "2. Improve JavaScript fundamentals\n"
            );

            feedback.append(
                    "3. Learn React\n"
            );

            feedback.append(
                    "4. Build responsive frontend projects\n"
            );

            feedback.append(
                    "5. Create a frontend portfolio\n"
            );

            feedback.append(
                    "Estimated Time: 6–8 Weeks\n"
            );

        } else {

            feedback.append(
                    "1. Strengthen programming fundamentals\n"
            );

            feedback.append(
                    "2. Learn Git and version control\n"
            );

            feedback.append(
                    "3. Build practical projects\n"
            );

            feedback.append(
                    "4. Develop job-relevant technical skills\n"
            );

            feedback.append(
                    "5. Create a professional project portfolio\n"
            );

            feedback.append(
                    "Estimated Time: 8–10 Weeks\n"
            );
        }

        return feedback.toString();
    }
}