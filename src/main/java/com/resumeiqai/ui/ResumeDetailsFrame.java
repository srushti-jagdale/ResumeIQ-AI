package com.resumeiqai.ui;

import com.resumeiqai.database.ResumeDAO;

import javax.swing.*;
import java.awt.*;

public class ResumeDetailsFrame extends JFrame {

    private final ResumeDAO resumeDAO;

    public ResumeDetailsFrame(int resumeId) {

        resumeDAO = new ResumeDAO();

        setTitle("ResumeIQ AI - Resume Details");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
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
                        "Resume Analysis Details"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =====================================================
        // DETAILS AREA
        // =====================================================

        JTextArea detailsArea =
                new JTextArea();

        detailsArea.setEditable(false);

        detailsArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        detailsArea.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        detailsArea
                );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        JButton closeButton =
                new JButton(
                        "Close"
                );

        closeButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        closeButton.addActionListener(e ->
                dispose()
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        bottomPanel.add(
                closeButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // LOAD DETAILS
        // =====================================================

        loadDetails(
                resumeId,
                detailsArea
        );

        // =====================================================
        // SHOW WINDOW
        // =====================================================

        add(mainPanel);

        setVisible(true);
    }

    // =========================================================
    // LOAD RESUME DETAILS
    // =========================================================

    private void loadDetails(
            int resumeId,
            JTextArea detailsArea) {

        try {

            String[] details =
                    resumeDAO.getResumeAnalysisById(
                            resumeId
                    );

            if (details == null) {

                detailsArea.setText(
                        "Unable to retrieve resume analysis details."
                );

                return;
            }

            String fileName =
                    getValue(details, 1);

            String resumeScore =
                    getValue(details, 2);

            String atsScore =
                    getValue(details, 3);

            String recommendedRole =
                    getValue(details, 4);

            String skillsFound =
                    getValue(details, 5);

            String analysisDate =
                    getValue(details, 6);

            // =================================================
            // BUILD DETAILS
            // =================================================

            StringBuilder output =
                    new StringBuilder();

            output.append(
                    "==================================================\n"
            );

            output.append(
                    "              RESUME ANALYSIS\n"
            );

            output.append(
                    "==================================================\n\n"
            );

            output.append(
                    "FILE INFORMATION\n"
            );

            output.append(
                    "--------------------------------------------------\n"
            );

            output.append(
                    "File Name       : "
                            + fileName
                            + "\n"
            );

            output.append(
                    "Analysis Date   : "
                            + analysisDate
                            + "\n\n"
            );

            output.append(
                    "SCORES\n"
            );

            output.append(
                    "--------------------------------------------------\n"
            );

            output.append(
                    "Resume Score    : "
                            + resumeScore
                            + "/100\n"
            );

            output.append(
                    "ATS Score       : "
                            + atsScore
                            + "/100\n\n"
            );

            output.append(
                    "RECOMMENDATION\n"
            );

            output.append(
                    "--------------------------------------------------\n"
            );

            output.append(
                    "Recommended Role:\n"
                            + recommendedRole
                            + "\n\n"
            );

            output.append(
                    "SKILLS FOUND\n"
            );

            output.append(
                    "--------------------------------------------------\n"
            );

            output.append(
                    skillsFound
                            + "\n\n"
            );

            output.append(
                    "==================================================\n"
            );

            detailsArea.setText(
                    output.toString()
            );

            detailsArea.setCaretPosition(0);

        } catch (Exception ex) {

            ex.printStackTrace();

            detailsArea.setText(
                    "Error while loading resume details:\n\n"
                            + ex.getMessage()
            );
        }
    }

    // =========================================================
    // SAFE VALUE
    // =========================================================

    private String getValue(
            String[] values,
            int index) {

        if (
                values == null ||
                        index < 0 ||
                        index >= values.length ||
                        values[index] == null ||
                        values[index].trim().isEmpty()
        ) {

            return "-";
        }

        return values[index];
    }
}