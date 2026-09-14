package com.resumeiqai.ui;

import com.resumeiqai.service.JobDescriptionMatcher;
import com.resumeiqai.database.JobMatchDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JobDescriptionFrame extends JFrame {

    private JTextField jobTitleField;
    private JTextArea jobDescriptionArea;
    private JTextArea resultArea;

    private String resumeText;
    private String resumeFileName;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public JobDescriptionFrame(
            String resumeText,
            String resumeFileName) {

        this.resumeText = resumeText;
        this.resumeFileName = resumeFileName;

        setTitle("Job Description Matcher");
        setSize(750, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
                        20,
                        20,
                        20,
                        20
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "Job Description Matcher"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel descriptionLabel =
                new JLabel(
                        "Enter the job title and paste the job description"
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // JOB TITLE LABEL
        // =====================================================

        JLabel jobTitleLabel =
                new JLabel(
                        "Job Title:"
                );

        jobTitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        jobTitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // JOB TITLE FIELD
        // =====================================================

        jobTitleField =
                new JTextField();

        jobTitleField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        jobTitleField.setMaximumSize(
                new Dimension(
                        650,
                        35
                )
        );

        jobTitleField.setPreferredSize(
                new Dimension(
                        650,
                        35
                )
        );

        // =====================================================
        // JOB DESCRIPTION LABEL
        // =====================================================

        JLabel jobDescriptionLabel =
                new JLabel(
                        "Job Description:"
                );

        jobDescriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        jobDescriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // JOB DESCRIPTION AREA
        // =====================================================

        jobDescriptionArea =
                new JTextArea();

        jobDescriptionArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        jobDescriptionArea.setLineWrap(true);
        jobDescriptionArea.setWrapStyleWord(true);

        JScrollPane jobScrollPane =
                new JScrollPane(
                        jobDescriptionArea
                );

        jobScrollPane.setPreferredSize(
                new Dimension(
                        650,
                        180
                )
        );

        jobScrollPane.setMaximumSize(
                new Dimension(
                        650,
                        180
                )
        );

        jobScrollPane.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // MATCH BUTTON
        // =====================================================

        JButton matchButton =
                new JButton(
                        "Analyze Job Match"
                );

        matchButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        matchButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // RESULT AREA
        // =====================================================

        resultArea =
                new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane resultScrollPane =
                new JScrollPane(
                        resultArea
                );

        resultScrollPane.setPreferredSize(
                new Dimension(
                        650,
                        250
                )
        );

        resultScrollPane.setMaximumSize(
                new Dimension(
                        650,
                        250
                )
        );

        resultScrollPane.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // MATCH BUTTON ACTION
        // =====================================================

        matchButton.addActionListener(e -> {

            String jobTitle =
                    jobTitleField
                            .getText()
                            .trim();

            String jobDescription =
                    jobDescriptionArea
                            .getText()
                            .trim();

            // =================================================
            // VALIDATION
            // =================================================

            if (jobTitle.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the job title first."
                );

                return;
            }

            if (jobDescription.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please paste a job description first."
                );

                return;
            }

            if (resumeText == null ||
                    resumeText.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Resume text is not available."
                );

                return;
            }

            try {

                // =============================================
                // ANALYZE MATCH
                // =============================================

                JobDescriptionMatcher matcher =
                        new JobDescriptionMatcher();

                JobDescriptionMatcher.MatchResult result =
                        matcher.analyze(
                                resumeText,
                                jobDescription
                        );

                int score =
                        result.getScore();

                List<String> matched =
                        result.getMatchedKeywords();

                List<String> missing =
                        result.getMissingKeywords();

                // =============================================
                // BUILD RESULT
                // =============================================

                StringBuilder output =
                        new StringBuilder();

                output.append(
                        "========================================\n"
                );

                output.append(
                        "       JOB DESCRIPTION MATCH RESULT\n"
                );

                output.append(
                        "========================================\n\n"
                );

                output.append(
                        "Job Title : "
                                + jobTitle
                                + "\n"
                );

                output.append(
                        "Resume : "
                                + resumeFileName
                                + "\n\n"
                );

                output.append(
                        "Match Score : "
                                + score
                                + "/100\n\n"
                );

                // =============================================
                // MATCHED KEYWORDS
                // =============================================

                output.append(
                        "----------------------------------------\n"
                );

                output.append(
                        "MATCHED KEYWORDS\n"
                );

                output.append(
                        "----------------------------------------\n"
                );

                if (matched.isEmpty()) {

                    output.append(
                            "No matching keywords found.\n"
                    );

                } else {

                    for (String keyword : matched) {

                        output.append(
                                "✓ "
                                        + keyword
                                        + "\n"
                        );
                    }
                }

                output.append("\n");

                // =============================================
                // MISSING KEYWORDS
                // =============================================

                output.append(
                        "----------------------------------------\n"
                );

                output.append(
                        "MISSING KEYWORDS\n"
                );

                output.append(
                        "----------------------------------------\n"
                );

                if (missing.isEmpty()) {

                    output.append(
                            "No missing keywords found.\n"
                    );

                } else {

                    for (String keyword : missing) {

                        output.append(
                                "✗ "
                                        + keyword
                                        + "\n"
                        );
                    }
                }

                output.append("\n");

                // =============================================
                // RECOMMENDATION
                // =============================================

                output.append(
                        "----------------------------------------\n"
                );

                output.append(
                        "RECOMMENDATION\n"
                );

                output.append(
                        "----------------------------------------\n"
                );

                String recommendation =
                        result.getRecommendation();

                output.append(
                        recommendation
                                + "\n"
                );

                resultArea.setText(
                        output.toString()
                );

                resultArea.setCaretPosition(0);

                // =============================================
                // SAVE JOB MATCH TO DATABASE
                // =============================================

                JobMatchDAO jobMatchDAO =
                        new JobMatchDAO();

                String matchedKeywords =
                        String.join(
                                ", ",
                                matched
                        );

                String missingKeywords =
                        String.join(
                                ", ",
                                missing
                        );

                boolean saved =
                        jobMatchDAO.saveJobMatch(
                                resumeFileName,
                                jobTitle,
                                score,
                                matchedKeywords,
                                missingKeywords,
                                recommendation
                        );

                if (saved) {

                    System.out.println(
                            "Job match saved successfully!"
                    );

                } else {

                    System.out.println(
                            "Job match could not be saved."
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error while matching job description:\n"
                                + ex.getMessage(),
                        "Matching Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        mainPanel.add(titleLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 10)
                )
        );

        mainPanel.add(descriptionLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(jobTitleLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        mainPanel.add(jobTitleField);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        mainPanel.add(jobDescriptionLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        mainPanel.add(jobScrollPane);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        mainPanel.add(matchButton);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(resultScrollPane);

        // =====================================================
        // WINDOW
        // =====================================================

        add(mainPanel);

        setVisible(true);
    }
}