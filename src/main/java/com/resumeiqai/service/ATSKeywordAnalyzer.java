package com.resumeiqai.service;

import java.util.*;

public class ATSKeywordAnalyzer {

    // =====================================================
    // KEYWORDS FOR DIFFERENT JOB ROLES
    // =====================================================

    private final Map<String, List<String>> roleKeywords =
            new HashMap<>();

    public ATSKeywordAnalyzer() {

        // -------------------------------------------------
        // FRONTEND DEVELOPMENT
        // -------------------------------------------------

        roleKeywords.put(
                "Frontend Development",

                        Arrays.asList(
                                "HTML",
                                "CSS",
                                "JavaScript",
                                "React",
                                "Git",
                                "Bootstrap",
                                "Responsive Design",
                                "UI Design",
                                "REST API",
                                "TypeScript",
                                "Tailwind CSS",
                                "Redux",
                                "Frontend",
                                "Web Development",
                                "API Integration"
                        )

        );

        // -------------------------------------------------
        // BACKEND DEVELOPMENT
        // -------------------------------------------------

        roleKeywords.put(
                "Backend Development",
                Arrays.asList(
                        "Java",
                        "Spring Boot",
                        "REST API",
                        "MySQL",
                        "SQL",
                        "Git",
                        "Docker",
                        "API",
                        "Microservices",
                        "Hibernate",
                        "JPA",
                        "Maven",
                        "Backend",
                        "Database",
                        "JUnit"
                )
        );

        // -------------------------------------------------
        // FULL STACK DEVELOPMENT
        // -------------------------------------------------

        roleKeywords.put(
                "Full Stack Development",
                Arrays.asList(
                        "HTML",
                        "CSS",
                        "JavaScript",
                        "React",
                        "Java",
                        "Spring Boot",
                        "REST API",
                        "MySQL",
                        "SQL",
                        "Git",
                        "Docker",
                        "Maven",
                        "Hibernate",
                        "Frontend",
                        "Backend"
                )
        );

        // -------------------------------------------------
        // PYTHON / DATA
        // -------------------------------------------------

        roleKeywords.put(
                "Python Development",
                Arrays.asList(
                        "Python",
                        "Django",
                        "Flask",
                        "SQL",
                        "MySQL",
                        "REST API",
                        "Git",
                        "Docker",
                        "Pandas",
                        "NumPy",
                        "Data Analysis",
                        "Machine Learning",
                        "Matplotlib",
                        "Jupyter",
                        "Backend"
                )
        );
    }

    // =====================================================
    // ANALYZE ATS KEYWORDS
    // =====================================================

    public ATSResult analyze(
            String resumeText,
            String careerArea) {

        List<String> keywords =
                roleKeywords.get(
                        careerArea
                );

        // If role is not found
        if (keywords == null) {

            keywords =
                    roleKeywords.get(
                            "Full Stack Development"
                    );
        }

        List<String> found =
                new ArrayList<>();

        List<String> missing =
                new ArrayList<>();

        String text =
                resumeText.toLowerCase();

        // -------------------------------------------------
        // CHECK EACH KEYWORD
        // -------------------------------------------------

        for (String keyword : keywords) {

            String normalizedText =
                    text
                            .replaceAll("[^a-z0-9+#.]", " ")
                            .replaceAll("\\s+", " ")
                            .trim();

            String normalizedKeyword =
                    keyword
                            .toLowerCase()
                            .replaceAll("[^a-z0-9+#.]", " ")
                            .replaceAll("\\s+", " ")
                            .trim();

            if (
                    normalizedText.contains(
                            normalizedKeyword
                    )
            ) {

                found.add(keyword);

            } else {

                missing.add(keyword);
            }
        }

        // -------------------------------------------------
        // CALCULATE MATCH PERCENTAGE
        // -------------------------------------------------

        int score = 0;

        if (!keywords.isEmpty()) {

            score =
                    (found.size() * 100)
                            / keywords.size();
        }

        String recommendation;

        if (missing.isEmpty()) {

            recommendation =
                    "Excellent! Your resume contains all important ATS keywords.";

        } else if (score >= 80) {

            recommendation =
                    "Good ATS compatibility. Consider adding: "
                            + String.join(", ", missing);

        } else if (score >= 50) {

            recommendation =
                    "Improve your ATS compatibility by adding: "
                            + String.join(", ", missing);

        } else {

            recommendation =
                    "Your resume is missing several important keywords. Consider adding: "
                            + String.join(", ", missing);
        }

        return new ATSResult(
                score,
                found,
                missing,
                recommendation
        );
    }

    // =====================================================
    // RESULT CLASS
    // =====================================================

    public static class ATSResult {

        private final int score;

        private final List<String> foundKeywords;

        private final List<String> missingKeywords;

        private final String recommendation;


        public ATSResult(
                int score,
                List<String> foundKeywords,
                List<String> missingKeywords,
                String recommendation) {

            this.score = score;

            this.foundKeywords =
                    foundKeywords;

            this.missingKeywords =
                    missingKeywords;

            this.recommendation =
                    recommendation;
        }

        public int getScore() {

            return score;
        }

        public List<String> getFoundKeywords() {

            return foundKeywords;
        }

        public List<String> getMissingKeywords() {

            return missingKeywords;
        }
        public String getRecommendation() {

            return recommendation;
        }
    }
}