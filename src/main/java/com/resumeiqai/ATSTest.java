package com.resumeiqai;

import com.resumeiqai.service.ATSKeywordAnalyzer;

public class ATSTest {

    public static void main(String[] args) {

        ATSKeywordAnalyzer analyzer =
                new ATSKeywordAnalyzer();

        String resumeText =
                """
                I am a Java developer with experience in
                Java, Spring Boot, MySQL, SQL, REST API and Git.

                I have worked on backend development projects.
                """;

        ATSKeywordAnalyzer.ATSResult result =
                analyzer.analyze(
                        resumeText,
                        "Backend Development"
                );

        System.out.println(
                "===== ATS KEYWORD TEST ====="
        );

        System.out.println(
                "ATS Keyword Score : "
                        + result.getScore()
                        + "/100"
        );

        System.out.println();

        System.out.println(
                "Keywords Found:"
        );

        for (
                String keyword :
                result.getFoundKeywords()
        ) {

            System.out.println(
                    "✓ " + keyword
            );
        }

        System.out.println();

        System.out.println(
                "Keywords Missing:"
        );

        for (
                String keyword :
                result.getMissingKeywords()
        ) {

            System.out.println(
                    "✗ " + keyword
            );
        }
    }
}