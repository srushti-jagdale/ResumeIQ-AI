package com.resumeiqai.service;

public class JobMatchCalculator {

    public int calculateMatch(
            String resumeText,
            String[] requiredSkills) {

        if (resumeText == null) {
            resumeText = "";
        }

        if (requiredSkills == null
                || requiredSkills.length == 0) {
            return 0;
        }

        String text = resumeText.toLowerCase();

        int foundSkills = 0;

        for (String skill : requiredSkills) {

            if (text.contains(skill.toLowerCase())) {
                foundSkills++;
            }
        }

        return (foundSkills * 100)
                / requiredSkills.length;
    }
}