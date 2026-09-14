package com.resumeiqai.service;

import java.util.*;
import java.util.regex.Pattern;

public class JobDescriptionMatcher {

    // =====================================================
    // KEYWORD CATEGORIES
    // =====================================================

    private final Map<String, List<String>> keywordCategories =
            new LinkedHashMap<>();

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public JobDescriptionMatcher() {

        // =================================================
        // PROGRAMMING LANGUAGES
        // =================================================

        keywordCategories.put(
                "Programming Languages",
                Arrays.asList(
                        "JavaScript",
                        "TypeScript",
                        "Python",
                        "Java",
                        "C++",
                        "C#",
                        "Kotlin",
                        "Swift",
                        "Ruby",
                        "PHP",
                        "Go",
                        "C"
                )
        );

        // =================================================
        // WEB TECHNOLOGIES
        // =================================================

        keywordCategories.put(
                "Web Technologies",
                Arrays.asList(
                        "REST API",
                        "Node.js",
                        "Express.js",
                        "Bootstrap",
                        "Angular",
                        "React",
                        "Vue.js",
                        "HTML",
                        "CSS",
                        "JSON",
                        "XML",
                        "API"
                )
        );

        // =================================================
        // BACKEND TECHNOLOGIES
        // =================================================

        keywordCategories.put(
                "Backend Technologies",
                Arrays.asList(
                        "Spring Boot",
                        "Microservices",
                        "Hibernate",
                        "Spring MVC",
                        "Spring",
                        "JPA",
                        "Maven",
                        "Gradle"
                )
        );

        // =================================================
        // DATABASES
        // =================================================

        keywordCategories.put(
                "Databases",
                Arrays.asList(
                        "PostgreSQL",
                        "MongoDB",
                        "MySQL",
                        "SQLite",
                        "Oracle",
                        "Redis",
                        "SQL"
                )
        );

        // =================================================
        // CLOUD AND DEVOPS
        // =================================================

        keywordCategories.put(
                "Cloud & DevOps",
                Arrays.asList(
                        "Google Cloud Platform",
                        "Cloud Computing",
                        "Kubernetes",
                        "GitHub Actions",
                        "GitLab CI",
                        "Docker",
                        "Jenkins",
                        "GitHub",
                        "GitLab",
                        "Azure",
                        "AWS",
                        "GCP",
                        "CI/CD",
                        "Git"
                )
        );

        // =================================================
        // DATA AND AI
        // =================================================

        keywordCategories.put(
                "Data & AI",
                Arrays.asList(
                        "Artificial Intelligence",
                        "Machine Learning",
                        "Deep Learning",
                        "Data Science",
                        "Scikit-learn",
                        "TensorFlow",
                        "PyTorch",
                        "Pandas",
                        "NumPy"
                )
        );

        // =================================================
        // SOFTWARE ENGINEERING
        // =================================================

        keywordCategories.put(
                "Software Engineering",
                Arrays.asList(
                        "Object Oriented Programming",
                        "Software Engineering",
                        "Software Development",
                        "Data Structures",
                        "Problem Solving",
                        "Unit Testing",
                        "Algorithms",
                        "JUnit",
                        "Agile",
                        "Scrum",
                        "OOP"
                )
        );

        // =================================================
        // DEVELOPMENT TOOLS
        // =================================================

        keywordCategories.put(
                "Development Tools",
                Arrays.asList(
                        "IntelliJ IDEA",
                        "Visual Studio Code",
                        "Postman",
                        "Eclipse",
                        "JIRA"
                )
        );

        // =================================================
        // GENERAL TECHNICAL SKILLS
        // =================================================

        keywordCategories.put(
                "Technical Concepts",
                Arrays.asList(
                        "System Design",
                        "Database Design",
                        "RESTful Services",
                        "Object-Oriented Design",
                        "Debugging",
                        "Version Control",
                        "Software Testing"
                )
        );
    }

    // =====================================================
    // MATCH JOB DESCRIPTION WITH RESUME
    // =====================================================

    public MatchResult analyze(
            String resumeText,
            String jobDescription) {

        if (resumeText == null) {

            resumeText = "";
        }

        if (jobDescription == null) {

            jobDescription = "";
        }

        String resume =
                resumeText.toLowerCase();

        String job =
                jobDescription.toLowerCase();

        // =================================================
        // USE LINKEDHASHSET TO AUTOMATICALLY PREVENT DUPLICATES
        // =================================================

        Set<String> jobKeywords =
                new LinkedHashSet<>();

        Set<String> matchedKeywords =
                new LinkedHashSet<>();

        Set<String> missingKeywords =
                new LinkedHashSet<>();

        // =================================================
        // CHECK EACH CATEGORY
        // =================================================

        for (
                Map.Entry<String, List<String>> category
                : keywordCategories.entrySet()
        ) {

            List<String> keywords =
                    category.getValue();

            for (String keyword : keywords) {

                // =============================================
                // CHECK IF KEYWORD EXISTS IN JOB DESCRIPTION
                // =============================================

                if (
                        containsKeyword(
                                job,
                                keyword
                        )
                ) {

                    // Avoid counting smaller overlapping
                    // keywords when a more specific keyword
                    // is already present.

                    if (
                            isCoveredByExistingKeyword(
                                    keyword,
                                    jobKeywords
                            )
                    ) {

                        continue;
                    }

                    removeCoveredKeywords(
                            keyword,
                            jobKeywords
                    );

                    removeCoveredKeywords(
                            keyword,
                            matchedKeywords
                    );

                    removeCoveredKeywords(
                            keyword,
                            missingKeywords
                    );

                    jobKeywords.add(
                            keyword
                    );

                    // =========================================
                    // CHECK IF KEYWORD EXISTS IN RESUME
                    // =========================================

                    if (
                            containsKeyword(
                                    resume,
                                    keyword
                            )
                    ) {

                        matchedKeywords.add(
                                keyword
                        );

                    } else {

                        missingKeywords.add(
                                keyword
                        );
                    }
                }
            }
        }

        // =================================================
        // CALCULATE MATCH SCORE
        // =================================================

        int score = 0;

        if (!jobKeywords.isEmpty()) {

            score =
                    (int) Math.round(
                            (
                                    matchedKeywords.size()
                                            * 100.0
                            )
                                    / jobKeywords.size()
                    );
        }

        // =================================================
        // GENERATE RECOMMENDATION
        // =================================================

        List<String> jobKeywordList =
                new ArrayList<>(
                        jobKeywords
                );

        List<String> matchedKeywordList =
                new ArrayList<>(
                        matchedKeywords
                );

        List<String> missingKeywordList =
                new ArrayList<>(
                        missingKeywords
                );

        String recommendation =
                generateRecommendation(
                        score,
                        jobKeywordList,
                        matchedKeywordList,
                        missingKeywordList
                );

        // =================================================
        // RETURN RESULT
        // =================================================

        return new MatchResult(
                score,
                matchedKeywordList,
                missingKeywordList,
                recommendation
        );
    }

