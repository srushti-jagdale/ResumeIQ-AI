package com.resumeiqai.ui;

import com.resumeiqai.database.ResumeDAO;
import com.resumeiqai.service.PDFReader;
import com.resumeiqai.service.ResumeAnalyzer;
import com.resumeiqai.service.PDFReportGenerator;
import com.resumeiqai.service.WordReportGenerator;
import com.resumeiqai.service.ATSKeywordAnalyzer;
import com.resumeiqai.service.ResumeImprovementService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private File selectedFile;
    private String analyzedResumeText = "";

    // =========================
    // ANALYSIS SUMMARY LABELS
    // =========================

    private JLabel careerAreaValue;
    private JLabel scoreValue;
    private JLabel bestRoleValue;
    private JLabel skillsValue;
    private JLabel missingSkillsValue;
    private JLabel atsScoreValue;
    private JLabel atsKeywordScoreValue;
    private JLabel atsKeywordsFoundValue;
    private JLabel atsKeywordsMissingValue;
    private JLabel atsRecommendationValue;
    private JLabel improvementSuggestionsValue;

    public MainFrame() {

        setTitle("ResumeIQ AI");
        setSize(900, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        mainPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel("ResumeIQ AI");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        JLabel subtitleLabel =
                new JLabel(
                        "Analyze your resume and get job recommendations"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // RESUME INFORMATION
        // =====================================================

        JLabel statusLabel =
                new JLabel("No Resume Selected");

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        statusLabel.setForeground(
                Color.RED
        );

        statusLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel fileNameLabel =
                new JLabel("File Name : -");

        JLabel fileTypeLabel =
                new JLabel("File Type : -");

        JLabel fileSizeLabel =
                new JLabel("File Size : -");

        fileNameLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        fileTypeLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        fileSizeLabel.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        fileNameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        fileTypeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        fileSizeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JPanel infoPanel =
                new JPanel();

        infoPanel.setLayout(
                new BoxLayout(
                        infoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        infoPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Resume Information"
                )
        );

        infoPanel.setPreferredSize(
                new Dimension(500, 170)
        );

        infoPanel.setMaximumSize(
                new Dimension(500, 170)
        );

        infoPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        infoPanel.add(statusLabel);

        infoPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        infoPanel.add(fileNameLabel);

        infoPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 5)
                )
        );

        infoPanel.add(fileTypeLabel);

        infoPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 5)
                )
        );

        infoPanel.add(fileSizeLabel);

        // =====================================================
        // RESUME SCORE BAR
        // =====================================================

        JProgressBar scoreBar =
                new JProgressBar(0, 100);

        scoreBar.setValue(0);
        scoreBar.setString("Resume Score");
        scoreBar.setStringPainted(true);

        scoreBar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        scoreBar.setPreferredSize(
                new Dimension(500, 30)
        );

        scoreBar.setMaximumSize(
                new Dimension(500, 30)
        );

        // =====================================================
        // ATS SCORE BAR
        // =====================================================

        JProgressBar atsScoreBar =
                new JProgressBar(0, 100);

        atsScoreBar.setValue(0);
        atsScoreBar.setString(
                "ATS Compatibility"
        );

        atsScoreBar.setStringPainted(true);

        atsScoreBar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        atsScoreBar.setPreferredSize(
                new Dimension(500, 30)
        );

        atsScoreBar.setMaximumSize(
                new Dimension(500, 30)
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton uploadButton =
                new JButton("Upload Resume");

        uploadButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        JButton analyzeButton =
                new JButton("Analyze Resume");
        JButton improvementButton =
                new JButton("Resume Improvement");

        improvementButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        improvementButton.setEnabled(false);
        JButton jobDescriptionButton =
                new JButton("Job Description Matcher");

        jobDescriptionButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        jobDescriptionButton.setEnabled(false);

        analyzeButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        analyzeButton.setEnabled(false);

        JButton historyButton =
                new JButton("View History");

        historyButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        JButton dashboardButton =
                new JButton("Dashboard");

        dashboardButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        JButton downloadButton =
                new JButton("Download PDF");

        downloadButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        downloadButton.setEnabled(false);

        JButton exportButton =
                new JButton("Export Word");

        exportButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        exportButton.setEnabled(false);

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                3,
                                12,
                                12
                        )
                );

        buttonPanel.setPreferredSize(
                new Dimension(650, 140)
        );

        buttonPanel.setMaximumSize(
                new Dimension(650, 140)
        );

        buttonPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        buttonPanel.add(uploadButton);
        buttonPanel.add(analyzeButton);
        buttonPanel.add(improvementButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(dashboardButton);
        buttonPanel.add(downloadButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(jobDescriptionButton);

        // =====================================================
        // ANALYSIS SUMMARY
        // =====================================================

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                15,
                                20
                        )
                );

        summaryPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Analysis Summary"
                )
        );

        summaryPanel.setPreferredSize(
                new Dimension(900, 900)
        );

        summaryPanel.setMaximumSize(
                new Dimension(900, 900)
        );

        summaryPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // CAREER AREA
        // =====================================================

        summaryPanel.add(
                new JLabel("Career Area :")
        );

        careerAreaValue =
                new JLabel("-");

        careerAreaValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                careerAreaValue
        );

        // =====================================================
        // RESUME SCORE
        // =====================================================

        summaryPanel.add(
                new JLabel("Resume Score :")
        );

        scoreValue =
                new JLabel("-");

        scoreValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                scoreValue
        );

        // =====================================================
        // BEST JOB ROLE
        // =====================================================

        summaryPanel.add(
                new JLabel("Best Job Role :")
        );

        bestRoleValue =
                new JLabel("-");

        bestRoleValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                bestRoleValue
        );

        // =====================================================
        // SKILLS FOUND
        // =====================================================

        summaryPanel.add(
                new JLabel("Skills Found :")
        );

        skillsValue =
                new JLabel("-");

        skillsValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                skillsValue
        );

        // =====================================================
        // MISSING SKILLS
        // =====================================================

        summaryPanel.add(
                new JLabel("Missing Skills :")
        );

        missingSkillsValue =
                new JLabel("-");

        missingSkillsValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                missingSkillsValue
        );

        // =====================================================
        // ATS SCORE
        // =====================================================

        summaryPanel.add(
                new JLabel("ATS Score :")
        );

        atsScoreValue =
                new JLabel("-");

        atsScoreValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                atsScoreValue
        );

        // =====================================================
        // ATS KEYWORD SCORE
        // =====================================================

        summaryPanel.add(
                new JLabel(
                        "ATS Keyword Match :"
                )
        );

        atsKeywordScoreValue =
                new JLabel("-");

        atsKeywordScoreValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        summaryPanel.add(
                atsKeywordScoreValue
        );

        // =====================================================
        // ATS KEYWORDS FOUND
        // =====================================================

        summaryPanel.add(
                new JLabel(
                        "ATS Keywords Found :"
                )
        );

        atsKeywordsFoundValue =
                new JLabel(
                        "<html>-</html>"
                );

        atsKeywordsFoundValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        atsKeywordsFoundValue.setVerticalAlignment(
                SwingConstants.TOP
        );

        summaryPanel.add(
                atsKeywordsFoundValue
        );

        // =====================================================
        // ATS KEYWORDS MISSING
        // =====================================================

        summaryPanel.add(
                new JLabel(
                        "ATS Keywords Missing :"
                )
        );

        atsKeywordsMissingValue =
                new JLabel(
                        "<html>-</html>"
                );

        atsKeywordsMissingValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        atsKeywordsMissingValue.setVerticalAlignment(
                SwingConstants.TOP
        );

        summaryPanel.add(
                atsKeywordsMissingValue
        );

        // =====================================================
        // IMPROVEMENT SUGGESTIONS
        // =====================================================

        improvementSuggestionsValue =
                new JLabel(
                        "<html>-</html>"
                );

        improvementSuggestionsValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        improvementSuggestionsValue.setVerticalAlignment(
                SwingConstants.TOP
        );

        summaryPanel.add(
                new JLabel(
                        "Improvement Suggestions :"
                )
        );

        summaryPanel.add(
                improvementSuggestionsValue
        );

        // =====================================================
        // ATS RECOMMENDATION
        // =====================================================

        summaryPanel.add(
                new JLabel(
                        "ATS Recommendation :"
                )
        );

        atsRecommendationValue =
                new JLabel(
                        "<html>-</html>"
                );

        atsRecommendationValue.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        atsRecommendationValue.setVerticalAlignment(
                SwingConstants.TOP
        );

        summaryPanel.add(
                atsRecommendationValue
        );

        // =====================================================
        // REPORT AREA
        // =====================================================

        JTextArea reportArea =
                new JTextArea();

        reportArea.setEditable(false);

        reportArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        reportArea.setLineWrap(true);
        reportArea.setWrapStyleWord(true);

        JScrollPane reportScrollPane =
                new JScrollPane(reportArea);

        reportScrollPane.setPreferredSize(
                new Dimension(750, 350)
        );

        reportScrollPane.setMaximumSize(
                new Dimension(750, 350)
        );

        reportScrollPane.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // UPLOAD BUTTON
        // =====================================================

        uploadButton.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter(
                            "PDF Files",
                            "pdf"
                    );

            chooser.setFileFilter(filter);

            int result =
                    chooser.showOpenDialog(
                            MainFrame.this
                    );

            if (
                    result ==
                            JFileChooser.APPROVE_OPTION
            ) {

                selectedFile =
                        chooser.getSelectedFile();

                statusLabel.setText(
                        "Resume Selected"
                );

                statusLabel.setForeground(
                        new Color(34, 139, 34)
                );

                fileNameLabel.setText(
                        "File Name : "
                                + selectedFile.getName()
                );

                String extension =
                        "Unknown";

                int dot =
                        selectedFile
                                .getName()
                                .lastIndexOf(".");

                if (dot != -1) {

                    extension =
                            selectedFile
                                    .getName()
                                    .substring(
                                            dot + 1
                                    )
                                    .toUpperCase();
                }

                fileTypeLabel.setText(
                        "File Type : "
                                + extension
                );

                long kb =
                        selectedFile.length()
                                / 1024;

                fileSizeLabel.setText(
                        "File Size : "
                                + kb
                                + " KB"
                );

                analyzeButton.setEnabled(true);

                downloadButton.setEnabled(false);
                exportButton.setEnabled(false);
                improvementButton.setEnabled(false);
                jobDescriptionButton.setEnabled(false);
                analyzedResumeText = "";

                scoreBar.setValue(0);
                scoreBar.setString(
                        "Resume Score"
                );

                atsScoreBar.setValue(0);
                atsScoreBar.setString(
                        "ATS Compatibility"
                );

                reportArea.setText("");

                careerAreaValue.setText("-");
                scoreValue.setText("-");
                bestRoleValue.setText("-");
                skillsValue.setText("-");
                missingSkillsValue.setText("-");
                atsScoreValue.setText("-");
                atsKeywordScoreValue.setText("-");
                atsKeywordsFoundValue.setText("-");
                atsKeywordsMissingValue.setText("-");

                improvementSuggestionsValue.setText(
                        "<html>-</html>"
                );

                atsRecommendationValue.setText(
                        "<html>-</html>"
                );
            }
        });
        // =====================================================
        // ANALYZE BUTTON
        // =====================================================

        analyzeButton.addActionListener(e -> {

            if (selectedFile == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please upload a resume first."
                );

                return;
            }

            statusLabel.setText(
                    "Analyzing Resume..."
            );

            statusLabel.setForeground(
                    Color.BLUE
            );

            try {

                // =================================================
                // READ PDF
                // =================================================

                PDFReader reader =
                        new PDFReader();

                String resumeText =
                        reader.extractText(
                                selectedFile
                        );

                if (
                        resumeText == null
                                ||
                                resumeText.trim().isEmpty()
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No readable text was found in this PDF."
                    );

                    statusLabel.setText(
                            "Unable to analyze resume"
                    );

                    statusLabel.setForeground(
                            Color.RED
                    );

                    return;
                }

                // =================================================
                // RESUME ANALYSIS
                // =================================================

                ResumeAnalyzer analyzer =
                        new ResumeAnalyzer();

                String report =
                        analyzer.analyzeResume(
                                resumeText
                        );

                analyzedResumeText = resumeText;

                reportArea.setText(report);
                reportArea.setCaretPosition(0);

                downloadButton.setEnabled(true);
                exportButton.setEnabled(true);
                jobDescriptionButton.setEnabled(true);
                improvementButton.setEnabled(true);

                int score = 0;

                String careerArea = "-";

                String recommendedRole = "";

                String skillsFound = "";

                String missingSkills = "-";

                // =================================================
                // EXTRACT RESUME INFORMATION
                // =================================================

                try {

                    // -------------------------------
                    // SCORE
                    // -------------------------------

                    int scoreStart =
                            report.indexOf(
                                    "Resume Score : "
                            );

                    if (scoreStart != -1) {

                        scoreStart +=
                                "Resume Score : "
                                        .length();

                        int scoreEnd =
                                report.indexOf(
                                        "/100",
                                        scoreStart
                                );

                        if (scoreEnd != -1) {

                            score =
                                    Integer.parseInt(
                                            report.substring(
                                                    scoreStart,
                                                    scoreEnd
                                            ).trim()
                                    );
                        }
                    }

                    // -------------------------------
                    // CAREER AREA
                    // -------------------------------

                    int careerStart =
                            report.indexOf(
                                    "Career Area : "
                            );

                    if (careerStart != -1) {

                        careerStart +=
                                "Career Area : "
                                        .length();

                        int careerEnd =
                                report.indexOf(
                                        "\n",
                                        careerStart
                                );

                        if (careerEnd != -1) {

                            careerArea =
                                    report.substring(
                                            careerStart,
                                            careerEnd
                                    ).trim();
                        }
                    }

                    // -------------------------------
                    // JOB ROLE
                    // -------------------------------

                    int roleStart =
                            report.indexOf(
                                    "Recommended Job Roles:"
                            );

                    if (roleStart != -1) {

                        roleStart +=
                                "Recommended Job Roles:"
                                        .length();

                        int roleEnd =
                                report.indexOf(
                                        "Suggestions:",
                                        roleStart
                                );

                        if (roleEnd != -1) {

                            recommendedRole =
                                    report.substring(
                                            roleStart,
                                            roleEnd
                                    ).trim();
                        }
                    }

                    // -------------------------------
                    // SKILLS
                    // -------------------------------

                    int skillsStart =
                            report.indexOf(
                                    "Skills Found:"
                            );

                    if (skillsStart != -1) {

                        skillsStart +=
                                "Skills Found:"
                                        .length();

                        int skillsEnd =
                                report.indexOf(
                                        "Missing Skills:",
                                        skillsStart
                                );

                        if (skillsEnd != -1) {

                            skillsFound =
                                    report.substring(
                                            skillsStart,
                                            skillsEnd
                                    ).trim();
                        }
                    }

                    // -------------------------------
                    // MISSING SKILLS
                    // -------------------------------

                    int missingStart =
                            report.indexOf(
                                    "Missing Skills:"
                            );

                    if (missingStart != -1) {

                        missingStart +=
                                "Missing Skills:"
                                        .length();

                        int missingEnd =
                                report.indexOf(
                                        "Recommended Job Roles:",
                                        missingStart
                                );

                        if (missingEnd != -1) {

                            missingSkills =
                                    report.substring(
                                            missingStart,
                                            missingEnd
                                    ).trim();
                        }
                    }

                    // -------------------------------
                    // UPDATE SUMMARY
                    // -------------------------------

                    careerAreaValue.setText(
                            careerArea
                    );

                    scoreValue.setText(
                            score + "/100"
                    );

                    String bestRole =
                            recommendedRole;

                    if (bestRole.contains("\n")) {

                        bestRole =
                                bestRole
                                        .split("\n")[0];
                    }

                    bestRole =
                            bestRole
                                    .replace(
                                            "🥇",
                                            ""
                                    )
                                    .trim();

                    bestRoleValue.setText(
                            bestRole.isEmpty()
                                    ? "-"
                                    : bestRole
                    );

                    skillsValue.setText(
                            skillsFound.isEmpty()
                                    ? "-"
                                    : skillsFound
                    );

                    missingSkillsValue.setText(
                            missingSkills.isEmpty()
                                    ? "-"
                                    : missingSkills
                    );

                } catch (
                        Exception extractionError
                ) {

                    extractionError.printStackTrace();
                }

                // =================================================
                // RESUME SCORE BAR
                // =================================================

                scoreBar.setValue(score);

                scoreBar.setString(
                        score + "%"
                );

                if (score >= 80) {

                    scoreBar.setForeground(
                            new Color(
                                    34,
                                    139,
                                    34
                            )
                    );

                } else if (score >= 50) {

                    scoreBar.setForeground(
                            Color.ORANGE
                    );

                } else {

                    scoreBar.setForeground(
                            Color.RED
                    );
                }

                // =================================================
                // ATS ANALYZER
                // =================================================

                ATSKeywordAnalyzer atsAnalyzer =
                        new ATSKeywordAnalyzer();

                ATSKeywordAnalyzer.ATSResult atsResult =
                        atsAnalyzer.analyze(
                                resumeText,
                                careerArea
                        );

                // =================================================
                // ATS SCORE
                // =================================================

                int atsScore =
                        atsResult.getScore();

                atsScoreValue.setText(
                        atsScore + "/100"
                );

                atsScoreBar.setValue(
                        atsScore
                );

                atsScoreBar.setString(
                        "ATS Compatibility: "
                                + atsScore
                                + "%"
                );

                if (atsScore >= 80) {

                    atsScoreBar.setForeground(
                            new Color(
                                    34,
                                    139,
                                    34
                            )
                    );

                } else if (atsScore >= 50) {

                    atsScoreBar.setForeground(
                            Color.ORANGE
                    );

                } else {

                    atsScoreBar.setForeground(
                            Color.RED
                    );
                }

                // =================================================
                // ATS KEYWORDS
                // =================================================

                List<String> foundATSKeywords =
                        atsResult.getFoundKeywords();

                List<String> missingATSKeywords =
                        atsResult.getMissingKeywords();

                int atsKeywordScore =
                        atsScore;

                /*
                 * We use the ATSKeywordAnalyzer score as the
                 * keyword match score because it is directly
                 * calculated from role-specific keywords.
                 */

                atsKeywordScoreValue.setText(
                        atsKeywordScore + "/100"
                );

                // =================================================
                // DISPLAY FOUND ATS KEYWORDS
                // =================================================

                StringBuilder foundHTML =
                        new StringBuilder(
                                "<html><div style='width:250px;'>"
                        );

                if (
                        foundATSKeywords == null
                                ||
                                foundATSKeywords.isEmpty()
                ) {

                    foundHTML.append("-");

                } else {

                    for (
                            int i = 0;
                            i < foundATSKeywords.size();
                            i++
                    ) {

                        foundHTML
                                .append(
                                        foundATSKeywords.get(i)
                                );

                        if (
                                i <
                                        foundATSKeywords.size() - 1
                        ) {

                            foundHTML.append(", ");
                        }
                    }
                }

                foundHTML.append(
                        "</div></html>"
                );

                atsKeywordsFoundValue.setText(
                        foundHTML.toString()
                );

                // =================================================
                // DISPLAY MISSING ATS KEYWORDS
                // =================================================

                StringBuilder missingHTML =
                        new StringBuilder(
                                "<html><div style='width:250px;'>"
                        );

                if (
                        missingATSKeywords == null
                                ||
                                missingATSKeywords.isEmpty()
                ) {

                    missingHTML.append("-");

                } else {

                    for (
                            int i = 0;
                            i < missingATSKeywords.size();
                            i++
                    ) {

                        missingHTML
                                .append(
                                        missingATSKeywords.get(i)
                                );

                        if (
                                i <
                                        missingATSKeywords.size() - 1
                        ) {

                            missingHTML.append(", ");
                        }
                    }
                }

                missingHTML.append(
                        "</div></html>"
                );

                atsKeywordsMissingValue.setText(
                        missingHTML.toString()
                );

                // =================================================
                // ATS RECOMMENDATION
                // =================================================

                String atsRecommendation =
                        atsResult.getRecommendation();

                if (
                        atsRecommendation == null
                                ||
                                atsRecommendation.trim().isEmpty()
                ) {

                    atsRecommendation =
                            "-";
                }

                atsRecommendationValue.setText(
                        "<html><div style='width:250px;'>"
                                + atsRecommendation
                                + "</div></html>"
                );

                // =====================================================
// RESUME IMPROVEMENT SUGGESTIONS
// =====================================================

                try {

                    ResumeImprovementService improvementService =
                            new ResumeImprovementService();

                    java.util.List<String> missingSkillList =
                            new java.util.ArrayList<>();

                    if (
                            missingSkills != null
                                    &&
                                    !missingSkills.trim().isEmpty()
                                    &&
                                    !missingSkills.equals("-")
                    ) {

                        String[] skillArray =
                                missingSkills
                                        .split("[,\\n]+");

                        for (String skill : skillArray) {

                            skill = skill.trim();

                            if (!skill.isEmpty()) {

                                missingSkillList.add(skill);
                            }
                        }
                    }

                    java.util.List<String> missingATSKeywordList =
                            atsResult.getMissingKeywords();

                    java.util.List<String> suggestions =
                            improvementService.generateSuggestions(
                                    score,
                                    atsScore,
                                    atsKeywordScore,
                                    missingSkillList,
                                    missingATSKeywordList
                            );

                    StringBuilder suggestionHTML =
                            new StringBuilder();

                    suggestionHTML.append(
                            "<html><div style='width:250px;'>"
                    );

                    for (String suggestion : suggestions) {

                        suggestionHTML.append(
                                "• "
                                        + suggestion
                                        + "<br><br>"
                        );
                    }

                    suggestionHTML.append(
                            "</div></html>"
                    );

                    improvementSuggestionsValue.setText(
                            suggestionHTML.toString()
                    );

                } catch (Exception improvementError) {

                    improvementError.printStackTrace();

                    improvementSuggestionsValue.setText(
                            "<html>Unable to generate improvement suggestions.</html>"
                    );
                }
                // =================================================
                // PREPARE MISSING SKILLS LIST
                // =================================================

                List<String> missingSkillList =
                        new ArrayList<>();

                if (
                        missingSkills != null
                                &&
                                !missingSkills.equals("-")
                                &&
                                !missingSkills.trim().isEmpty()
                ) {

                    String[] skillArray =
                            missingSkills.split(",");

                    for (String skill : skillArray) {

                        String cleanedSkill =
                                skill.trim();

                        if (
                                !cleanedSkill.isEmpty()
                        ) {

                            missingSkillList.add(
                                    cleanedSkill
                            );
                        }
                    }
                }

                // =================================================
                // GENERATE IMPROVEMENT SUGGESTIONS
                // =================================================

                ResumeImprovementService
                        improvementService =
                        new ResumeImprovementService();

                List<String>
                        improvementSuggestions =
                        improvementService.generateSuggestions(
                                score,
                                atsScore,
                                atsKeywordScore,
                                missingSkillList,
                                missingATSKeywords
                        );

                // =================================================
                // DISPLAY IMPROVEMENT SUGGESTIONS
                // =================================================

                StringBuilder suggestionsHTML =
                        new StringBuilder(
                                "<html><div style='width:250px;'>"
                        );

                if (
                        improvementSuggestions == null
                                ||
                                improvementSuggestions.isEmpty()
                ) {

                    suggestionsHTML.append("-");

                } else {

                    for (
                            String suggestion :
                            improvementSuggestions
                    ) {

                        suggestionsHTML
                                .append("• ")
                                .append(suggestion)
                                .append("<br><br>");
                    }
                }

                suggestionsHTML.append(
                        "</div></html>"
                );

                improvementSuggestionsValue.setText(
                        suggestionsHTML.toString()
                );

                // =================================================
                // SAVE TO DATABASE
                // =================================================

                ResumeDAO dao =
                        new ResumeDAO();

                boolean saved =
                        dao.saveResumeAnalysis(
                                selectedFile.getName(),
                                score,
                                atsScore,
                                recommendedRole,
                                skillsFound
                        );

                if (saved) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Resume analysis saved successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Resume analyzed, but database saving failed."
                    );
                }

                // =================================================
                // FINAL STATUS
                // =================================================

                statusLabel.setText(
                        "Resume Analyzed Successfully"
                );

                statusLabel.setForeground(
                        new Color(
                                34,
                                139,
                                34
                        )
                );
            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error while analyzing resume:\n"
                                + ex.getMessage(),
                        "Analysis Error",
                        JOptionPane.ERROR_MESSAGE
                );

                statusLabel.setText(
                        "Analysis Failed"
                );

                statusLabel.setForeground(
                        Color.RED
                );
            }
        });
