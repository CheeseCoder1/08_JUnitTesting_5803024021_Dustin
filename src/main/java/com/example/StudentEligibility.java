
package com.example;

public class StudentEligibility {

    // Method 1: Check age eligibility
    public boolean isEligible(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(
                "Age cannot be negative"
            );
        }

        return age >= 17 && age <= 60;
    }

    // Method 2: Calculate final score
    // Midterm = 40%, Final exam = 60%
    public double calculateFinalScore(
            double midterm, double finalExam) {

        if (midterm < 0 || midterm > 100) {
            throw new IllegalArgumentException(
                "Midterm score must be between 0 and 100"
            );
        }

        if (finalExam < 0 || finalExam > 100) {
            throw new IllegalArgumentException(
                "Final exam score must be between 0 and 100"
            );
        }

        return (midterm * 0.40) + (finalExam * 0.60);
    }

    // Method 3: Classify final grade
    public String classifyGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                "Score must be between 0 and 100"
            );
        }

        if (score >= 85) {
            return "A";
        } else if (score >= 70) {
            return "B";
        } else if (score >= 55) {
            return "C";
        } else if (score >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}
