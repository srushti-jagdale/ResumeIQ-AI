package com.resumeiqai.service;

public class ResumeAnalyzer {

    private final SkillAnalyzer skillAnalyzer;
    private final ResumeScorer resumeScorer;
    private final JobRoleRecommender jobRoleRecommender;
    private final ResumeSuggestionGenerator suggestionGenerator;
    private final ResumeFeedbackGenerator feedbackGenerator;

    public ResumeAnalyzer() {

        skillAnalyzer = new SkillAnalyzer();
        resumeScorer = new ResumeScorer();
        jobRoleRecommender = new JobRoleRecommender();
        suggestionGenerator = new ResumeSuggestionGenerator();
        feedbackGenerator = new ResumeFeedbackGenerator();
    }

    public String analyzeResume(String resumeText) {

        // =====================================================
        // SAFETY CHECK
        // =====================================================

        if (resumeText == null) {
            resumeText = "";
        }

        String text = resumeText.toLowerCase();

        // =====================================================
        // DETECT CAREER AREA
        // =====================================================

        String careerArea = detectCareerArea(text);

        // =====================================================
        // ANALYZE SKILLS
        // =====================================================

        String[] skills =
                skillAnalyzer.getSkillsForCareer(careerArea);

        int foundSkills =
                skillAnalyzer.countFoundSkills(
                        resumeText,
                        skills
                );

        String skillsFound =
                skillAnalyzer.getSkillsFound(
                        resumeText,
                        skills
                );

        String missingSkills =
                skillAnalyzer.getMissingSkills(
                        resumeText,
                        skills
                );

        // =====================================================
        // CALCULATE SCORE
        // =====================================================

        int skillScore =
                resumeScorer.calculateSkillScore(
                        resumeText,
                        skills
                );

        int educationScore =
                resumeScorer.calculateEducationScore(text);

        int projectScore =
                resumeScorer.calculateProjectScore(text);

        int experienceScore =
                resumeScorer.calculateExperienceScore(text);

        int certificationScore =
                resumeScorer.calculateCertificationScore(text);

        int contactScore =
                resumeScorer.calculateContactScore(text);

        int structureScore =
                resumeScorer.calculateStructureScore(text);

        int resumeScore =
                resumeScorer.calculateFinalScore(
                        skillScore,
                        educationScore,
                        projectScore,
                        experienceScore,
                        certificationScore,
                        contactScore,
                        structureScore
                );

        // =====================================================
        // JOB RECOMMENDATIONS
        // =====================================================

        String recommendedRoles =
                jobRoleRecommender.recommendRoles(
                        resumeText
                );

        // =====================================================
        // SUGGESTIONS
        // =====================================================

        String suggestions =
                suggestionGenerator.generateSuggestions(
                        resumeText,
                        careerArea
                );

        // =====================================================
        // AI CAREER FEEDBACK
        // =====================================================

        String feedback =
                feedbackGenerator.generateFeedback(
                        resumeText
                );

        // =====================================================
        // BUILD REPORT
        // =====================================================

        StringBuilder report =
                new StringBuilder();

        report.append(
                "===== Resume Analysis =====\n\n"
        );

        report.append(
                        "Career Area : "
                )
                .append(careerArea)
                .append("\n\n");

        report.append(
                        "Resume Score : "
                )
                .append(resumeScore)
                .append("/100\n\n");

        // =====================================================
        // SCORE BREAKDOWN
        // =====================================================

        report.append(
                "Score Breakdown:\n"
        );

        report.append(
                        "Technical Skills : "
                )
                .append(skillScore)
                .append("/50\n");

        report.append(
                        "Education : "
                )
                .append(educationScore)
                .append("/15\n");

        report.append(
                        "Projects : "
                )
                .append(projectScore)
                .append("/10\n");

        report.append(
                        "Experience / Internship : "
                )
                .append(experienceScore)
                .append("/10\n");

        report.append(
                        "Certifications : "
                )
                .append(certificationScore)
                .append("/5\n");

        report.append(
                        "Contact Information : "
                )
                .append(contactScore)
                .append("/5\n");

        report.append(
                        "Resume Structure : "
                )
                .append(structureScore)
                .append("/5\n\n");

        // =====================================================
        // SKILLS
        // =====================================================

        report.append(
                "Skills Found:\n"
        );

        report.append(skillsFound);

        report.append(
                "\nMissing Skills:\n"
        );

        report.append(missingSkills);

        // =====================================================
        // JOB ROLES
        // =====================================================

        report.append(
                "\nRecommended Job Roles:\n"
        );

        if (recommendedRoles.isEmpty()) {

            report.append(
                    "No suitable job role found.\n"
            );

        } else {

            report.append(recommendedRoles);
        }

        // =====================================================
        // SUGGESTIONS
        // =====================================================

        report.append(
                "\nSuggestions:\n"
        );

        if (suggestions.isEmpty()) {

            report.append(
                    "• Your resume contains most of the recommended skills and sections.\n"
            );

        } else {

            report.append(suggestions);
        }

        // =====================================================
        // AI FEEDBACK
        // =====================================================

        report.append(feedback);

        return report.toString();
    }

