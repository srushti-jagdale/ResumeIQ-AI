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
import java.util.Map;

public class RoleDistributionChartPanel extends JPanel {

    public RoleDistributionChartPanel() {

        // =====================================================
        // PANEL SETTINGS
        // =====================================================

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // =====================================================
        // DATABASE
        // =====================================================

        DashboardDAO dao =
                new DashboardDAO();

        Map<String, Integer> roleDistribution =
                dao.getRoleDistribution();

        // =====================================================
        // DATASET
        // =====================================================

        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        for (
                Map.Entry<String, Integer> entry :
                roleDistribution.entrySet()
        ) {

            dataset.addValue(
                    entry.getValue(),
                    "Resumes",
                    entry.getKey()
            );
        }

        // =====================================================
        // CREATE CHART
        // =====================================================

        JFreeChart chart =
                ChartFactory.createBarChart(

                        null,

                        "Recommended Role",

                        "Number of Resumes",

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
