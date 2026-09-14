package com.resumeiqai.service;

public class ResumeScorer {

    public int calculateSkillScore(
            String resumeText,
            String[] skills) {

        if (resumeText == null) {
            resumeText = "";
        }

        String text = resumeText.toLowerCase();

        int foundSkills = 0;

        for (String skill : skills) {

            if (text.contains(skill.toLowerCase())) {
                foundSkills++;
            }
        }

        if (skills.length == 0) {
            return 0;
        }

        return (foundSkills * 50) / skills.length;
    }

    public int calculateEducationScore(String text) {

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
                "diploma",
                "degree",
                "university",
                "college"
        )) {
            return 15;
        }

        return 0;
    }

    public int calculateProjectScore(String text) {

        if (containsAny(
                text,
                "project",
                "projects",
                "developed",
                "developing",
                "application",
                "system",
                "website",
                "dashboard"
        )) {
            return 10;
        }

        return 0;
    }

    public int calculateExperienceScore(String text) {

        if (containsAny(
                text,
                "experience",
                "internship",
                "intern",
                "work experience",
                "employment",
                "worked",
                "developer intern",
                "software engineer",
                "data analyst",
                "data analytics"
        )) {
            return 10;
        }

        return 0;
    }

    public int calculateCertificationScore(String text) {

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
            return 5;
        }

        return 0;
    }

    public int calculateContactScore(String text) {

        int score = 0;

        if (text == null) {
            text = "";
        }

        // Email detection
        boolean hasEmail =
                text.matches(
                        "(?s).*\\b[\\w.%+-]+@[\\w.-]+\\.[a-z]{2,}\\b.*"
                );

        // Phone detection
        String digitsOnly = text.replaceAll("\\D", "");

        boolean hasPhone = false;

        // Check for a 10-digit Indian mobile number
        if (digitsOnly.matches(".*[6-9]\\d{9}.*")) {
            hasPhone = true;
        }

        if (hasEmail) {
            score += 3;
        }

        if (hasPhone) {
            score += 2;
        }

        return score;
    }

    public int calculateStructureScore(String text) {

        int sectionCount = 0;

        if (containsAny(
                text,
                "objective",
                "summary",
                "profile"
        )) {
            sectionCount++;
        }

        if (text.contains("education")) {
            sectionCount++;
        }

        if (containsAny(
                text,
                "skills",
                "technical skills"
        )) {
            sectionCount++;
        }

        if (text.contains("projects")) {
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
            return 5;
        }

        if (sectionCount >= 2) {
            return 3;
        }

        if (sectionCount == 1) {
            return 1;
        }

        return 0;
    }

    public int calculateFinalScore(
            int skillScore,
            int educationScore,
            int projectScore,
            int experienceScore,
            int certificationScore,
            int contactScore,
            int structureScore) {

        int total =
                skillScore
                        + educationScore
                        + projectScore
                        + experienceScore
                        + certificationScore
                        + contactScore
                        + structureScore;

        if (total > 100) {
            total = 100;
        }

        if (total < 0) {
            total = 0;
        }

        return total;
    }

    private boolean containsAny(
            String text,
            String... keywords) {

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