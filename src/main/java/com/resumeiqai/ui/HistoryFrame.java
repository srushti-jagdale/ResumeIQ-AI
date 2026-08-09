package com.resumeiqai.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.resumeiqai.database.DBConnection;
import com.resumeiqai.database.ResumeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistoryFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public HistoryFrame() {

        setTitle("Resume History");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("File Name");
        model.addColumn("Score");
        model.addColumn("Recommended Role");
        model.addColumn("Date");

        table = new JTable(model);

        // ================= SEARCH PANEL =================

        JLabel searchLabel = new JLabel("Search :");

        JTextField searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");

        JPanel topPanel = new JPanel();

        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        JScrollPane pane = new JScrollPane(table);

        add(pane, BorderLayout.CENTER);

        // ================= BUTTON PANEL =================

        JButton deleteButton = new JButton("Delete Selected");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load all history
        loadHistory("");

        // ================= DELETE BUTTON =================

        deleteButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a record first."
                );

                return;

            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this record?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                int id = Integer.parseInt(
                        model.getValueAt(selectedRow, 0).toString()
                );

                ResumeDAO dao = new ResumeDAO();

                if (dao.deleteResumeAnalysis(id)) {

                    model.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(
                            this,
                            "Record deleted successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to delete record."
                    );

                }

            }

        });

        // ================= SEARCH BUTTON =================

        searchButton.addActionListener(e -> {

            String keyword = searchField.getText().trim();

            loadHistory(keyword);

        });

        setVisible(true);

    }

    // ================= LOAD HISTORY =================

    private void loadHistory(String keyword) {

        try {

            model.setRowCount(0);

            Connection connection = DBConnection.getConnection();

            String sql;

            PreparedStatement statement;

            if (keyword == null || keyword.isEmpty()) {

                sql = "SELECT * FROM resume_analysis";

                statement = connection.prepareStatement(sql);

            } else {

                sql = "SELECT * FROM resume_analysis WHERE file_name LIKE ?";

                statement = connection.prepareStatement(sql);

                statement.setString(1, "%" + keyword + "%");

            }

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{

                        rs.getInt("id"),
                        rs.getString("file_name"),
                        rs.getInt("resume_score"),
                        rs.getString("recommended_role"),
                        rs.getTimestamp("analysis_date")

                });

            }

            rs.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}