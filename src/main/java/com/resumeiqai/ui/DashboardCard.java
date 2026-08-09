package com.resumeiqai.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardCard extends JPanel {

    private JLabel titleLabel;
    private JLabel valueLabel;

    public DashboardCard(String title, String value, Color color) {

        setPreferredSize(new Dimension(220,120));
        setBackground(color);

        setBorder(BorderFactory.createLineBorder(Color.GRAY,2));

        setLayout(new BorderLayout());

        titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 26));

        add(titleLabel, BorderLayout.NORTH);
        add(valueLabel, BorderLayout.CENTER);
    }

    public void setValue(String value) {

        // If text is long (like job roles), wrap it nicely
        if (value.length() > 20) {

            valueLabel.setFont(new Font("Arial", Font.BOLD, 18));

            valueLabel.setText(
                    "<html><center>"
                            + value.replace("\n", "<br>")
                            + "</center></html>"
            );

        } else {

            valueLabel.setFont(new Font("Arial", Font.BOLD, 26));
            valueLabel.setText(value);

        }

    }

}