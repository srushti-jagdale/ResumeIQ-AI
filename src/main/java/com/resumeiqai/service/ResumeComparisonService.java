package com.resumeiqai.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeComparisonService {

    // =====================================================
    // SKILLS DATABASE
    // =====================================================

    private final List<String> skills =
            Arrays.asList(

                    // Programming Languages
                    "Java",
                    "Python",
                    "C",
                    "C++",
                    "C#",
                    "JavaScript",
                    "TypeScript",
                    "PHP",
                    "Kotlin",
                    "Swift",
                    "Go",
                    "Ruby",

                    // Web Technologies
                    "HTML",
                    "CSS",
                    "React",
                    "Angular",
                    "Vue",
                    "Bootstrap",
                    "Node.js",
                    "Express",
                    "REST API",
                    "JSON",
                    "XML",

                    // Backend
                    "Spring",
                    "Spring Boot",
                    "Hibernate",
                    "JPA",
                    "Microservices",
                    "Maven",
                    "Gradle",

                    // Databases
                    "MySQL",
                    "SQL",
                    "PostgreSQL",
                    "Oracle",
                    "MongoDB",
                    "SQLite",
                    "Redis",

                    // Cloud and DevOps
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "GCP",
                    "Git",
                    "GitHub",
                    "Docker",
                    "Kubernetes",
                    "Jenkins",
                    "CI/CD",

                    // AI and Data
                    "Machine Learning",
                    "Deep Learning",
                    "Artificial Intelligence",
                    "Data Science",
                    "Pandas",
                    "NumPy",
                    "TensorFlow",
                    "PyTorch",

                    // Software Engineering
                    "Data Structures",
                    "Algorithms",
                    "OOP",
                    "Object Oriented Programming",
                    "Problem Solving",
                    "JUnit"
            );

    // =====================================================
    // COMPARE TWO RESUMES
    // =====================================================

    public ComparisonResult compareResumes(
            String resumeText1,
            String resumeText2) {

        if (resumeText1 == null) {

            resumeText1 = "";
        }

        if (resumeText2 == null) {

            resumeText2 = "";
        }

        // =================================================
        // EXTRACT SKILLS
        // =================================================

        List<String> resume1Skills =
                extractSkills(
                        resumeText1
                );

        List<String> resume2Skills =
                extractSkills(
                        resumeText2
                );

        // =================================================
        // CALCULATE SCORES
        // =================================================

        int resume1Score =
                calculateScore(
                        resume1Skills
                );

        int resume2Score =
                calculateScore(
                        resume2Skills
                );

        // =================================================
        // COMMON SKILLS
        // =================================================

        List<String> commonSkills =
                new ArrayList<>();

        for (
                String skill
                : resume1Skills
        ) {

            if (
                    resume2Skills.contains(
                            skill
                    )
            ) {

                commonSkills.add(
                        skill
                );
            }
        }

        // =================================================
        // UNIQUE SKILLS - RESUME 1
        // =================================================

        List<String> uniqueResume1Skills =
                new ArrayList<>();

        for (
                String skill
                : resume1Skills
        ) {

            if (
                    !resume2Skills.contains(
                            skill
                    )
            ) {

                uniqueResume1Skills.add(
                        skill
                );
            }
        }

        // =================================================
        // UNIQUE SKILLS - RESUME 2
        // =================================================

        List<String> uniqueResume2Skills =
                new ArrayList<>();

        for (
                String skill
                : resume2Skills
        ) {

            if (
                    !resume1Skills.contains(
                            skill
                    )
            ) {

                uniqueResume2Skills.add(
                        skill
                );
            }
        }

        // =================================================
        // DETERMINE STRONGER RESUME
        // =================================================

        String strongerResume;

        if (
                resume1Score >
                        resume2Score
        ) {

            strongerResume =
                    "Resume 1";

        } else if (
                resume2Score >
                        resume1Score
        ) {

            strongerResume =
                    "Resume 2";

        } else {

            strongerResume =
                    "Both resumes have equal scores";
        }

        // =================================================
        // RETURN RESULT
        // =================================================

        return new ComparisonResult(

                resume1Score,

                resume2Score,

                resume1Skills,

                resume2Skills,

                commonSkills,

                uniqueResume1Skills,

                uniqueResume2Skills,

                strongerResume
        );
    }

    // =====================================================
    // EXTRACT SKILLS
    // =====================================================

    private List<String> extractSkills(
            String resumeText) {

        List<String> foundSkills =
                new ArrayList<>();

        String text =
                resumeText.toLowerCase();

        for (
                String skill
                : skills
        ) {

            if (
                    text.contains(
                            skill.toLowerCase()
                    )
            ) {

                foundSkills.add(
                        skill
                );
            }
        }

        return foundSkills;
    }

    // =====================================================
    // CALCULATE SCORE
    // =====================================================

    private int calculateScore(
            List<String> foundSkills) {

        if (
                skills.isEmpty()
        ) {

            return 0;
        }

        return
                (int) Math.round(

                        (
                                foundSkills.size()
                                        * 100.0
                        )

                                /

                                skills.size()
                );
    }

    // =====================================================
    // COMPARISON RESULT CLASS
    // =====================================================

    public static class ComparisonResult {

        private final int resume1Score;

        private final int resume2Score;

        private final List<String> resume1Skills;

        private final List<String> resume2Skills;

        private final List<String> commonSkills;

        private final List<String> uniqueResume1Skills;

        private final List<String> uniqueResume2Skills;

        private final String strongerResume;

        public ComparisonResult(

                int resume1Score,

                int resume2Score,

                List<String> resume1Skills,

                List<String> resume2Skills,

                List<String> commonSkills,

                List<String> uniqueResume1Skills,

                List<String> uniqueResume2Skills,

                String strongerResume) {

            this.resume1Score =
                    resume1Score;

            this.resume2Score =
                    resume2Score;

            this.resume1Skills =
                    resume1Skills;

            this.resume2Skills =
                    resume2Skills;

            this.commonSkills =
                    commonSkills;

            this.uniqueResume1Skills =
                    uniqueResume1Skills;

            this.uniqueResume2Skills =
                    uniqueResume2Skills;

            this.strongerResume =
                    strongerResume;
        }

        public int getResume1Score() {

            return resume1Score;
        }

        public int getResume2Score() {

            return resume2Score;
        }

        public List<String> getResume1Skills() {

            return resume1Skills;
        }

        public List<String> getResume2Skills() {

            return resume2Skills;
        }

        public List<String> getCommonSkills() {

            return commonSkills;
        }

        public List<String> getUniqueResume1Skills() {

            return uniqueResume1Skills;
        }

        public List<String> getUniqueResume2Skills() {

            return uniqueResume2Skills;
        }

        public String getStrongerResume() {

            return strongerResume;
        }
    }
}