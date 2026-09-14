package com.resumeiqai.ui;

import com.resumeiqai.database.JobMatchDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class JobMatchScoreChartPanel extends JPanel {

    public JobMatchScoreChartPanel() {

        // =====================================================
        // PANEL SETTINGS
        // =====================================================

        setLayout(
                new BorderLayout()
        );

        setBackground(
                Color.WHITE
        );

        // =====================================================
        // DATABASE
        // =====================================================

        JobMatchDAO dao =
                new JobMatchDAO();

        int lowCount =
                dao.getLowJobMatchCount();

        int mediumCount =
                dao.getMediumJobMatchCount();

        int highCount =
                dao.getHighJobMatchCount();

        // =====================================================
        // DATASET
        // =====================================================

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        dataset.addValue(
                lowCount,
                "Job Matches",
                "Low (0-49)"
        );

        dataset.addValue(
                mediumCount,
                "Job Matches",
                "Medium (50-79)"
        );

        dataset.addValue(
                highCount,
                "Job Matches",
                "High (80-100)"
        );

        // =====================================================
        // CREATE CHART
        // =====================================================

        JFreeChart chart =
                ChartFactory.createBarChart(

                        null,

                        "Match Score Range",

                        "Number of Job Matches",

                        dataset
                );

        // =====================================================
        // CHART SETTINGS
        // =====================================================

        CategoryPlot plot =
                chart.getCategoryPlot();

        plot.setBackgroundPaint(
                Color.WHITE
        );

        plot.setRangeGridlinePaint(
                new Color(
                        220,
                        220,
                        220
                )
        );

        // =====================================================
        // BAR RENDERER
        // =====================================================

        BarRenderer renderer =
                (BarRenderer) plot.getRenderer();

        renderer.setDefaultItemLabelsVisible(
                true
        );

        renderer.setDefaultItemLabelGenerator(
                new org.jfree.chart.labels
                        .StandardCategoryItemLabelGenerator()
        );

        renderer.setSeriesPaint(
                0,
                new Color(
                        100,
                        149,
                        237
                )
        );

        renderer.setItemMargin(
                0.20
        );

        // =====================================================
        // CHART PANEL
        // =====================================================

        ChartPanel chartPanel =
                new ChartPanel(
                        chart
                );

        chartPanel.setMouseWheelEnabled(
                true
        );

        chartPanel.setPreferredSize(
                new Dimension(
                        750,
                        300
                )
        );

        // =====================================================
        // ADD CHART
        // =====================================================

        add(
                chartPanel,
                BorderLayout.CENTER
        );
    }
}