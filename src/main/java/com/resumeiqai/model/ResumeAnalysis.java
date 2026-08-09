package com.resumeiqai.model;

public class ResumeAnalysis {

    private int score;
    private String recommendedRole;
    private String skillsFound;
    private String fullReport;

    // Empty Constructor
    public ResumeAnalysis() {

    }

    // Getters

    public int getScore() {
        return score;
    }

    public String getRecommendedRole() {
        return recommendedRole;
    }

    public String getSkillsFound() {
        return skillsFound;
    }

    public String getFullReport() {
        return fullReport;
    }

    // Setters

    public void setScore(int score) {
        this.score = score;
    }

    public void setRecommendedRole(String recommendedRole) {
        this.recommendedRole = recommendedRole;
    }

    public void setSkillsFound(String skillsFound) {
        this.skillsFound = skillsFound;
    }

    public void setFullReport(String fullReport) {
        this.fullReport = fullReport;
    }

}