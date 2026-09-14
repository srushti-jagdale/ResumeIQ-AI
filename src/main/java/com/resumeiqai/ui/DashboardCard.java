package com.resumeiqai.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardCard extends JPanel {

    private JLabel titleLabel;
    private JLabel valueLabel;

    public DashboardCard(String title, String value, Color color) {

        setPreferredSize(new Dimension(260, 130));
        setMinimumSize(new Dimension(220, 120));
        setBackground(color);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 210, 210),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                12, 12, 12, 12
                        )
                )
        );

        setLayout(new BorderLayout(5, 10));

        // ==============================
        // TITLE
        // ==============================

        titleLabel = new JLabel(
                title,
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                new Color(45, 45, 45)
        );

        // ==============================
        // VALUE
        // ==============================

        valueLabel = new JLabel(
                value,
                SwingConstants.CENTER
        );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        valueLabel.setForeground(
                new Color(30, 30, 30)
        );

        // ==============================
        // ADD COMPONENTS
        // ==============================

        add(
                titleLabel,
                BorderLayout.NORTH
        );

        add(
                valueLabel,
                BorderLayout.CENTER
        );
    }

    // ==============================
    // UPDATE CARD VALUE
    // ==============================

    public void setValue(String value) {

        if (value == null) {
            value = "-";
        }

        // Long values such as job roles
        if (value.length() > 20) {

            valueLabel.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            18
                    )
            );

            valueLabel.setText(
                    "<html><div style='text-align:center;'>"
                            + value.replace(
                            "\n",
                            "<br>"
                    )
                            + "</div></html>"
            );

        } else {

            valueLabel.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            28
                    )
            );

            valueLabel.setText(value);
        }
    }
}