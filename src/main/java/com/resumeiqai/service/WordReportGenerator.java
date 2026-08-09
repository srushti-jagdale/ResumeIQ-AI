package com.resumeiqai.service;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class WordReportGenerator {

    public void generateReport(String reportText, String filePath) {

        try {

            XWPFDocument document = new XWPFDocument();

            // ================= PAGE SETTINGS =================

            document.getDocument().getBody().addNewSectPr();

            // ================= TITLE =================

            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun titleRun = title.createRun();

            titleRun.setText("ResumeIQ AI Analysis Report");
            titleRun.setBold(true);
            titleRun.setFontSize(22);
            titleRun.setFontFamily("Arial");

            title.setSpacingAfter(300);

            // ================= PROCESS REPORT =================

            String[] lines = reportText.split("\\r?\\n");

            for (String originalLine : lines) {

                String line = originalLine.trim();

                // ================= EMPTY LINE =================

                if (line.isEmpty()) {

                    XWPFParagraph emptyParagraph =
                            document.createParagraph();

                    emptyParagraph.setSpacingAfter(100);

                    continue;
                }

                // ================= SEPARATOR LINES =================

                if (line.matches("=+")) {
                    continue;
                }

                // ================= SECTION HEADINGS =================

                if (isHeading(line)) {

                    XWPFParagraph heading =
                            document.createParagraph();

                    heading.setSpacingBefore(250);
                    heading.setSpacingAfter(120);

                    XWPFRun headingRun =
                            heading.createRun();

                    headingRun.setText(cleanText(line));
                    headingRun.setBold(true);
                    headingRun.setFontSize(15);
                    headingRun.setFontFamily("Arial");

                    continue;
                }

                // ================= SCORE =================

                if (line.startsWith("Resume Score")) {

                    XWPFParagraph paragraph =
                            document.createParagraph();

                    paragraph.setSpacingAfter(120);

                    XWPFRun label =
                            paragraph.createRun();

                    label.setText("Resume Score: ");
                    label.setBold(true);
                    label.setFontSize(12);
                    label.setFontFamily("Arial");

                    XWPFRun value =
                            paragraph.createRun();

                    value.setText(
                            cleanText(
                                    line.substring(
                                            line.indexOf(":") + 1
                                    ).trim()
                            )
                    );

                    value.setFontSize(12);
                    value.setBold(true);
                    value.setFontFamily("Arial");

                    continue;
                }

                // ================= NUMBERED ROADMAP =================

                if (line.matches("^\\d+\\.\\s+.*")) {

                    XWPFParagraph paragraph =
                            document.createParagraph();

                    paragraph.setStyle("List Number");
                    paragraph.setSpacingAfter(80);

                    XWPFRun run =
                            paragraph.createRun();

                    run.setText(cleanText(line));
                    run.setFontSize(11);
                    run.setFontFamily("Arial");

                    continue;
                }

                // ================= CHECKMARK / CROSS ITEMS =================

                if (line.startsWith("✓")
                        || line.startsWith("✔")
                        || line.startsWith("X")
                        || line.startsWith("✗")
                        || line.startsWith("❌")) {

                    XWPFParagraph paragraph =
                            document.createParagraph();

                    paragraph.setIndentationLeft(300);
                    paragraph.setSpacingAfter(80);

                    XWPFRun run =
                            paragraph.createRun();

                    run.setText("• " + cleanText(line));
                    run.setFontSize(11);
                    run.setFontFamily("Arial");

                    continue;
                }

                // ================= BULLET ITEMS =================

                if (line.startsWith("•")
                        || line.startsWith("-")) {

                    XWPFParagraph paragraph =
                            document.createParagraph();

                    paragraph.setStyle("List Bullet");
                    paragraph.setSpacingAfter(80);

                    XWPFRun run =
                            paragraph.createRun();

                    run.setText(
                            cleanText(
                                    line.substring(1).trim()
                            )
                    );

                    run.setFontSize(11);
                    run.setFontFamily("Arial");

                    continue;
                }

                // ================= NORMAL TEXT =================

                XWPFParagraph paragraph =
                        document.createParagraph();

                paragraph.setSpacingAfter(100);

                XWPFRun run =
                        paragraph.createRun();

                run.setText(cleanText(line));

                run.setFontSize(11);
                run.setFontFamily("Arial");
            }

            // ================= FOOTER =================

            XWPFParagraph footerSpace =
                    document.createParagraph();

            footerSpace.setSpacingBefore(300);

            XWPFRun footerRun =
                    footerSpace.createRun();

            footerRun.setText("Generated by ResumeIQ AI");

            footerRun.setItalic(true);
            footerRun.setFontSize(9);
            footerRun.setFontFamily("Arial");

            // ================= SAVE FILE =================

            FileOutputStream outputStream =
                    new FileOutputStream(filePath);

            document.write(outputStream);

            outputStream.close();
            document.close();

            System.out.println(
                    "Word report generated successfully!"
            );

        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }

    // =========================================================
    // DETERMINE SECTION HEADINGS
    // =========================================================

    private boolean isHeading(String line) {

        String text = cleanText(line).toLowerCase();

        return text.equals("skills found:")
                || text.equals("missing skills:")
                || text.equals("recommended job roles:")
                || text.equals("suggestions:")
                || text.equals("ai career feedback")
                || text.equals("resume strengths:")
                || text.equals("resume weaknesses:")
                || text.equals("career readiness:")
                || text.equals("personalized learning roadmap:")
                || text.equals("resume analysis");
    }

    // =========================================================
    // CLEAN EMOJIS / SPECIAL SYMBOLS
    // =========================================================

    private String cleanText(String text) {

        return text
                .replace("📄", "")
                .replace("📊", "")
                .replace("🏆", "")
                .replace("💼", "")
                .replace("🚀", "")
                .replace("💪", "")
                .replace("📚", "")
                .replace("📈", "")
                .replace("🎯", "")
                .replace("⚠", "")
                .replace("⚠️", "")
                .replace("✓", "")
                .replace("✔", "")
                .replace("✗", "")
                .replace("❌", "")
                .replace("🔍", "")
                .trim();
    }
}