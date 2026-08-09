package com.resumeiqai.ui;

import com.resumeiqai.database.ResumeDAO;
import com.resumeiqai.service.PDFReader;
import com.resumeiqai.service.ResumeAnalyzer;
import com.resumeiqai.service.PDFReportGenerator;
import com.resumeiqai.service.WordReportGenerator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private File selectedFile;

    public MainFrame() {

        setTitle("ResumeIQ AI");
        setSize(850, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // =========================
        // Title
        // =========================

        JLabel titleLabel = new JLabel("ResumeIQ AI");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(
                "Analyze your resume and get job recommendations"
        );
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // =========================
        // Resume Information
        // =========================

        JLabel statusLabel = new JLabel("No Resume Selected");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.RED);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fileNameLabel = new JLabel("File Name : -");
        JLabel fileTypeLabel = new JLabel("File Type : -");
        JLabel fileSizeLabel = new JLabel("File Size : -");

        fileNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fileTypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fileSizeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        fileNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileSizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(
                BorderFactory.createTitledBorder("Resume Information")
        );
        infoPanel.setMaximumSize(new Dimension(500, 170));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(statusLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        infoPanel.add(fileNameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(fileTypeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(fileSizeLabel);

        // =========================
        // Score Progress Bar
        // =========================

        JProgressBar scoreBar = new JProgressBar(0, 100);
        scoreBar.setValue(0);
        scoreBar.setString("Resume Score");
        scoreBar.setStringPainted(true);
        scoreBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreBar.setMaximumSize(new Dimension(500, 30));
        scoreBar.setPreferredSize(new Dimension(500, 30));

        // =========================
        // Buttons
        // =========================

        JButton uploadButton = new JButton("Upload Resume");
        uploadButton.setFont(new Font("Arial", Font.BOLD, 15));

        JButton analyzeButton = new JButton("Analyze Resume");
        analyzeButton.setFont(new Font("Arial", Font.BOLD, 15));
        analyzeButton.setEnabled(false);

        JButton historyButton = new JButton("View History");
        historyButton.setFont(new Font("Arial", Font.BOLD, 15));

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.setFont(new Font("Arial", Font.BOLD, 15));

        JButton downloadButton = new JButton("Download PDF");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 15));
        downloadButton.setEnabled(false);

        JButton exportButton = new JButton("Export Word");
        exportButton.setFont(new Font("Arial", Font.BOLD, 15));
        exportButton.setEnabled(false);

        // =========================
        // Report Area
        // =========================

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        reportArea.setLineWrap(true);
        reportArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(750, 250));
        scrollPane.setMaximumSize(new Dimension(750, 250));

        // =========================
        // Button Panel
        // =========================

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 12, 12));
        buttonPanel.setMaximumSize(new Dimension(650, 100));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(uploadButton);
        buttonPanel.add(analyzeButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(dashboardButton);
        buttonPanel.add(downloadButton);
        buttonPanel.add(exportButton);

        // =========================
        // Upload Button
        // =========================

        uploadButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter("PDF Files", "pdf");

            chooser.setFileFilter(filter);

            int result = chooser.showOpenDialog(MainFrame.this);

            if (result == JFileChooser.APPROVE_OPTION) {

                selectedFile = chooser.getSelectedFile();

                statusLabel.setText("Resume Selected");
                statusLabel.setForeground(new Color(34, 139, 34));

                fileNameLabel.setText(
                        "File Name : " + selectedFile.getName()
                );

                String extension = "Unknown";

                int dot = selectedFile.getName().lastIndexOf(".");

                if (dot != -1) {
                    extension = selectedFile.getName()
                            .substring(dot + 1)
                            .toUpperCase();
                }

                fileTypeLabel.setText(
                        "File Type : " + extension
                );

                long kb = selectedFile.length() / 1024;

                fileSizeLabel.setText(
                        "File Size : " + kb + " KB"
                );

                analyzeButton.setEnabled(true);
                downloadButton.setEnabled(false);
                exportButton.setEnabled(false);

                scoreBar.setValue(0);
                scoreBar.setString("Resume Score");
                reportArea.setText("");
            }
        });

        // =========================
        // Analyze Button
        // =========================

        analyzeButton.addActionListener(e -> {

            if (selectedFile == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please upload a resume first."
                );

                return;
            }

            statusLabel.setText("Analyzing Resume...");
            statusLabel.setForeground(Color.BLUE);

            try {

                PDFReader reader = new PDFReader();

                String resumeText = reader.extractText(selectedFile);

                if (resumeText == null || resumeText.trim().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No readable text was found in this PDF."
                    );

                    statusLabel.setText("Unable to analyze resume");
                    statusLabel.setForeground(Color.RED);

                    return;
                }

                ResumeAnalyzer analyzer = new ResumeAnalyzer();

                String report = analyzer.analyzeResume(resumeText);

                reportArea.setText(report);
                reportArea.setCaretPosition(0);

                downloadButton.setEnabled(true);
                exportButton.setEnabled(true);

                int score = 0;
                String recommendedRole = "";
                String skillsFound = "";

                // =========================
                // Extract Score, Role & Skills
                // =========================

                try {

                    int scoreStart = report.indexOf("Resume Score : ");

                    if (scoreStart != -1) {

                        scoreStart += "Resume Score : ".length();

                        int scoreEnd = report.indexOf(
                                "/100",
                                scoreStart
                        );

                        if (scoreEnd != -1) {

                            score = Integer.parseInt(
                                    report.substring(
                                            scoreStart,
                                            scoreEnd
                                    ).trim()
                            );
                        }
                    }

                    int roleStart = report.indexOf(
                            "Recommended Job Roles:"
                    );

                    if (roleStart != -1) {

                        roleStart +=
                                "Recommended Job Roles:".length();

                        int roleEnd = report.indexOf(
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

                    int skillsStart = report.indexOf(
                            "Skills Found:"
                    );

                    if (skillsStart != -1) {

                        skillsStart +=
                                "Skills Found:".length();

                        int skillsEnd = report.indexOf(
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

                } catch (Exception extractionError) {

                    extractionError.printStackTrace();
                }

                // =========================
                // Update Score Bar
                // =========================

                scoreBar.setValue(score);
                scoreBar.setString(score + "%");

                if (score >= 80) {

                    scoreBar.setForeground(
                            new Color(34, 139, 34)
                    );

                } else if (score >= 50) {

                    scoreBar.setForeground(Color.ORANGE);

                } else {

                    scoreBar.setForeground(Color.RED);
                }

                // =========================
                // Save Analysis to Database
                // =========================

                ResumeDAO dao = new ResumeDAO();

                boolean saved = dao.saveResumeAnalysis(
                        selectedFile.getName(),
                        score,
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

                statusLabel.setText(
                        "Resume Analyzed Successfully"
                );
                statusLabel.setForeground(
                        new Color(34, 139, 34)
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
                statusLabel.setForeground(Color.RED);
            }
        });

        // =========================
        // History Button
        // =========================

        historyButton.addActionListener(e -> {
            new HistoryFrame();
        });

        // =========================
        // Dashboard Button
        // =========================

        dashboardButton.addActionListener(e -> {
            new DashboardFrame();
        });

        // =========================
        // Download PDF Report
        // =========================

        downloadButton.addActionListener(e -> {

            if (reportArea.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please analyze a resume first."
                );

                return;
            }

            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Save PDF Report");

            chooser.setSelectedFile(
                    new File("Resume_Report.pdf")
            );

            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                String filePath =
                        chooser.getSelectedFile()
                                .getAbsolutePath();

                if (!filePath.toLowerCase().endsWith(".pdf")) {
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

        // =========================
        // Export Word Report
        // =========================

        exportButton.addActionListener(e -> {

            if (reportArea.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please analyze a resume first."
                );

                return;
            }

            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Export Word Report");

            chooser.setSelectedFile(
                    new File("Resume_Report.docx")
            );

            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                String filePath =
                        chooser.getSelectedFile()
                                .getAbsolutePath();

                if (!filePath.toLowerCase().endsWith(".docx")) {
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

        // =========================
        // Main Layout
        // =========================

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
                        new Dimension(0, 20)
                )
        );

        mainPanel.add(scrollPane);

        add(mainPanel);

        setVisible(true);
    }
}
