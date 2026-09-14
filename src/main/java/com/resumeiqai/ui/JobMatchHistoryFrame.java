package com.resumeiqai.ui;

import com.resumeiqai.database.JobMatchDAO;
import com.resumeiqai.database.JobMatchDAO.JobMatchRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobMatchHistoryFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private final JobMatchDAO jobMatchDAO;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public JobMatchHistoryFrame() {

        jobMatchDAO = new JobMatchDAO();

        setTitle("ResumeIQ AI - Job Match History");
        setSize(950, 600);
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

        mainPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "Job Match History"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        JButton refreshButton =
                new JButton(
                        "Refresh"
                );

        refreshButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setOpaque(false);

        topPanel.add(
                titleLabel,
                BorderLayout.CENTER
        );

        topPanel.add(
                refreshButton,
                BorderLayout.EAST
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
        model.addColumn("Resume File");
        model.addColumn("Job Title");
        model.addColumn("Match Score");
        model.addColumn("Date");

        // =====================================================
        // TABLE
        // =====================================================

        table =
                new JTable(
                        model
                );

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
                .setReorderingAllowed(
                        false
                );

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(200);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(250);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(100);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(180);

        // =====================================================
        // TABLE SCROLL PANE
        // =====================================================

        JScrollPane tableScrollPane =
                new JScrollPane(
                        table
                );

        tableScrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Saved Job Match Analyses"
                )
        );

        mainPanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTONS
        // =====================================================

        JButton viewButton =
                new JButton(
                        "View Details"
                );

        JButton deleteButton =
                new JButton(
                        "Delete Selected"
                );

        viewButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
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
                                15,
                                5
                        )
                );

        bottomPanel.setOpaque(false);

        bottomPanel.add(
                viewButton
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

        loadJobMatchHistory();

        // =====================================================
        // REFRESH BUTTON ACTION
        // =====================================================

        refreshButton.addActionListener(e -> {

            loadJobMatchHistory();

            JOptionPane.showMessageDialog(
                    this,
                    "Job match history refreshed successfully!",
                    "Refresh Complete",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // =====================================================
        // VIEW DETAILS BUTTON ACTION
        // =====================================================

        viewButton.addActionListener(e -> {

            int selectedViewRow =
                    table.getSelectedRow();

            if (selectedViewRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a job match record first.",
                        "No Record Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int selectedModelRow =
                    table.convertRowIndexToModel(
                            selectedViewRow
                    );

            int id =
                    Integer.parseInt(
                            model.getValueAt(
                                    selectedModelRow,
                                    0
                            ).toString()
                    );

            showJobMatchDetails(
                    id
            );
        });

        // =====================================================
        // DELETE BUTTON ACTION
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
                            "Are you sure you want to delete this job match record?",
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

            int selectedModelRow =
                    table.convertRowIndexToModel(
                            selectedViewRow
                    );

            int id =
                    Integer.parseInt(
                            model.getValueAt(
                                    selectedModelRow,
                                    0
                            ).toString()
                    );

            boolean deleted =
                    jobMatchDAO.deleteJobMatch(
                            id
                    );

            if (deleted) {

                model.removeRow(
                        selectedModelRow
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Job match record deleted successfully!",
                        "Delete Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to delete job match record.",
                        "Delete Failed",
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

    // =====================================================
    // LOAD JOB MATCH HISTORY
    // =====================================================

    private void loadJobMatchHistory() {

        try {

            model.setRowCount(
                    0
            );

            List<JobMatchRecord> matches =
                    jobMatchDAO.getAllJobMatches();

            for (
                    JobMatchRecord record
                    : matches
            ) {

                model.addRow(
                        new Object[]{

                                record.getId(),

                                record.getResumeFile(),

                                record.getJobTitle(),

                                record.getMatchScore() + "%",

                                record.getMatchDate()
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load job match history:\n"
                            + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SHOW JOB MATCH DETAILS
    // =====================================================

    private void showJobMatchDetails(
            int id) {

        try {

            List<JobMatchRecord> matches =
                    jobMatchDAO.getAllJobMatches();

            JobMatchRecord selectedRecord =
                    null;

            for (
                    JobMatchRecord record
                    : matches
            ) {

                if (
                        record.getId()
                                == id
                ) {

                    selectedRecord =
                            record;

                    break;
                }
            }

            if (selectedRecord == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Job match record not found.",
                        "Record Not Found",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // =================================================
            // BUILD DETAILS
            // =================================================

            StringBuilder details =
                    new StringBuilder();

            details.append(
                    "JOB MATCH DETAILS\n"
            );

            details.append(
                    "================================================\n\n"
            );

            details.append(
                    "Resume File: "
            );

            details.append(
                    selectedRecord.getResumeFile()
            );

            details.append(
                    "\n\n"
            );

            details.append(
                    "Job Title: "
            );

            details.append(
                    selectedRecord.getJobTitle()
            );

            details.append(
                    "\n\n"
            );

            details.append(
                    "Match Score: "
            );

            details.append(
                    selectedRecord.getMatchScore()
            );

            details.append(
                    "%\n\n"
            );

            details.append(
                    "MATCHED KEYWORDS\n"
            );

            details.append(
                    "------------------------------------------------\n"
            );

            details.append(
                    selectedRecord.getMatchedKeywords()
            );

            details.append(
                    "\n\n"
            );

            details.append(
                    "MISSING KEYWORDS\n"
            );

            details.append(
                    "------------------------------------------------\n"
            );

            details.append(
                    selectedRecord.getMissingKeywords()
            );

            details.append(
                    "\n\n"
            );

            details.append(
                    "RECOMMENDATION\n"
            );

            details.append(
                    "------------------------------------------------\n"
            );

            details.append(
                    selectedRecord.getRecommendation()
            );

            details.append(
                    "\n\n"
            );

            details.append(
                    "Date: "
            );

            details.append(
                    selectedRecord.getMatchDate()
            );

            // =================================================
            // DETAILS TEXT AREA
            // =================================================

            JTextArea detailsArea =
                    new JTextArea(
                            details.toString()
                    );

            detailsArea.setEditable(
                    false
            );

            detailsArea.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            14
                    )
            );

            detailsArea.setLineWrap(
                    true
            );

            detailsArea.setWrapStyleWord(
                    true
            );

            detailsArea.setMargin(
                    new Insets(
                            15,
                            15,
                            15,
                            15
                    )
            );

            JScrollPane scrollPane =
                    new JScrollPane(
                            detailsArea
                    );

            scrollPane.setPreferredSize(
                    new Dimension(
                            650,
                            500
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    scrollPane,
                    "Job Match Details",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load job match details:\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}