    // =====================================================
    // CHECK IF KEYWORD IS COVERED BY A MORE SPECIFIC KEYWORD
    // =====================================================

    private boolean isCoveredByExistingKeyword(
            String keyword,
            Set<String> existingKeywords) {

        String current =
                keyword.toLowerCase();

        for (
                String existingKeyword
                : existingKeywords
        ) {

            String existing =
                    existingKeyword.toLowerCase();

            if (
                    existing.contains(current)
                            &&
                            existing.length()
                                    > current.length()
            ) {

                return true;
            }
        }

        return false;
    }

    // =====================================================
    // REMOVE SMALLER KEYWORDS COVERED BY CURRENT KEYWORD
    // =====================================================

    private void removeCoveredKeywords(
            String keyword,
            Set<String> keywords) {

        String current =
                keyword.toLowerCase();

        keywords.removeIf(
                existingKeyword -> {

                    String existing =
                            existingKeyword.toLowerCase();

                    return current.contains(existing)
                            &&
                            current.length()
                                    > existing.length();
                }
        );
    }

    // =====================================================
    // GENERATE SMART RECOMMENDATION
    // =====================================================

    private String generateRecommendation(
            int score,
            List<String> jobKeywords,
            List<String> matchedKeywords,
            List<String> missingKeywords) {

        // =================================================
        // NO RECOGNIZED KEYWORDS
        // =================================================

        if (jobKeywords.isEmpty()) {

            return
                    "No major technical keywords were detected "
                            + "from the job description. Try using "
                            + "a more detailed job description that "
                            + "includes required skills, technologies, "
                            + "tools, or programming languages.";
        }

        // =================================================
        // PERFECT MATCH
        // =================================================

        if (score == 100) {

            return
                    "Excellent match! Your resume contains all "
                            + "of the important technical keywords "
                            + "identified from this job description.";
        }

        // =================================================
        // HIGH MATCH
        // =================================================

        if (score >= 80) {

            return
                    "Excellent match! Your resume already covers "
                            + "most important technical requirements. "
                            + "Review the missing keywords and include "
                            + "them only if they genuinely represent "
                            + "your skills or experience.";
        }

        // =================================================
        // GOOD MATCH
        // =================================================

        if (score >= 60) {

            return
                    "Good match. Your resume matches many important "
                            + "job requirements, but relevant missing "
                            + "skills, projects, or experience may "
                            + "improve your compatibility.";
        }

        // =================================================
        // MODERATE MATCH
        // =================================================

        if (score >= 40) {

            return
                    "Moderate match. Several important technical "
                            + "keywords are missing from your resume. "
                            + "Consider strengthening your projects, "
                            + "skills, and experience sections.";
        }

        // =================================================
        // LOW MATCH
        // =================================================

        return
                "Low match. The job description requires several "
                        + "technical skills that are not currently "
                        + "visible in your resume. Review the missing "
                        + "keywords and tailor your resume only with "
                        + "skills and experience you genuinely possess.";
    }

    // =====================================================
    // CHECK WHETHER KEYWORD EXISTS
    // =====================================================

    private boolean containsKeyword(
            String text,
            String keyword) {

        if (
                text == null
                        ||
                        text.trim().isEmpty()
        ) {

            return false;
        }

        String escapedKeyword =
                Pattern.quote(
                        keyword.toLowerCase()
                );

        return Pattern.compile(
                "(?<![a-z0-9])"
                        + escapedKeyword
                        + "(?![a-z0-9])"
        ).matcher(
                text.toLowerCase()
        ).find();
    }

    // =====================================================
    // RESULT CLASS
    // =====================================================

    public static class MatchResult {

        private final int score;

        private final List<String> matchedKeywords;

        private final List<String> missingKeywords;

        private final String recommendation;

        public MatchResult(
                int score,
                List<String> matchedKeywords,
                List<String> missingKeywords,
                String recommendation) {

            this.score =
                    score;

            this.matchedKeywords =
                    matchedKeywords;

            this.missingKeywords =
                    missingKeywords;

            this.recommendation =
                    recommendation;
        }

        public int getScore() {

            return score;
        }

        public List<String> getMatchedKeywords() {

            return matchedKeywords;
        }

        public List<String> getMissingKeywords() {

            return missingKeywords;
        }

        public String getRecommendation() {

            return recommendation;
        }
    }
}