// =====================================================
// JOB DESCRIPTION MATCHER
// =====================================================
        jobDescriptionButton.addActionListener(e -> {

            if (
                    analyzedResumeText == null
                            ||
                            analyzedResumeText.trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please analyze a resume first."
                );

                return;
            }

            if (selectedFile == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a resume first."
                );

                return;
            }

            new JobDescriptionFrame(
                    analyzedResumeText,
                    selectedFile.getName()
            );
        });
        // =====================================================
// RESUME IMPROVEMENT BUTTON
// =====================================================

        improvementButton.addActionListener(e -> {

            if (analyzedResumeText == null ||
                    analyzedResumeText.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please analyze a resume first."
                );

                return;
            }

            try {

                ResumeImprovementService service =
                        new ResumeImprovementService();

                List<String> missingSkillsList =
                        new ArrayList<>();

                List<String> missingATSKeywordsList =
                        new ArrayList<>();

                // Convert the existing missing-skills text
                // into a list.

                if (
                        missingSkillsValue.getText() != null
                                &&
                                !missingSkillsValue.getText()
                                        .trim()
                                        .equals("-")
                ) {

                    String[] skills =
                            missingSkillsValue
                                    .getText()
                                    .split(",");

                    for (String skill : skills) {

                        if (!skill.trim().isEmpty()) {

                            missingSkillsList.add(
                                    skill.trim()
                            );
                        }
                    }
                }

                // Convert the existing ATS missing-keywords
                // text into a list.

                String atsMissingText =
                        atsKeywordsMissingValue
                                .getText()
                                .replaceAll(
                                        "<[^>]*>",
                                        ""
                                )
                                .trim();

                if (
                        !atsMissingText.isEmpty()
                                &&
                                !atsMissingText.equals("-")
                ) {

                    String[] keywords =
                            atsMissingText.split(",");

                    for (String keyword : keywords) {

                        if (!keyword.trim().isEmpty()) {

                            missingATSKeywordsList.add(
                                    keyword.trim()
                            );
                        }
                    }
                }

                List<String> suggestions =
                        service.generateSuggestions(
                                Integer.parseInt(
                                        scoreValue.getText()
                                                .replace(
                                                        "/100",
                                                        ""
                                                )
                                                .trim()
                                ),
                                Integer.parseInt(
                                        atsScoreValue.getText()
                                                .replace(
                                                        "/100",
                                                        ""
                                                )
                                                .trim()
                                ),
                                Integer.parseInt(
                                        atsKeywordScoreValue
                                                .getText()
                                                .replace(
                                                        "/100",
                                                        ""
                                                )
                                                .trim()
                                ),
                                missingSkillsList,
                                missingATSKeywordsList
                        );

                new ResumeImprovementFrame(
                        Integer.parseInt(
                                scoreValue.getText()
                                        .replace(
                                                "/100",
                                                ""
                                        )
                                        .trim()
                        ),
                        Integer.parseInt(
                                atsScoreValue.getText()
                                        .replace(
                                                "/100",
                                                ""
                                        )
                                        .trim()
                        ),
                        Integer.parseInt(
                                atsKeywordScoreValue
                                        .getText()
                                        .replace(
                                                "/100",
                                                ""
                                        )
                                        .trim()
                        ),
                        missingSkillsList,
                        missingATSKeywordsList,
                        suggestions
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to open Resume Improvement:\n"
                                + ex.getMessage(),
                        "Improvement Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        // =====================================================
        // HISTORY
        // =====================================================

        historyButton.addActionListener(e ->
                new HistoryFrame()
        );

        // =====================================================
        // DASHBOARD
        // =====================================================

        dashboardButton.addActionListener(e ->
                new DashboardFrame()
        );

        // =====================================================
        // DOWNLOAD PDF
        // =====================================================

        downloadButton.addActionListener(e -> {

            if (
                    reportArea.getText()
                            .trim()
                            .isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please analyze a resume first."
                );

                return;
            }

            JFileChooser chooser =
                    new JFileChooser();

            chooser.setDialogTitle(
                    "Save PDF Report"
            );

            chooser.setSelectedFile(
                    new File(
                            "Resume_Report.pdf"
                    )
            );

            int result =
                    chooser.showSaveDialog(
                            this
                    );

            if (
                    result ==
                            JFileChooser.APPROVE_OPTION
            ) {

                String filePath =
                        chooser
                                .getSelectedFile()
                                .getAbsolutePath();

                if (
                        !filePath
                                .toLowerCase()
                                .endsWith(".pdf")
                ) {

                    filePath += ".pdf";
                }

                PDFReportGenerator generator =
                        new PDFReportGenerator();

                generator.generateReport(
                        reportArea.getText(),
                        filePath
                );

                JOptionPane.showMessageDialog(
                        this,
                        "PDF Report downloaded successfully!"
                );
            }
        });

        // =====================================================
        // EXPORT WORD
        // =====================================================

        exportButton.addActionListener(e -> {

            if (
                    reportArea.getText()
                            .trim()
                            .isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please analyze a resume first."
                );

                return;
            }

            JFileChooser chooser =
                    new JFileChooser();

            chooser.setDialogTitle(
                    "Export Word Report"
            );

            chooser.setSelectedFile(
                    new File(
                            "Resume_Report.docx"
                    )
            );

            int result =
                    chooser.showSaveDialog(
                            this
                    );

            if (
                    result ==
                            JFileChooser.APPROVE_OPTION
            ) {

                String filePath =
                        chooser
                                .getSelectedFile()
                                .getAbsolutePath();

                if (
                        !filePath
                                .toLowerCase()
                                .endsWith(".docx")
                ) {

                    filePath += ".docx";
                }

                WordReportGenerator generator =
                        new WordReportGenerator();

                generator.generateReport(
                        reportArea.getText(),
                        filePath
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Word Report exported successfully!"
                );
            }
        });

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        mainPanel.add(titleLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        mainPanel.add(subtitleLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(buttonPanel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(infoPanel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(scoreBar);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 12)
                )
        );

        mainPanel.add(atsScoreBar);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(summaryPanel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(reportScrollPane);

        // =====================================================
        // MAIN SCROLL PANE
        // =====================================================

        JScrollPane mainScrollPane =
                new JScrollPane(
                        mainPanel
                );

        mainScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        mainScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        mainScrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        mainScrollPane.setViewportView(
                mainPanel
        );

        mainScrollPane.getViewport()
                .setScrollMode(
                        JViewport.BLIT_SCROLL_MODE
                );

        add(mainScrollPane);

        setVisible(true);
    }
}
