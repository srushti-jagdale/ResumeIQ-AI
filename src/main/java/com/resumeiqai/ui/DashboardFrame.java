package com.resumeiqai.ui;

import com.resumeiqai.database.DashboardDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private DashboardCard totalCard;
    private DashboardCard averageCard;
    private DashboardCard highestCard;
    private DashboardCard roleCard;

    public DashboardFrame() {

        // ================= WINDOW SETTINGS =================

        setTitle("ResumeIQ AI Dashboard");
        setSize(850, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ================= TITLE =================

        JLabel title = new JLabel(
                "ResumeIQ AI Dashboard",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 0, 20, 0
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // ================= CONTENT PANEL =================

        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(Color.WHITE);

        // ================= DASHBOARD CARDS =================

        JPanel cardPanel = new JPanel(
                new GridLayout(
                        2,
                        2,
                        20,
                        20
                )
        );

        cardPanel.setBackground(Color.WHITE);

        cardPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 20, 20
                )
        );

        cardPanel.setMaximumSize(
                new Dimension(
                        800,
                        250
                )
        );

        // ================= TOTAL RESUMES =================

        totalCard = new DashboardCard(
                "Total Resumes",
                "0",
                new Color(173, 216, 230)
        );

        // ================= AVERAGE SCORE =================

        averageCard = new DashboardCard(
                "Average Score",
                "0%",
                new Color(255, 228, 181)
        );

        // ================= HIGHEST SCORE =================

        highestCard = new DashboardCard(
                "Highest Score",
                "0%",
                new Color(144, 238, 144)
        );

        // ================= TOP ROLE =================

        roleCard = new DashboardCard(
                "Top Role",
                "-",
                new Color(221, 160, 221)
        );

        // ================= ADD CARDS =================

        cardPanel.add(totalCard);
        cardPanel.add(averageCard);
        cardPanel.add(highestCard);
        cardPanel.add(roleCard);

        contentPanel.add(cardPanel);

        // ================= SPACE =================

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 10)
                )
        );

        // ================= CHART =================



        ScoreChartPanel chartPanel =
                new ScoreChartPanel();

        chartPanel.setPreferredSize(
                new Dimension(
                        750,
                        300
                )
        );

        chartPanel.setMaximumSize(
                new Dimension(
                        800,
                        320
                )
        );

        chartPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        contentPanel.add(chartPanel);

        // ================= SCROLL SUPPORT =================

        JScrollPane scrollPane =
                new JScrollPane(
                        contentPanel
                );

        scrollPane.setBorder(null);

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ================= ADD MAIN PANEL =================

        add(mainPanel);

        // ================= LOAD DATABASE DATA =================

        loadDashboardData();

        // ================= SHOW WINDOW =================

        setVisible(true);
    }

    // =========================================================
    // LOAD DASHBOARD DATA
    // =========================================================

    private void loadDashboardData() {

        DashboardDAO dao =
                new DashboardDAO();

        // Total resumes

        totalCard.setValue(
                String.valueOf(
                        dao.getTotalResumes()
                )
        );

        // Average score

        averageCard.setValue(
                String.format(
                        "%.2f%%",
                        dao.getAverageScore()
                )
        );

        // Highest score

        highestCard.setValue(
                dao.getHighestScore() + "%"
        );

        // Top role

        roleCard.setValue(
                dao.getTopRole()
        );
    }
}