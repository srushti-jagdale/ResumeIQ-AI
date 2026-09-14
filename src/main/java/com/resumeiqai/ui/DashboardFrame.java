package com.resumeiqai.ui;

import com.resumeiqai.database.DashboardDAO;
import com.resumeiqai.database.JobMatchDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    // =========================================================
    // RESUME DASHBOARD CARDS
    // =========================================================

    private DashboardCard totalCard;
    private DashboardCard averageCard;
    private DashboardCard highestCard;
    private DashboardCard roleCard;

    private DashboardCard averageATSCard;
    private DashboardCard highestATSCard;

    // =========================================================
    // JOB MATCH DASHBOARD CARDS
    // =========================================================

    private DashboardCard totalJobMatchCard;
    private DashboardCard averageJobMatchCard;
    private DashboardCard highestJobMatchCard;

    // =========================================================
    // CHART CONTAINERS
    // =========================================================

    private JPanel scoreChartContainer;
    private JPanel atsChartContainer;
    private JPanel jobMatchChartContainer;

    public DashboardFrame() {

        // =========================================================
        // WINDOW SETTINGS
        // =========================================================

        setTitle("ResumeIQ AI Dashboard");
        setSize(950, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // =========================================================
        // MAIN PANEL
        // =========================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        // =========================================================
        // HEADER
        // =========================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                new Color(
                        36,
                        59,
                        83
                )
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        40,
                        25,
                        40
                )
        );

        // =========================================================
        // HEADER TEXT PANEL
        // =========================================================

        JPanel headerTextPanel =
                new JPanel();

        headerTextPanel.setLayout(
                new BoxLayout(
                        headerTextPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerTextPanel.setOpaque(false);

        JLabel titleLabel =
                new JLabel(
                        "ResumeIQ AI Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Resume analysis statistics, ATS insights and job match performance"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        235,
                        240,
                        245
                )
        );

        headerTextPanel.add(
                titleLabel
        );

        headerTextPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                8
                        )
                )
        );

        headerTextPanel.add(
                subtitleLabel
        );

        // =========================================================
        // REFRESH BUTTON
        // =========================================================

        JButton refreshButton =
                new JButton(
                        "Refresh Dashboard"
                );

        refreshButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        refreshButton.setPreferredSize(
                new Dimension(
                        180,
                        50
                )
        );

        refreshButton.setFocusPainted(false);

        headerPanel.add(
                headerTextPanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                refreshButton,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================================================
        // CONTENT PANEL
        // =========================================================

        JPanel contentPanel =
                new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        30,
                        30
                )
        );

        // =========================================================
        // RESUME STATISTICS TITLE
        // =========================================================

        JLabel statisticsTitle =
                new JLabel(
                        "Resume Statistics"
                );

        statisticsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        statisticsTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(
                statisticsTitle
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                20
                        )
                )
        );

        // =========================================================
        // RESUME DASHBOARD CARDS PANEL
        // =========================================================

        JPanel cardPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                25,
                                25
                        )
                );

        cardPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        cardPanel.setPreferredSize(
                new Dimension(
                        820,
                        300
                )
        );

        cardPanel.setMaximumSize(
                new Dimension(
                        820,
                        300
                )
        );

        cardPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================================================
        // RESUME DASHBOARD CARDS
        // =========================================================

        totalCard =
                new DashboardCard(
                        "Total Resumes",
                        "0",
                        new Color(
                                173,
                                216,
                                230
                        )
                );

        averageCard =
                new DashboardCard(
                        "Average Score",
                        "0%",
                        new Color(
                                255,
                                228,
                                181
                        )
                );

        highestCard =
                new DashboardCard(
                        "Highest Score",
                        "0%",
                        new Color(
                                144,
                                238,
                                144
                        )
                );

        roleCard =
                new DashboardCard(
                        "Top Role",
                        "-",
                        new Color(
                                221,
                                160,
                                221
                        )
                );

        averageATSCard =
                new DashboardCard(
                        "Average ATS Score",
                        "0%",
                        new Color(
                                173,
                                216,
                                230
                        )
                );

        highestATSCard =
                new DashboardCard(
                        "Highest ATS Score",
                        "0%",
                        new Color(
                                144,
                                238,
                                144
                        )
                );

        cardPanel.add(totalCard);
        cardPanel.add(averageCard);
        cardPanel.add(highestCard);
        cardPanel.add(roleCard);
        cardPanel.add(averageATSCard);
        cardPanel.add(highestATSCard);

        contentPanel.add(
                cardPanel
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                30
                        )
                )
        );

        // =========================================================
        // RESUME SCORE CHART TITLE
        // =========================================================

        JLabel scoreChartTitle =
                new JLabel(
                        "Resume Score Distribution"
                );

        scoreChartTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        scoreChartTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(
                scoreChartTitle
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                10
                        )
                )
        );

        // =========================================================
        // RESUME SCORE CHART
        // =========================================================

        scoreChartContainer =
                new JPanel(
                        new BorderLayout()
                );

        scoreChartContainer.setBackground(
                Color.WHITE
        );

        scoreChartContainer.setPreferredSize(
                new Dimension(
                        820,
                        330
                )
        );

        scoreChartContainer.setMaximumSize(
                new Dimension(
                        820,
                        330
                )
        );

        scoreChartContainer.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loadScoreChart();

        contentPanel.add(
                scoreChartContainer
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                40
                        )
                )
        );

        // =========================================================
        // ATS SCORE CHART TITLE
        // =========================================================

        JLabel atsChartTitle =
                new JLabel(
                        "ATS Score Distribution"
                );

        atsChartTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        atsChartTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(
                atsChartTitle
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                10
                        )
                )
        );

        // =========================================================
        // ATS SCORE CHART
        // =========================================================

        atsChartContainer =
                new JPanel(
                        new BorderLayout()
                );

        atsChartContainer.setBackground(
                Color.WHITE
        );

        atsChartContainer.setPreferredSize(
                new Dimension(
                        820,
                        330
                )
        );

        atsChartContainer.setMaximumSize(
                new Dimension(
                        820,
                        330
                )
        );

        atsChartContainer.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loadATSChart();

        contentPanel.add(
                atsChartContainer
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                50
                        )
                )
        );

        // =========================================================
        // JOB MATCH STATISTICS TITLE
        // =========================================================

        JLabel jobMatchTitle =
                new JLabel(
                        "Job Match Statistics"
                );

        jobMatchTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        jobMatchTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(
                jobMatchTitle
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                20
                        )
                )
        );

        // =========================================================
        // JOB MATCH CARDS PANEL
        // =========================================================

        JPanel jobMatchCardPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                25,
                                25
                        )
                );

        jobMatchCardPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        jobMatchCardPanel.setPreferredSize(
                new Dimension(
                        820,
                        140
                )
        );

        jobMatchCardPanel.setMaximumSize(
                new Dimension(
                        820,
                        140
                )
        );

        jobMatchCardPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================================================
        // JOB MATCH CARDS
        // =========================================================

        totalJobMatchCard =
                new DashboardCard(
                        "Total Job Matches",
                        "0",
                        new Color(
                                255,
                                218,
                                185
                        )
                );

        averageJobMatchCard =
                new DashboardCard(
                        "Average Job Match",
                        "0%",
                        new Color(
                                176,
                                224,
                                230
                        )
                );

        highestJobMatchCard =
                new DashboardCard(
                        "Highest Job Match",
                        "0%",
                        new Color(
                                152,
                                251,
                                152
                        )
                );

        jobMatchCardPanel.add(
                totalJobMatchCard
        );

        jobMatchCardPanel.add(
                averageJobMatchCard
        );

        jobMatchCardPanel.add(
                highestJobMatchCard
        );

        contentPanel.add(
                jobMatchCardPanel
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                30
                        )
                )
        );

        // =========================================================
        // JOB MATCH CHART TITLE
        // =========================================================

        JLabel jobMatchChartTitle =
                new JLabel(
                        "Job Match Score Distribution"
                );

        jobMatchChartTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        jobMatchChartTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(
                jobMatchChartTitle
        );

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(
                                0,
                                10
                        )
                )
        );

        // =========================================================
        // JOB MATCH CHART CONTAINER
        // =========================================================

        jobMatchChartContainer =
                new JPanel(
                        new BorderLayout()
                );

        jobMatchChartContainer.setBackground(
                Color.WHITE
        );

        jobMatchChartContainer.setPreferredSize(
                new Dimension(
                        820,
                        330
                )
        );

        jobMatchChartContainer.setMaximumSize(
                new Dimension(
                        820,
                        330
                )
        );

        jobMatchChartContainer.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loadJobMatchChart();

        contentPanel.add(
                jobMatchChartContainer
        );

        // =========================================================
        // SCROLL PANE
        // =========================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        contentPanel
                );

        scrollPane.setBorder(
                null
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================================================
        // ADD MAIN PANEL
        // =========================================================

        add(
                mainPanel
        );

        // =========================================================
        // LOAD DASHBOARD DATA
        // =========================================================

        loadDashboardData();

        // =========================================================
        // REFRESH BUTTON ACTION
        // =========================================================

        refreshButton.addActionListener(e -> {

            loadDashboardData();

            loadScoreChart();

            loadATSChart();

            loadJobMatchChart();

            JOptionPane.showMessageDialog(
                    this,
                    "Dashboard refreshed successfully!"
            );
        });

        // =========================================================
        // SHOW WINDOW
        // =========================================================

        setVisible(true);
    }

    // =========================================================
    // LOAD DASHBOARD DATA
    // =========================================================

    private void loadDashboardData() {

        DashboardDAO dashboardDAO =
                new DashboardDAO();

        JobMatchDAO jobMatchDAO =
                new JobMatchDAO();

        // =====================================================
        // TOTAL RESUMES
        // =====================================================

        totalCard.setValue(
                String.valueOf(
                        dashboardDAO.getTotalResumes()
                )
        );

        // =====================================================
        // AVERAGE RESUME SCORE
        // =====================================================

        averageCard.setValue(
                String.format(
                        "%.2f%%",
                        dashboardDAO.getAverageScore()
                )
        );

        // =====================================================
        // HIGHEST RESUME SCORE
        // =====================================================

        highestCard.setValue(
                dashboardDAO.getHighestScore()
                        + "%"
        );

        // =====================================================
        // TOP ROLE
        // =====================================================

        roleCard.setValue(
                dashboardDAO.getTopRole()
        );

        // =====================================================
        // AVERAGE ATS SCORE
        // =====================================================

        averageATSCard.setValue(
                String.format(
                        "%.2f%%",
                        dashboardDAO.getAverageATSScore()
                )
        );

        // =====================================================
        // HIGHEST ATS SCORE
        // =====================================================

        highestATSCard.setValue(
                dashboardDAO.getHighestATSScore()
                        + "%"
        );

        // =====================================================
        // TOTAL JOB MATCHES
        // =====================================================

        totalJobMatchCard.setValue(
                String.valueOf(
                        jobMatchDAO.getTotalJobMatches()
                )
        );

        // =====================================================
        // AVERAGE JOB MATCH SCORE
        // =====================================================

        averageJobMatchCard.setValue(
                String.format(
                        "%.2f%%",
                        jobMatchDAO.getAverageJobMatchScore()
                )
        );

        // =====================================================
        // HIGHEST JOB MATCH SCORE
        // =====================================================

        highestJobMatchCard.setValue(
                jobMatchDAO.getHighestJobMatchScore()
                        + "%"
        );
    }

    // =========================================================
    // LOAD RESUME SCORE CHART
    // =========================================================

    private void loadScoreChart() {

        scoreChartContainer.removeAll();

        ScoreChartPanel chartPanel =
                new ScoreChartPanel();

        chartPanel.setPreferredSize(
                new Dimension(
                        810,
                        320
                )
        );

        scoreChartContainer.add(
                chartPanel,
                BorderLayout.CENTER
        );

        scoreChartContainer.revalidate();

        scoreChartContainer.repaint();
    }

    // =========================================================
    // LOAD ATS SCORE CHART
    // =========================================================

    private void loadATSChart() {

        atsChartContainer.removeAll();

        ATSScoreChartPanel chartPanel =
                new ATSScoreChartPanel();

        chartPanel.setPreferredSize(
                new Dimension(
                        810,
                        320
                )
        );

        atsChartContainer.add(
                chartPanel,
                BorderLayout.CENTER
        );

        atsChartContainer.revalidate();

        atsChartContainer.repaint();
    }

    // =========================================================
    // LOAD JOB MATCH SCORE CHART
    // =========================================================

    private void loadJobMatchChart() {

        jobMatchChartContainer.removeAll();

        JobMatchScoreChartPanel chartPanel =
                new JobMatchScoreChartPanel();

        chartPanel.setPreferredSize(
                new Dimension(
                        810,
                        320
                )
        );

        jobMatchChartContainer.add(
                chartPanel,
                BorderLayout.CENTER
        );

        jobMatchChartContainer.revalidate();

        jobMatchChartContainer.repaint();
    }
}