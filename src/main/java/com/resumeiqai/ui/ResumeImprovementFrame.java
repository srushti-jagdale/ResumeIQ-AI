package com.resumeiqai.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResumeImprovementFrame extends JFrame {

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ResumeImprovementFrame(
            int resumeScore,
            int atsScore,
            int atsKeywordScore,
            List<String> missingSkills,
            List<String> missingATSKeywords,
            List<String> suggestions) {

        setTitle("Resume Improvement Suggestions");
        setSize(800, 700);
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
                        25,
                        25,
                        25,
                        25
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "Resume Improvement Suggestions"
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

        mainPanel.add(titleLabel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        // =====================================================
        // SCORE PANEL
        // =====================================================

        JPanel scorePanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                15,
                                0
                        )
                );

        scorePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Current Scores"
                )
        );

        JLabel resumeScoreLabel =
                new JLabel(
                        "<html><center>"
                                + "<b>Resume Score</b><br>"
                                + resumeScore
                                + "/100"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        JLabel atsScoreLabel =
                new JLabel(
                        "<html><center>"
                                + "<b>ATS Compatibility</b><br>"
                                + atsScore
                                + "/100"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        JLabel keywordScoreLabel =
                new JLabel(
                        "<html><center>"
                                + "<b>ATS Keyword Match</b><br>"
                                + atsKeywordScore
                                + "/100"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        resumeScoreLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        atsScoreLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        keywordScoreLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        scorePanel.add(resumeScoreLabel);
        scorePanel.add(atsScoreLabel);
        scorePanel.add(keywordScoreLabel);

        scorePanel.setMaximumSize(
                new Dimension(
                        700,
                        100
                )
        );

        mainPanel.add(scorePanel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        // =====================================================
        // MISSING SKILLS
        // =====================================================

        JPanel missingSkillsPanel =
                new JPanel(
                        new BorderLayout()
                );

        missingSkillsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Missing Skills"
                )
        );

        JTextArea missingSkillsArea =
                new JTextArea();

        missingSkillsArea.setEditable(false);
        missingSkillsArea.setLineWrap(true);
        missingSkillsArea.setWrapStyleWord(true);

        if (
                missingSkills != null
                        &&
                        !missingSkills.isEmpty()
        ) {

            for (String skill : missingSkills) {

                missingSkillsArea.append(
                        "• "
                                + skill
                                + "\n"
                );
            }

        } else {

            missingSkillsArea.setText(
                    "No major missing skills detected."
            );
        }

        missingSkillsPanel.add(
                new JScrollPane(
                        missingSkillsArea
                ),
                BorderLayout.CENTER
        );

        missingSkillsPanel.setPreferredSize(
                new Dimension(
                        700,
                        120
                )
        );

        missingSkillsPanel.setMaximumSize(
                new Dimension(
                        700,
                        120
                )
        );

        mainPanel.add(missingSkillsPanel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        // =====================================================
        // MISSING ATS KEYWORDS
        // =====================================================

        JPanel missingKeywordsPanel =
                new JPanel(
                        new BorderLayout()
                );

        missingKeywordsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Missing ATS Keywords"
                )
        );

        JTextArea missingKeywordsArea =
                new JTextArea();

        missingKeywordsArea.setEditable(false);
        missingKeywordsArea.setLineWrap(true);
        missingKeywordsArea.setWrapStyleWord(true);

        if (
                missingATSKeywords != null
                        &&
                        !missingATSKeywords.isEmpty()
        ) {

            for (
                    String keyword :
                    missingATSKeywords
            ) {

                missingKeywordsArea.append(
                        "• "
                                + keyword
                                + "\n"
                );
            }

        } else {

            missingKeywordsArea.setText(
                    "No major ATS keywords are missing."
            );
        }

        missingKeywordsPanel.add(
                new JScrollPane(
                        missingKeywordsArea
                ),
                BorderLayout.CENTER
        );

        missingKeywordsPanel.setPreferredSize(
                new Dimension(
                        700,
                        120
                )
        );

        missingKeywordsPanel.setMaximumSize(
                new Dimension(
                        700,
                        120
                )
        );

        mainPanel.add(missingKeywordsPanel);

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        // =====================================================
        // IMPROVEMENT SUGGESTIONS
        // =====================================================

        JPanel suggestionsPanel =
                new JPanel(
                        new BorderLayout()
                );

        suggestionsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Recommended Improvements"
                )
        );

        JTextArea suggestionsArea =
                new JTextArea();

        suggestionsArea.setEditable(false);

        suggestionsArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        suggestionsArea.setLineWrap(true);
        suggestionsArea.setWrapStyleWord(true);

        if (
                suggestions != null
                        &&
                        !suggestions.isEmpty()
        ) {

            for (
                    int i = 0;
                    i < suggestions.size();
                    i++
            ) {

                suggestionsArea.append(
                        (i + 1)
                                + ". "
                                + suggestions.get(i)
                                + "\n\n"
                );
            }

        } else {

            suggestionsArea.setText(
                    "No improvement suggestions available."
            );
        }

        suggestionsPanel.add(
                new JScrollPane(
                        suggestionsArea
                ),
                BorderLayout.CENTER
        );

        suggestionsPanel.setPreferredSize(
                new Dimension(
                        700,
                        220
                )
        );

        suggestionsPanel.setMaximumSize(
                new Dimension(
                        700,
                        220
                )
        );

        mainPanel.add(suggestionsPanel);

        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        mainPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        JButton closeButton =
                new JButton("Close");

        closeButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        closeButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        mainPanel.add(closeButton);

        // =====================================================
        // SCROLL
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        mainPanel
                );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(scrollPane);

        setVisible(true);
    }
}