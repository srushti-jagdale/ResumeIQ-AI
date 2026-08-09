package com.resumeiqai.service;

public class ResumeAnalyzer {

    public String analyzeResume(String resumeText) {

        StringBuilder report = new StringBuilder();

        // =====================================================
        // SAFETY CHECK
        // =====================================================

        if (resumeText == null) {
            resumeText = "";
        }

        String text = resumeText.toLowerCase();

        // =====================================================
        // TECHNICAL SKILLS
        // Maximum = 50 points
        // =====================================================

        String[] skills = {
                "Docker",
                "Java",
                "MySQL",
                "HTML",
                "CSS",
                "Git",
                "Spring Boot",
                "JavaScript",
                "React",
                "Python",
                "SQL"
        };

        int foundSkills = 0;
        int totalSkills = skills.length;

        StringBuilder skillsFound =
                new StringBuilder();

        StringBuilder missingSkills =
                new StringBuilder();

        for (String skill : skills) {

            if (text.contains(skill.toLowerCase())) {

                skillsFound
                        .append("✔ ")
                        .append(skill)
                        .append("\n");

                foundSkills++;

            } else {

                missingSkills
                        .append("✘ ")
                        .append(skill)
                        .append("\n");
            }
        }

        /*
         * 11 skills = 50 points maximum.
         */

        int skillScore =
                (foundSkills * 50) / totalSkills;

        // =====================================================
        // EDUCATION
        // Maximum = 15 points
        // =====================================================

        int educationScore = 0;

        if (containsAny(
                text,
                "education",
                "bachelor",
                "b.tech",
                "b.e",
                "bsc",
                "b.sc",
                "bca",
                "mca",
                "m.tech",
                "mca",
                "diploma",
                "degree",
                "university",
                "college"
        )) {

            educationScore = 15;

        }

        // =====================================================
        // PROJECTS
        // Maximum = 10 points
        // =====================================================

        int projectScore = 0;

        if (containsAny(
                text,
                "project",
                "projects",
                "developed",
                "developing",
                "application",
                "system",
                "website"
        )) {

            projectScore = 10;

        }

        // =====================================================
        // EXPERIENCE / INTERNSHIP
        // Maximum = 10 points
        // =====================================================

        int experienceScore = 0;

        if (containsAny(
                text,
                "experience",
                "internship",
                "intern",
                "work experience",
                "employment",
                "worked",
                "developer intern",
                "software engineer"
        )) {

            experienceScore = 10;

        }

        // =====================================================
        // CERTIFICATIONS
        // Maximum = 5 points
        // =====================================================

        int certificationScore = 0;

        if (containsAny(
                text,
                "certification",
                "certified",
                "certificate",
                "course",
                "coursera",
                "udemy",
                "nptel"
        )) {

            certificationScore = 5;

        }

        // =====================================================
        // CONTACT INFORMATION
        // Maximum = 5 points
        // =====================================================

        int contactScore = 0;

        boolean hasEmail =
                text.matches(
                        "(?s).*\\b[\\w.%+-]+@[\\w.-]+\\.[a-z]{2,}\\b.*"
                );

        boolean hasPhone =
                text.matches(
                        "(?s).*\\b\\d{10}\\b.*"
                );

        if (hasEmail) {
            contactScore += 3;
        }

        if (hasPhone) {
            contactScore += 2;
        }

        // =====================================================
        // RESUME STRUCTURE
        // Maximum = 5 points
        // =====================================================

        int structureScore = 0;

        int sectionCount = 0;

        if (containsAny(
                text,
                "objective",
                "summary",
                "profile"
        )) {

            sectionCount++;

        }

        if (containsAny(
                text,
                "education"
        )) {

            sectionCount++;

        }

        if (containsAny(
                text,
                "skills",
                "technical skills"
        )) {

            sectionCount++;

        }

        if (containsAny(
                text,
                "projects"
        )) {

            sectionCount++;

        }

        if (containsAny(
                text,
                "experience",
                "internship"
        )) {

            sectionCount++;

        }

        if (sectionCount >= 4) {

            structureScore = 5;

        } else if (sectionCount >= 2) {

            structureScore = 3;

        } else if (sectionCount == 1) {

            structureScore = 1;

        }

        // =====================================================
        // FINAL SCORE
        // =====================================================

        int resumeScore =
                skillScore
                        + educationScore
                        + projectScore
                        + experienceScore
                        + certificationScore
                        + contactScore
                        + structureScore;

        // Make absolutely sure score stays between 0 and 100.

        if (resumeScore > 100) {
            resumeScore = 100;
        }

        if (resumeScore < 0) {
            resumeScore = 0;
        }

        // =====================================================
        // JOB ROLE RECOMMENDATIONS
        // =====================================================

        StringBuilder recommendedRoles =
                new StringBuilder();

        // Java Backend Developer

        if (text.contains("java")
                && text.contains("mysql")
                && text.contains("spring boot")) {

            recommendedRoles.append(
                    "💼 Java Backend Developer\n"
            );
        }

        // Frontend Developer

        if (text.contains("html")
                && text.contains("css")
                && text.contains("javascript")
                && text.contains("react")) {

            recommendedRoles.append(
                    "💼 Frontend Developer\n"
            );
        }

        // Full Stack Java Developer

        if (text.contains("java")
                && text.contains("spring boot")
                && text.contains("html")
                && text.contains("css")
                && text.contains("javascript")
                && text.contains("react")) {

            recommendedRoles.append(
                    "💼 Full Stack Java Developer\n"
            );
        }

        // Python Developer

        if (text.contains("python")) {

            recommendedRoles.append(
                    "💼 Python Developer\n"
            );
        }

        // Database Developer

        if (text.contains("mysql")
                && text.contains("sql")) {

            recommendedRoles.append(
                    "💼 Database Developer\n"
            );
        }

        // If no role found

        if (recommendedRoles.length() == 0) {

            recommendedRoles.append(
                    "No suitable job role found.\n"
            );
        }

        // =====================================================
        // SUGGESTIONS
        // =====================================================

        StringBuilder suggestions =
                new StringBuilder();

        if (!text.contains("docker")) {

            suggestions.append(
                    "• Learn Docker for Java deployment.\n"
            );
        }

        if (!text.contains("git")) {

            suggestions.append(
                    "• Learn Git for version control.\n"
            );
        }

        if (!text.contains("html")) {

            suggestions.append(
                    "• Learn HTML for web development.\n"
            );
        }

        if (!text.contains("css")) {

            suggestions.append(
                    "• Learn CSS to improve UI design.\n"
            );
        }

        if (!text.contains("javascript")) {

            suggestions.append(
                    "• Learn JavaScript for interactive websites.\n"
            );
        }

        if (!text.contains("react")) {

            suggestions.append(
                    "• Learn React for modern frontend development.\n"
            );
        }

        if (!text.contains("python")) {

            suggestions.append(
                    "• Learn Python for automation and AI.\n"
            );
        }

        if (!text.contains("spring boot")) {

            suggestions.append(
                    "• Learn Spring Boot for Java backend development.\n"
            );
        }

        if (!text.contains("project")) {

            suggestions.append(
                    "• Add practical projects to demonstrate your skills.\n"
            );
        }

        if (!text.contains("certification")) {

            suggestions.append(
                    "• Consider adding relevant technical certifications.\n"
            );
        }

        if (!hasEmail || !hasPhone) {

            suggestions.append(
                    "• Make sure your resume contains complete contact information.\n"
            );
        }

        // =====================================================
        // BUILD REPORT
        // =====================================================

        report.append(
                "===== Resume Analysis =====\n\n"
        );

        // IMPORTANT:
        // MainFrame expects this exact format.

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
        // SKILLS FOUND
        // =====================================================

        report.append(
                "Skills Found:\n"
        );

        report.append(
                skillsFound
        );

        // =====================================================
        // MISSING SKILLS
        // =====================================================

        report.append(
                "\nMissing Skills:\n"
        );

        report.append(
                missingSkills
        );

        // =====================================================
        // RECOMMENDED ROLES
        // =====================================================

        report.append(
                "\nRecommended Job Roles:\n"
        );

        report.append(
                recommendedRoles
        );

        // =====================================================
        // SUGGESTIONS
        // =====================================================

        report.append(
                "\nSuggestions:\n"
        );

        if (suggestions.length() == 0) {

            report.append(
                    "• Your resume contains most of the recommended skills and sections.\n"
            );

        } else {

            report.append(
                    suggestions
            );
        }

        // =====================================================
        // AI CAREER FEEDBACK
        // =====================================================

        ResumeFeedbackGenerator feedbackGenerator =
                new ResumeFeedbackGenerator();

        report.append(
                feedbackGenerator.generateFeedback(
                        resumeText
                )
        );

        // =====================================================
        // RETURN REPORT
        // =====================================================

        return report.toString();
    }

    // =========================================================
    // HELPER METHOD
    // =========================================================

    private boolean containsAny(
            String text,
            String... keywords
    ) {

        for (String keyword : keywords) {

            if (text.contains(
                    keyword.toLowerCase()
            )) {

                return true;
            }
        }

        return false;
    }
}