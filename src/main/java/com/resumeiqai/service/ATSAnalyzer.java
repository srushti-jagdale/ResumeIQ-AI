package com.resumeiqai.service;

public class ATSAnalyzer {

    // =========================================================
    // MAIN ATS ANALYSIS METHOD
    // =========================================================

    public String analyzeATS(
            String resumeText,
            String careerArea) {

        if (resumeText == null) {
            resumeText = "";
        }

        if (careerArea == null ||
                careerArea.trim().isEmpty()) {

            careerArea = "General Software Development";
        }

        String text = resumeText.toLowerCase();

        // =====================================================
        // GET CAREER-SPECIFIC SKILLS
        // =====================================================

        SkillAnalyzer skillAnalyzer =
                new SkillAnalyzer();

        String[] careerSkills =
                skillAnalyzer.getSkillsForCareer(
                        careerArea
                );

        // =====================================================
        // CALCULATE SCORES
        // =====================================================

        int keywordScore =
                calculateKeywordScore(
                        text,
                        careerSkills
                );

        int structureScore =
                calculateStructureScore(text);

        int contactScore =
                calculateContactScore(text);

        int skillsScore =
                calculateSkillsScore(
                        text,
                        careerSkills
                );

        int projectScore =
                calculateProjectScore(text);

        // =====================================================
        // FINAL ATS SCORE
        // =====================================================

        int atsScore =
                keywordScore
                        + structureScore
                        + contactScore
                        + skillsScore
                        + projectScore;

        if (atsScore > 100) {
            atsScore = 100;
        }

        if (atsScore < 0) {
            atsScore = 0;
        }

        // =====================================================
        // GET KEYWORD ANALYSIS
        // =====================================================

        String matchedKeywords =
                getMatchedKeywords(
                        text,
                        careerSkills
                );

        String missingKeywords =
                getMissingKeywords(
                        text,
                        careerSkills
                );

        int matchedKeywordCount =
                countMatchedKeywords(
                        text,
                        careerSkills
                );

        int missingKeywordCount =
                countMissingKeywords(
                        text,
                        careerSkills
                );

        // =====================================================
        // BUILD REPORT
        // =====================================================

        StringBuilder report =
                new StringBuilder();

        report.append(
                "\n\n===== ATS Analysis =====\n\n"
        );

        report.append(
                        "ATS Career Area : "
                )
                .append(careerArea)
                .append("\n\n");

        report.append(
                        "ATS Compatibility Score : "
                )
                .append(atsScore)
                .append("/100\n\n");

        // =====================================================
        // SCORE BREAKDOWN
        // =====================================================

        report.append(
                "ATS Score Breakdown:\n"
        );

        report.append(
                        "Keyword Match : "
                )
                .append(keywordScore)
                .append("/25\n");

        report.append(
                        "Resume Structure : "
                )
                .append(structureScore)
                .append("/20\n");

        report.append(
                        "Contact Information : "
                )
                .append(contactScore)
                .append("/15\n");

        report.append(
                        "Skills Relevance : "
                )
                .append(skillsScore)
                .append("/25\n");

        report.append(
                        "Project Relevance : "
                )
                .append(projectScore)
                .append("/15\n\n");

        // =====================================================
        // ATS KEYWORD SUMMARY
        // =====================================================

        report.append(
                "ATS Keyword Summary:\n"
        );

        report.append(
                        "Matched Keywords : "
                )
                .append(matchedKeywordCount)
                .append("\n");

        report.append(
                        "Missing Keywords : "
                )
                .append(missingKeywordCount)
                .append("\n\n");

        // =====================================================
        // MATCHED KEYWORDS
        // =====================================================

        report.append(
                "ATS Matched Keywords:\n"
        );

        if (matchedKeywords.isEmpty()) {

            report.append(
                    "• No career-specific keywords detected.\n"
            );

        } else {

            report.append(
                    matchedKeywords
            );
        }

        // =====================================================
        // MISSING KEYWORDS
        // =====================================================

        report.append(
                "\nATS Missing Keywords:\n"
        );

        if (missingKeywords.isEmpty()) {

            report.append(
                    "• No major career-specific keywords are missing.\n"
            );

        } else {

            report.append(
                    missingKeywords
            );
        }

        // =====================================================
        // ATS STRENGTHS
        // =====================================================

        report.append(
                "\nATS Strengths:\n"
        );

        boolean hasStrength = false;

        if (keywordScore >= 18) {

            report.append(
                    "• Good career-specific keyword usage.\n"
            );

            hasStrength = true;
        }

        if (structureScore >= 15) {

            report.append(
                    "• Resume contains important sections.\n"
            );

            hasStrength = true;
        }

        if (contactScore >= 10) {

            report.append(
                    "• Contact information is clearly available.\n"
            );

            hasStrength = true;
        }

        if (skillsScore >= 18) {

            report.append(
                    "• Relevant technical skills detected for the selected career.\n"
            );

            hasStrength = true;
        }

        if (projectScore >= 10) {

            report.append(
                    "• Projects are included in the resume.\n"
            );

            hasStrength = true;
        }

        if (!hasStrength) {

            report.append(
                    "• Resume has some basic ATS-compatible elements.\n"
            );
        }

        // =====================================================
        // ATS ISSUES
        // =====================================================

        report.append(
                "\nATS Issues:\n"
        );

        boolean hasIssue = false;

        if (keywordScore < 18) {

            report.append(
                    "• Improve career-specific keyword coverage.\n"
            );

            hasIssue = true;
        }

        if (structureScore < 15) {

            report.append(
                    "• Improve resume structure and section organization.\n"
            );

            hasIssue = true;
        }

        if (contactScore < 10) {

            report.append(
                    "• Add complete contact information.\n"
            );

            hasIssue = true;
        }

        if (skillsScore < 18) {

            report.append(
                    "• Add more relevant skills for "
                            + careerArea
                            + ".\n"
            );

            hasIssue = true;
        }

        if (projectScore < 10) {

            report.append(
                    "• Add or improve project descriptions.\n"
            );

            hasIssue = true;
        }

        if (!hasIssue) {

            report.append(
                    "• No major ATS issues detected.\n"
            );
        }

        // =====================================================
        // FINAL RECOMMENDATION
        // =====================================================

        report.append(
                "\nATS Recommendation:\n"
        );

        if (atsScore >= 80) {

            report.append(
                    "Excellent ATS compatibility. "
                            + "Your resume is well optimized for "
                            + careerArea
                            + " roles.\n"
            );

        } else if (atsScore >= 60) {

            report.append(
                    "Good ATS compatibility. "
                            + "Improve keyword coverage and resume "
                            + "structure for better results.\n"
            );

        } else if (atsScore >= 40) {

            report.append(
                    "Moderate ATS compatibility. "
                            + "Your resume needs improvement in "
                            + "several important areas.\n"
            );

        } else {

            report.append(
                    "Low ATS compatibility. "
                            + "Add relevant keywords, skills, "
                            + "projects and important resume sections.\n"
            );
        }

        return report.toString();
    }

