package com.resumeiqai.service;

public class ResumeFeedbackGenerator {

    public String generateFeedback(String resumeText) {

        StringBuilder feedback = new StringBuilder();

        feedback.append("\n==============================\n");
        feedback.append("      AI Career Feedback\n");
        feedback.append("==============================\n\n");

        // Strengths
        feedback.append("💪 Resume Strengths:\n");

        if (resumeText.toLowerCase().contains("java")) {
            feedback.append("✔ Strong Java knowledge.\n");
        }

        if (resumeText.toLowerCase().contains("spring boot")) {
            feedback.append("✔ Good Spring Boot foundation.\n");
        }

        if (resumeText.toLowerCase().contains("mysql")
                || resumeText.toLowerCase().contains("sql")) {
            feedback.append("✔ Database skills detected.\n");
        }

        feedback.append("\n");

        // Weaknesses
        feedback.append("⚠ Resume Weaknesses:\n");

        if (!resumeText.toLowerCase().contains("git")) {
            feedback.append("✘ Git not found.\n");
        }

        if (!resumeText.toLowerCase().contains("docker")) {
            feedback.append("✘ Docker not found.\n");
        }

        if (!resumeText.toLowerCase().contains("react")) {
            feedback.append("✘ React not found.\n");
        }

        if (!resumeText.toLowerCase().contains("python")) {
            feedback.append("✘ Python not found.\n");
        }

        feedback.append("\n");

        // Career Readiness
        feedback.append("🚀 Career Readiness:\n");

        if (resumeText.toLowerCase().contains("java")
                && resumeText.toLowerCase().contains("spring boot")) {

            feedback.append("Intermediate Java Backend Developer.\n");
            feedback.append("Ready for internships.\n");

        } else {

            feedback.append("Beginner Level.\n");
            feedback.append("Needs more practical projects.\n");

        }

        feedback.append("\n");

        // Learning Roadmap
        feedback.append("📚 Personalized Learning Roadmap:\n");
        feedback.append("1. Learn Git\n");
        feedback.append("2. Learn Docker\n");
        feedback.append("3. Build REST APIs using Spring Boot\n");
        feedback.append("4. Deploy your project\n");
        feedback.append("Estimated Time: 6–8 Weeks\n");

        return feedback.toString();
    }
}