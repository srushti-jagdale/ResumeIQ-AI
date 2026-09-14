package com.resumeiqai.ui;

import com.resumeiqai.database.ResumeDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField searchField;

    private final ResumeDAO resumeDAO;

    public HistoryFrame() {

        resumeDAO = new ResumeDAO();

        setTitle("ResumeIQ AI - Resume History");
        setSize(900, 550);
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
                        15,
                        15,
                        15,
                        15
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "Resume Analysis History"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // =====================================================
        // SEARCH PANEL
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        JLabel searchLabel =
                new JLabel(
                        "Search:"
                );

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        searchField =
                new JTextField(
                        25
                );

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JButton searchButton =
                new JButton(
                        "Search"
                );

        JButton clearButton =
                new JButton(
                        "Clear"
                );

        JButton refreshButton =
                new JButton(
                        "Refresh"
                );

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(refreshButton);

        // =====================================================
        // TOP PANEL
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        topPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        topPanel.add(
                searchPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE MODEL
        // =====================================================

        model =
                new DefaultTableModel() {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        model.addColumn("ID");
        model.addColumn("File Name");
        model.addColumn("Score");
        model.addColumn("Recommended Role");
        model.addColumn("Date");

        // =====================================================
        // TABLE
        // =====================================================

        table =
                new JTable(model);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        table.setRowHeight(28);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setAutoCreateRowSorter(
                true
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        table.getTableHeader()
                .setReorderingAllowed(false);

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(230);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(80);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(280);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(150);

        // =====================================================
        // TABLE SCROLL PANE
        // =====================================================

        JScrollPane tableScrollPane =
                new JScrollPane(
                        table
                );

        tableScrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Saved Resume Analyses"
                )
        );

        mainPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTONS
        // =====================================================

        JButton viewDetailsButton =
                new JButton(
                        "View Details"
                );

        viewDetailsButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        JButton deleteButton =
                new JButton(
                        "Delete Selected"
                );

        deleteButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        bottomPanel.add(
                viewDetailsButton
        );

        bottomPanel.add(
                deleteButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // LOAD HISTORY
        // =====================================================

        loadHistory("");

        // =====================================================
        // SEARCH BUTTON
        // =====================================================

        searchButton.addActionListener(e -> {

            String keyword =
                    searchField
                            .getText()
                            .trim();

            loadHistory(keyword);
        });

        // =====================================================
        // ENTER KEY SEARCH
        // =====================================================

        searchField.addActionListener(e -> {

            String keyword =
                    searchField
                            .getText()
                            .trim();

            loadHistory(keyword);
        });

        // =====================================================
        // CLEAR BUTTON
        // =====================================================

        clearButton.addActionListener(e -> {

            searchField.setText("");

            loadHistory("");
        });

        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        refreshButton.addActionListener(e -> {

            String keyword =
                    searchField
                            .getText()
                            .trim();

            loadHistory(keyword);
        });

        // =====================================================
        // VIEW DETAILS BUTTON
        // =====================================================

        viewDetailsButton.addActionListener(e -> {

            int selectedViewRow =
                    table.getSelectedRow();

            if (selectedViewRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a record first.",
                        "No Record Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int selectedModelRow =
                        table.convertRowIndexToModel(
                                selectedViewRow
                        );

                int id =
                        Integer.parseInt(
                                model.getValueAt(
                                                selectedModelRow,
                                                0
                                        )
                                        .toString()
                        );

                new ResumeDetailsFrame(
                        id
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to open resume details:\n"
                                + ex.getMessage(),
                        "Details Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =====================================================
        // DELETE BUTTON
        // =====================================================

        deleteButton.addActionListener(e -> {

            int selectedViewRow =
                    table.getSelectedRow();

            if (selectedViewRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a record first.",
                        "No Record Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this record?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (
                    confirm !=
                            JOptionPane.YES_OPTION
            ) {

                return;
            }

            try {

                int selectedModelRow =
                        table.convertRowIndexToModel(
                                selectedViewRow
                        );

                int id =
                        Integer.parseInt(
                                model.getValueAt(
                                                selectedModelRow,
                                                0
                                        )
                                        .toString()
                        );

                boolean deleted =
                        resumeDAO.deleteResumeAnalysis(
                                id
                        );

                if (deleted) {

                    model.removeRow(
                            selectedModelRow
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Record deleted successfully!",
                            "Delete Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to delete record.",
                            "Delete Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error while deleting record:\n"
                                + ex.getMessage(),
                        "Delete Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =====================================================
        // ADD MAIN PANEL
        // =====================================================

        add(
                mainPanel
        );

        // =====================================================
        // SHOW WINDOW
        // =====================================================

        setVisible(true);
    }

    // =========================================================
    // LOAD HISTORY
    // =========================================================

    private void loadHistory(
            String keyword) {

        try {

            model.setRowCount(0);

            List<String[]> results =
                    resumeDAO.searchResumeAnalysis(
                            keyword
                    );

            for (String[] row : results) {

                if (row == null) {
                    continue;
                }

                model.addRow(
                        new Object[]{

                                row.length > 0
                                        ? row[0]
                                        : "",

                                row.length > 1
                                        ? row[1]
                                        : "",

                                row.length > 2
                                        ? row[2]
                                        : "",

                                row.length > 3
                                        ? row[3]
                                        : "",

                                row.length > 4
                                        ? row[4]
                                        : ""
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load resume history:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}