    // =========================================================
    // MATCHED KEYWORDS
    // =========================================================

    private String getMatchedKeywords(
            String text,
            String[] careerSkills) {

        StringBuilder result =
                new StringBuilder();

        if (careerSkills == null) {
            return "";
        }

        for (String skill : careerSkills) {

            if (skill == null ||
                    skill.trim().isEmpty()) {

                continue;
            }

            if (text.contains(
                    skill.toLowerCase()
            )) {

                result.append("✔ ")
                        .append(skill)
                        .append("\n");
            }
        }

        return result.toString();
    }

    // =========================================================
    // MISSING KEYWORDS
    // =========================================================

    private String getMissingKeywords(
            String text,
            String[] careerSkills) {

        StringBuilder result =
                new StringBuilder();

        if (careerSkills == null) {
            return "";
        }

        for (String skill : careerSkills) {

            if (skill == null ||
                    skill.trim().isEmpty()) {

                continue;
            }

            if (!text.contains(
                    skill.toLowerCase()
            )) {

                result.append("✘ ")
                        .append(skill)
                        .append("\n");
            }
        }

        return result.toString();
    }

    // =========================================================
    // COUNT MATCHED KEYWORDS
    // =========================================================

    private int countMatchedKeywords(
            String text,
            String[] careerSkills) {

        int count = 0;

        if (careerSkills == null) {
            return 0;
        }

        for (String skill : careerSkills) {

            if (skill == null ||
                    skill.trim().isEmpty()) {

                continue;
            }

            if (text.contains(
                    skill.toLowerCase()
            )) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // COUNT MISSING KEYWORDS
    // =========================================================

    private int countMissingKeywords(
            String text,
            String[] careerSkills) {

        int count = 0;

        if (careerSkills == null) {
            return 0;
        }

        for (String skill : careerSkills) {

            if (skill == null ||
                    skill.trim().isEmpty()) {

                continue;
            }

            if (!text.contains(
                    skill.toLowerCase()
            )) {

                count++;
            }
        }

        return count;
    }

    // =========================================================
    // KEYWORD SCORE
    // Maximum = 25
    // =========================================================

    private int calculateKeywordScore(
            String text,
            String[] careerSkills) {

        int score = 0;

        if (careerSkills == null) {
            return 0;
        }

        for (String skill : careerSkills) {

            if (skill == null ||
                    skill.trim().isEmpty()) {

                continue;
            }

            if (text.contains(
                    skill.toLowerCase()
            )) {

                score += 3;
            }

            if (score >= 25) {
                break;
            }
        }

        return Math.min(score, 25);
    }

    // =========================================================
    // STRUCTURE SCORE
    // Maximum = 20
    // =========================================================

    private int calculateStructureScore(
            String text) {

        int score = 0;

        if (text.contains("education")) {
            score += 4;
        }

        if (text.contains("skills")) {
            score += 4;
        }

        if (text.contains("project")
                || text.contains("projects")) {

            score += 4;
        }

        if (text.contains("experience")
                || text.contains("internship")) {

            score += 4;
        }

        if (text.contains("summary")
                || text.contains("objective")
                || text.contains("profile")) {

            score += 4;
        }

        return Math.min(score, 20);
    }

    // =========================================================
    // CONTACT INFORMATION SCORE
    // Maximum = 15
    // =========================================================

    private int calculateContactScore(
            String text) {

        int score = 0;

        if (text.contains("@")) {
            score += 5;
        }

        if (containsPhoneNumber(text)) {
            score += 5;
        }

        if (text.contains("linkedin")
                || text.contains("github")) {

            score += 5;
        }

        return Math.min(score, 15);
    }

    // =========================================================
    // SKILLS SCORE
    // Maximum = 25
    // =========================================================

    private int calculateSkillsScore(
            String text,
            String[] careerSkills) {

        int score = 0;

        if (careerSkills == null) {
            return 0;
        }

        for (String skill : careerSkills) {

            if (skill == null ||
                    skill.trim().isEmpty()) {

                continue;
            }

            if (text.contains(
                    skill.toLowerCase()
            )) {

                score += 3;
            }

            if (score >= 25) {
                break;
            }
        }

        return Math.min(score, 25);
    }

    // =========================================================
    // PROJECT SCORE
    // Maximum = 15
    // =========================================================

    private int calculateProjectScore(
            String text) {

        int score = 0;

        if (text.contains("project")
                || text.contains("projects")) {

            score += 5;
        }

        if (text.contains("developed")
                || text.contains("implemented")
                || text.contains("created")
                || text.contains("designed")) {

            score += 5;
        }

        if (text.contains("technologies")
                || text.contains("technology")
                || text.contains("tools")
                || text.contains("using")) {

            score += 5;
        }

        return Math.min(score, 15);
    }

    // =========================================================
    // PHONE NUMBER DETECTION
    // =========================================================

    private boolean containsPhoneNumber(
            String text) {

        int consecutiveDigits = 0;

        for (int i = 0;
             i < text.length();
             i++) {

            char character =
                    text.charAt(i);

            if (Character.isDigit(character)) {

                consecutiveDigits++;

                if (consecutiveDigits >= 10) {

                    return true;
                }

            } else {

                consecutiveDigits = 0;
            }
        }

        return false;
    }
}