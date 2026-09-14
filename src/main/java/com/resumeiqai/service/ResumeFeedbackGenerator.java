package com.resumeiqai.service;

public class ResumeFeedbackGenerator {

    public String generateFeedback(String resumeText) {

        if (resumeText == null) {
            resumeText = "";
        }

        String text = resumeText.toLowerCase();

        StringBuilder feedback = new StringBuilder();

        // =====================================================
        // CAREER PATH DETECTION
        // =====================================================

        boolean frontendPath =
                text.contains("html")
                        && text.contains("css")
                        && (text.contains("javascript")
                        || text.contains("react"));

        boolean strongBackendPath =
                text.contains("java")
                        && text.contains("spring boot")
                        && text.contains("mysql")
                        && text.contains("rest api");

        boolean fullStackPath =
                strongBackendPath
                        && text.contains("html")
                        && text.contains("css")
                        && text.contains("javascript")
                        && text.contains("react");

        // =====================================================
        // PYTHON / AI PATH
        // =====================================================

        boolean pythonAIPath =
                text.contains("python")
                        && (
                        text.contains("machine learning")
                                || text.contains("scikit-learn")
                                || text.contains("tensorflow")
                                || text.contains("artificial intelligence")
                                || text.contains("deep learning")
                );

        // =====================================================
        // DATA ANALYTICS PATH
        // =====================================================

        boolean dataPath =
                text.contains("python")
                        && (
                        text.contains("power bi")
                                || text.contains("tableau")
                                || text.contains("data analyst")
                                || text.contains("data analytics")
                                || text.contains("data analysis")
                                || text.contains("data visualization")
                );

        // =====================================================
        // HEADER
        // =====================================================

        feedback.append("\n==============================\n");
        feedback.append("      AI Career Feedback\n");
        feedback.append("==============================\n\n");

        // =====================================================
        // RESUME STRENGTHS
        // =====================================================

        feedback.append("💪 Resume Strengths:\n");

        // =====================================================
        // FULL STACK
        // =====================================================

        if (fullStackPath) {

            feedback.append(
                    "✔ Strong full-stack development foundation.\n"
            );

            feedback.append(
                    "✔ Java and Spring Boot skills detected.\n"
            );

            feedback.append(
                    "✔ HTML, CSS, JavaScript and React skills detected.\n"
            );

            if (text.contains("rest api")) {

                feedback.append(
                        "✔ REST API experience detected.\n"
                );
            }

            if (text.contains("git")) {

                feedback.append(
                        "✔ Git version control knowledge detected.\n"
                );
            }

        }

        // =====================================================
        // FRONTEND
        // =====================================================

        else if (frontendPath) {

            feedback.append(
                    "✔ Strong frontend development foundation.\n"
            );

            feedback.append(
                    "✔ HTML and CSS skills detected.\n"
            );

            if (text.contains("javascript")) {

                feedback.append(
                        "✔ JavaScript skills detected.\n"
                );
            }

            if (text.contains("react")) {

                feedback.append(
                        "✔ React development skills detected.\n"
                );
            }

            if (text.contains("git")) {

                feedback.append(
                        "✔ Git version control knowledge detected.\n"
                );
            }

            if (text.contains("bootstrap")) {

                feedback.append(
                        "✔ Bootstrap framework knowledge detected.\n"
                );
            }

        }

        // =====================================================
        // JAVA BACKEND
        // =====================================================

        else if (strongBackendPath) {

            feedback.append(
                    "✔ Strong Java backend foundation.\n"
            );

            feedback.append(
                    "✔ Spring Boot knowledge detected.\n"
            );

            feedback.append(
                    "✔ MySQL and database integration skills detected.\n"
            );

            feedback.append(
                    "✔ REST API experience detected.\n"
            );

            if (text.contains("git")) {

                feedback.append(
                        "✔ Git version control knowledge detected.\n"
                );
            }

        }

        // =====================================================
        // PYTHON / AI
        // IMPORTANT: THIS MUST COME BEFORE dataPath
        // =====================================================

        else if (pythonAIPath) {

            feedback.append(
                    "✔ Strong Python and AI/ML development foundation.\n"
            );

            if (text.contains("machine learning")) {

                feedback.append(
                        "✔ Machine Learning skills detected.\n"
                );
            }

            if (text.contains("pandas")) {

                feedback.append(
                        "✔ Pandas and data processing skills detected.\n"
                );
            }

            if (text.contains("numpy")) {

                feedback.append(
                        "✔ NumPy numerical computing skills detected.\n"
                );
            }

            if (text.contains("scikit-learn")
                    || text.contains("sklearn")) {

                feedback.append(
                        "✔ Scikit-learn machine learning skills detected.\n"
                );
            }

            if (text.contains("tensorflow")) {

                feedback.append(
                        "✔ TensorFlow/deep learning skills detected.\n"
                );
            }

            if (text.contains("sql")
                    || text.contains("mysql")) {

                feedback.append(
                        "✔ SQL and database skills detected.\n"
                );
            }

            if (text.contains("git")) {

                feedback.append(
                        "✔ Git version control knowledge detected.\n"
                );
            }

        }

        // =====================================================
        // DATA ANALYTICS
        // =====================================================

        else if (dataPath) {

            feedback.append(
                    "✔ Strong Python and data analysis foundation.\n"
            );

            if (text.contains("pandas")) {

                feedback.append(
                        "✔ Pandas/data manipulation skills detected.\n"
                );
            }

            if (text.contains("sql")
                    || text.contains("mysql")) {

                feedback.append(
                        "✔ Database and SQL skills detected.\n"
                );
            }

            if (text.contains("power bi")) {

                feedback.append(
                        "✔ Power BI/data visualization experience detected.\n"
                );
            }

            if (text.contains("tableau")) {

                feedback.append(
                        "✔ Tableau data visualization skills detected.\n"
                );
            }

            if (text.contains("statistics")) {

                feedback.append(
                        "✔ Statistics knowledge detected.\n"
                );
            }

        }

        // =====================================================
        // GENERAL
        // =====================================================

        else {

            if (text.contains("java")) {

                feedback.append(
                        "✔ Java knowledge detected.\n"
                );
            }

            if (text.contains("python")) {

                feedback.append(
                        "✔ Python knowledge detected.\n"
                );
            }

            if (text.contains("sql")
                    || text.contains("mysql")) {

                feedback.append(
                        "✔ Database skills detected.\n"
                );
            }

            if (text.contains("git")) {

                feedback.append(
                        "✔ Version control knowledge detected.\n"
                );
            }
        }

        feedback.append("\n");

        // =====================================================
        // RESUME WEAKNESSES
        // =====================================================

        feedback.append("⚠ Resume Weaknesses:\n");

        // =====================================================
        // FULL STACK WEAKNESSES
        // =====================================================

        if (fullStackPath) {

            if (!text.contains("docker")) {

                feedback.append(
                        "✘ Docker not found.\n"
                );
            }

            if (!text.contains("rest api")) {

                feedback.append(
                        "✘ REST API not found.\n"
                );
            }
        }

        // =====================================================
        // FRONTEND WEAKNESSES
        // =====================================================

        else if (frontendPath) {

            if (!text.contains("ui design")) {

                feedback.append(
                        "✘ UI Design not found.\n"
                );
            }

            if (!text.contains("responsive design")) {

                feedback.append(
                        "✘ Responsive Design not found.\n"
                );
            }

            if (!text.contains("git")) {

                feedback.append(
                        "✘ Git not found.\n"
                );
            }
        }

        // =====================================================
        // JAVA BACKEND WEAKNESSES
        // =====================================================

        else if (strongBackendPath) {

            if (!text.contains("git")) {

                feedback.append(
                        "✘ Git not found.\n"
                );
            }

            if (!text.contains("docker")) {

                feedback.append(
                        "✘ Docker not found.\n"
                );
            }

            if (!text.contains("maven")) {

                feedback.append(
                        "✘ Maven not found.\n"
                );
            }
        }

        // =====================================================
        // PYTHON / AI WEAKNESSES
        // =====================================================

        else if (pythonAIPath) {

            if (!text.contains("machine learning")) {

                feedback.append(
                        "✘ Machine Learning not found.\n"
                );
            }

            if (!text.contains("scikit-learn")
                    && !text.contains("sklearn")) {

                feedback.append(
                        "✘ Scikit-learn not found.\n"
                );
            }

            if (!text.contains("tensorflow")) {

                feedback.append(
                        "✘ TensorFlow not found.\n"
                );
            }

            if (!text.contains("git")) {

                feedback.append(
                        "✘ Git not found.\n"
                );
            }
        }

        // =====================================================
        // DATA ANALYTICS WEAKNESSES
        // =====================================================

        else if (dataPath) {

            if (!text.contains("pandas")) {

                feedback.append(
                        "✘ Pandas not found.\n"
                );
            }

            if (!text.contains("power bi")) {

                feedback.append(
                        "✘ Power BI not found.\n"
                );
            }

            if (!text.contains("statistics")) {

                feedback.append(
                        "✘ Statistics knowledge not found.\n"
                );
            }
        }

        // =====================================================
        // GENERAL WEAKNESSES
        // =====================================================

        else {

            if (!text.contains("git")) {

                feedback.append(
                        "✘ Git not found.\n"
                );
            }

            if (!text.contains("docker")) {

                feedback.append(
                        "✘ Docker not found.\n"
                );
            }
        }

        feedback.append("\n");

        // =====================================================
        // CAREER READINESS
        // =====================================================

        feedback.append("🚀 Career Readiness:\n");

        // =====================================================
        // FULL STACK
        // =====================================================

        if (fullStackPath) {

            feedback.append(
                    "Advanced Full Stack Development candidate.\n"
            );

            feedback.append(
                    "Suitable for internships and junior full-stack roles.\n"
            );
        }

        // =====================================================
        // FRONTEND
        // =====================================================

        else if (frontendPath) {

            feedback.append(
                    "Intermediate Frontend Developer candidate.\n"
            );

            feedback.append(
                    "Suitable for frontend internships and junior frontend roles.\n"
            );
        }

        // =====================================================
        // JAVA BACKEND
        // =====================================================

        else if (strongBackendPath) {

            feedback.append(
                    "Intermediate Java Backend Developer.\n"
            );

            feedback.append(
                    "Suitable for internships and junior backend roles.\n"
            );
        }

        // =====================================================
        // PYTHON / AI
        // =====================================================

        else if (pythonAIPath) {

            feedback.append(
                    "Intermediate Python / AI Developer candidate.\n"
            );

            feedback.append(
                    "Suitable for Python, Machine Learning and AI internships and junior roles.\n"
            );
        }

        // =====================================================
        // DATA ANALYTICS
        // =====================================================

        else if (dataPath) {

            feedback.append(
                    "Intermediate Data Analytics candidate.\n"
            );

            feedback.append(
                    "Suitable for data analyst internships and entry-level roles.\n"
            );
        }

        // =====================================================
        // GENERAL
        // =====================================================

        else {

            feedback.append(
                    "Beginner Level.\n"
            );

            feedback.append(
                    "Needs more practical projects and industry-relevant skills.\n"
            );
        }

        feedback.append("\n");

        // =====================================================
        // PERSONALIZED LEARNING ROADMAP
        // =====================================================

        feedback.append(
                "📚 Personalized Learning Roadmap:\n"
        );

        // =====================================================
        // FULL STACK ROADMAP
        // =====================================================

        if (fullStackPath) {

            feedback.append(
                    "1. Strengthen Java and Spring Boot\n"
            );

            feedback.append(
                    "2. Build REST APIs\n"
            );

            feedback.append(
                    "3. Improve JavaScript and React\n"
            );

            feedback.append(
                    "4. Learn Git and Docker\n"
            );

            feedback.append(
                    "5. Build and deploy a full-stack project\n"
            );

            feedback.append(
                    "Estimated Time: 8–10 Weeks\n"
            );
        }

        // =====================================================
        // FRONTEND ROADMAP
        // =====================================================

        else if (frontendPath) {

            feedback.append(
                    "1. Strengthen HTML and CSS\n"
            );

            feedback.append(
                    "2. Improve JavaScript fundamentals\n"
            );

            feedback.append(
                    "3. Build advanced React projects\n"
            );

            feedback.append(
                    "4. Improve UI/UX and responsive design\n"
            );

            feedback.append(
                    "5. Build and deploy a professional frontend portfolio\n"
            );

            feedback.append(
                    "Estimated Time: 6–8 Weeks\n"
            );
        }

        // =====================================================
        // JAVA BACKEND ROADMAP
        // =====================================================

        else if (strongBackendPath) {

            feedback.append(
                    "1. Strengthen Java fundamentals\n"
            );

            feedback.append(
                    "2. Build REST APIs using Spring Boot\n"
            );

            feedback.append(
                    "3. Practice MySQL and database integration\n"
            );

            feedback.append(
                    "4. Learn Git and Docker\n"
            );

            feedback.append(
                    "5. Build and deploy a Java backend project\n"
            );

            feedback.append(
                    "Estimated Time: 6–8 Weeks\n"
            );
        }

        // =====================================================
        // PYTHON / AI ROADMAP
        // =====================================================

        else if (pythonAIPath) {

            feedback.append(
                    "1. Strengthen Python programming\n"
            );

            feedback.append(
                    "2. Practice NumPy and Pandas\n"
            );

            feedback.append(
                    "3. Build Machine Learning models using Scikit-learn\n"
            );

            feedback.append(
                    "4. Learn TensorFlow and deep learning\n"
            );

            feedback.append(
                    "5. Build and deploy a Python AI/ML project\n"
            );

            feedback.append(
                    "Estimated Time: 8–10 Weeks\n"
            );
        }

        // =====================================================
        // DATA ANALYTICS ROADMAP
        // =====================================================

        else if (dataPath) {

            feedback.append(
                    "1. Strengthen Python and Pandas\n"
            );

            feedback.append(
                    "2. Practice advanced SQL and database querying\n"
            );

            feedback.append(
                    "3. Improve Power BI and data visualization skills\n"
            );

            feedback.append(
                    "4. Build 2–3 real-world data analytics projects\n"
            );

            feedback.append(
                    "5. Create a portfolio showcasing your analysis projects\n"
            );

            feedback.append(
                    "Estimated Time: 6–8 Weeks\n"
            );
        }

        // =====================================================
        // GENERAL ROADMAP
        // =====================================================

        else {

            feedback.append(
                    "1. Strengthen programming fundamentals\n"
            );

            feedback.append(
                    "2. Learn Git and version control\n"
            );

            feedback.append(
                    "3. Build practical projects\n"
            );

            feedback.append(
                    "4. Develop job-relevant technical skills\n"
            );

            feedback.append(
                    "5. Create a professional project portfolio\n"
            );

            feedback.append(
                    "Estimated Time: 8–10 Weeks\n"
            );
        }

        return feedback.toString();
    }
}