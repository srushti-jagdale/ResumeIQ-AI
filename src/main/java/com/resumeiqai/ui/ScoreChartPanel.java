package com.resumeiqai.ui;

import com.resumeiqai.database.DashboardDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ScoreChartPanel extends JPanel {

    public ScoreChartPanel() {

        // ================= PANEL SETTINGS =================

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ================= DATABASE =================

        DashboardDAO dao = new DashboardDAO();

        // ================= DATASET =================

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        int lowScoreCount =
                dao.getLowScoreCount();

        int mediumScoreCount =
                dao.getMediumScoreCount();

        int highScoreCount =
                dao.getHighScoreCount();

        dataset.addValue(
                lowScoreCount,
                "Resumes",
                "Low (0-49)"
        );

        dataset.addValue(
                mediumScoreCount,
                "Resumes",
                "Medium (50-79)"
        );

        dataset.addValue(
                highScoreCount,
                "Resumes",
                "High (80-100)"
        );

        // ================= CREATE CHART =================
        // IMPORTANT:
        // Chart title is set to null because
        // DashboardFrame already displays
        // "Resume Score Distribution".

        JFreeChart chart =
                ChartFactory.createBarChart(

                        null,

                        "Score Category",

                        "Number of Resumes",

                        dataset
                );

        // ================= CHART PLOT =================

        CategoryPlot plot =
                chart.getCategoryPlot();

        plot.setBackgroundPaint(Color.WHITE);

        plot.setRangeGridlinePaint(
                new Color(220, 220, 220)
        );

        // ================= BAR RENDERER =================

        BarRenderer renderer =
                (BarRenderer) plot.getRenderer();

        // Show values above bars

        renderer.setDefaultItemLabelsVisible(true);

        renderer.setDefaultItemLabelGenerator(
                new org.jfree.chart.labels.StandardCategoryItemLabelGenerator()
        );

        // ================= BAR COLORS =================

        // Low = Red

        renderer.setSeriesPaint(
                0,
                new Color(255, 99, 99)
        );

        // Medium = Orange

        renderer.setSeriesPaint(
                1,
                new Color(255, 183, 77)
        );

        // High = Green

        renderer.setSeriesPaint(
                2,
                new Color(76, 175, 80)
        );

        // ================= BAR SPACING =================

        renderer.setItemMargin(0.20);

        // ================= CHART PANEL =================

        ChartPanel chartPanel =
                new ChartPanel(chart);

        chartPanel.setMouseWheelEnabled(true);

        chartPanel.setPreferredSize(
                new Dimension(
                        750,
                        300
                )
        );

        // ================= ADD CHART =================

        add(
                chartPanel,
                BorderLayout.CENTER
        );
    }
}