    // =========================================================
    // CAREER DETECTION
    // =========================================================

    private String detectCareerArea(String text) {

        int dataAnalyticsScore = 0;
        int javaScore = 0;
        int frontendScore = 0;
        int pythonAIScore = 0;
        int databaseScore = 0;

        // -----------------------------------------------------
        // DATA ANALYTICS
        // -----------------------------------------------------

        if (text.contains("data analyst")) {
            dataAnalyticsScore += 5;
        }

        if (text.contains("data analytics")) {
            dataAnalyticsScore += 5;
        }

        if (text.contains("pandas")) {
            dataAnalyticsScore += 3;
        }

        if (text.contains("power bi")) {
            dataAnalyticsScore += 3;
        }

        if (text.contains("tableau")) {
            dataAnalyticsScore += 3;
        }

        if (text.contains("data visualization")) {
            dataAnalyticsScore += 3;
        }

        if (text.contains("statistics")) {
            dataAnalyticsScore += 2;
        }

        if (text.contains("excel")) {
            dataAnalyticsScore += 2;
        }

        // -----------------------------------------------------
        // JAVA BACKEND
        // -----------------------------------------------------

        if (text.contains("java")) {
            javaScore += 4;
        }

        if (text.contains("spring boot")) {
            javaScore += 5;
        }

        if (text.contains("jdbc")) {
            javaScore += 3;
        }

        if (text.contains("rest api")
                || text.contains("restful api")) {

            javaScore += 3;
        }

        if (text.contains("maven")) {
            javaScore += 2;
        }

        // -----------------------------------------------------
        // FRONTEND
        // -----------------------------------------------------

        if (text.contains("html")) {
            frontendScore += 3;
        }

        if (text.contains("css")) {
            frontendScore += 3;
        }

        if (text.contains("javascript")) {
            frontendScore += 4;
        }

        if (text.contains("react")) {
            frontendScore += 5;
        }

        if (text.contains("frontend")) {
            frontendScore += 4;
        }

        if (text.contains("web development")) {
            frontendScore += 3;
        }

        // -----------------------------------------------------
        // PYTHON / AI
        // -----------------------------------------------------

        if (text.contains("python")) {
            pythonAIScore += 4;
        }

        if (text.contains("machine learning")) {
            pythonAIScore += 5;
        }

        if (text.contains("artificial intelligence")
                || text.contains(" ai ")) {

            pythonAIScore += 3;
        }

        if (text.contains("tensorflow")) {
            pythonAIScore += 4;
        }

        if (text.contains("scikit-learn")
                || text.contains("sklearn")) {

            pythonAIScore += 4;
        }

        if (text.contains("numpy")) {
            pythonAIScore += 2;
        }

        // -----------------------------------------------------
        // DATABASE
        // -----------------------------------------------------

        if (text.contains("sql")) {
            databaseScore += 4;
        }

        if (text.contains("mysql")) {
            databaseScore += 4;
        }

        if (text.contains("postgresql")) {
            databaseScore += 4;
        }

        if (text.contains("database design")) {
            databaseScore += 4;
        }

        if (text.contains("database management")) {
            databaseScore += 4;
        }

        // =====================================================
        // FIND HIGHEST SCORE
        // =====================================================

        int highestScore =
                Math.max(
                        dataAnalyticsScore,
                        Math.max(
                                javaScore,
                                Math.max(
                                        frontendScore,
                                        Math.max(
                                                pythonAIScore,
                                                databaseScore
                                        )
                                )
                        )
                );

        // =====================================================
        // DEFAULT
        // =====================================================

        if (highestScore == 0) {
            return "General Software Development";
        }

        if (dataAnalyticsScore == highestScore) {
            return "Data Analytics";
        }

        if (javaScore == highestScore) {
            return "Java Backend Development";
        }

        if (frontendScore == highestScore) {
            return "Frontend Development";
        }

        if (pythonAIScore == highestScore) {
            return "Python / AI";
        }

        return "Database Development";
    }
}