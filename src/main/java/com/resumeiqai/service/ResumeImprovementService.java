package com.resumeiqai.service;

import java.util.ArrayList;
import java.util.List;

public class ResumeImprovementService {

    // =====================================================
    // GENERATE RESUME IMPROVEMENT SUGGESTIONS
    // =====================================================

    public List<String> generateSuggestions(
            int resumeScore,
            int atsScore,
            int atsKeywordScore,
            List<String> missingSkills,
            List<String> missingATSKeywords) {

        List<String> suggestions =
                new ArrayList<>();

        // =================================================
        // RESUME SCORE SUGGESTION
        // =================================================

        if (resumeScore < 50) {

            suggestions.add(
                    "Improve your overall resume content by highlighting relevant skills, projects, education, and experience."
            );

        } else if (resumeScore < 70) {

            suggestions.add(
                    "Your resume has a reasonable foundation. Strengthen your project descriptions and highlight your technical skills more clearly."
            );

        } else if (resumeScore < 85) {

            suggestions.add(
                    "Your resume is performing well. Add more measurable achievements and role-specific details to make it stronger."
            );

        } else {

            suggestions.add(
                    "Your resume has a strong overall score. Continue maintaining clear, relevant, and achievement-focused content."
            );
        }

        // =================================================
        // ATS SCORE SUGGESTION
        // =================================================

        if (atsScore < 50) {

            suggestions.add(
                    "Your ATS compatibility is low. Improve formatting and include more relevant technical and role-specific keywords."
            );

        } else if (atsScore < 75) {

            suggestions.add(
                    "Your ATS compatibility is moderate. Consider adding relevant keywords and improving the clarity of your resume sections."
            );

        } else {

            suggestions.add(
                    "Your resume has good ATS compatibility. Keep the formatting simple and maintain relevant keywords."
            );
        }

        // =================================================
        // ATS KEYWORD SUGGESTION
        // =================================================

        if (atsKeywordScore < 50) {

            suggestions.add(
                    "Your ATS keyword coverage is low. Add relevant keywords naturally to your Skills, Projects, and Experience sections."
            );

        } else if (atsKeywordScore < 75) {

            suggestions.add(
                    "Your ATS keyword coverage can be improved by including more job-specific technologies and skills."
            );

        } else {

            suggestions.add(
                    "Your ATS keyword coverage is strong. Make sure the listed skills are supported by relevant experience or projects."
            );
        }

        // =================================================
        // MISSING SKILLS
        // =================================================

        if (
                missingSkills != null
                        &&
                        !missingSkills.isEmpty()
        ) {

            suggestions.add(
                    "Consider adding relevant missing skills: "
                            + formatList(missingSkills)
            );
        }

        // =================================================
        // MISSING ATS KEYWORDS
        // =================================================

        if (
                missingATSKeywords != null
                        &&
                        !missingATSKeywords.isEmpty()
        ) {

            suggestions.add(
                    "Consider including these missing ATS keywords where they accurately reflect your experience: "
                            + formatList(missingATSKeywords)
            );
        }

        // =================================================
        // PROJECT SUGGESTION
        // =================================================

        suggestions.add(
                "Strengthen project descriptions by mentioning the technologies used, your specific contribution, and the result achieved."
        );

        // =================================================
        // ACHIEVEMENT SUGGESTION
        // =================================================

        suggestions.add(
                "Use measurable achievements whenever possible, such as percentages, time saved, performance improvements, or project scale."
        );

        return suggestions;
    }

    // =====================================================
    // FORMAT LIST
    // =====================================================

    private String formatList(
            List<String> items) {

        if (items == null || items.isEmpty()) {

            return "";
        }

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < items.size(); i++) {

            result.append(
                    items.get(i)
            );

            if (i < items.size() - 1) {

                result.append(", ");
            }
        }

        return result.toString();
    }
}