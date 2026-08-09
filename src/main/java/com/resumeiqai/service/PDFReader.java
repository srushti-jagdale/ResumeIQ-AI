package com.resumeiqai.service;

import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader {

    public String extractText(File file) {

        try {

            // Open the PDF
            PDDocument document = Loader.loadPDF(file);

            // Create PDF text extractor
            PDFTextStripper stripper = new PDFTextStripper();

            // Extract all text from the PDF
            String text = stripper.getText(document);
            System.out.println("========== EXTRACTED RESUME TEXT ==========");
            System.out.println(text);
            System.out.println("============================================");

            // Close the PDF
            document.close();

            // Return the extracted text
            return text;

        } catch (Exception e) {

            e.printStackTrace();
            return "Error Reading PDF";

        }
    }

}