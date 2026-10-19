
package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StudentEligibilityTest {

    private StudentEligibility student;

    @BeforeEach
    void setUp() {
        student = new StudentEligibility();
    }

    // 1. BVA: Test age boundaries
    @ParameterizedTest(name = "Age {0} should be eligible: {1}")
    @CsvSource({
        "16, false",
        "17, true",
        "18, true",
        "59, true",
        "60, true",
        "61, false"
    })
    @DisplayName("Test age eligibility using BVA")
    void testAgeBoundaryValues(int age, boolean expected) {
        assertEquals(expected, student.isEligible(age));
    }

    // 2. Negative test: age cannot be negative
    @Test
    @DisplayName("Negative age should throw exception")
    void testNegativeAge() {
        assertThrows(
            IllegalArgumentException.class,
            () -> student.isEligible(-1)
        );
    }

    // 3. Test weighted final score
    @Test
    @DisplayName("Calculate weighted final score correctly")
    void testCalculateFinalScore() {
        double result = student.calculateFinalScore(80, 90);

        assertEquals(86.0, result, 0.001);
    }

    // 4. Test score boundaries: lower limit
    @Test
    @DisplayName("Zero scores should produce zero")
    void testZeroScores() {
        assertEquals(
            0.0,
            student.calculateFinalScore(0, 0),
            0.001
        );
    }

    // 5. Negative test: invalid midterm score
    @Test
    @DisplayName("Invalid midterm score should throw exception")
    void testInvalidMidtermScore() {
        assertThrows(
            IllegalArgumentException.class,
            () -> student.calculateFinalScore(-1, 80)
        );
    }

    // 6. Negative test: invalid final exam score
    @Test
    @DisplayName("Invalid final exam score should throw exception")
    void testInvalidFinalExamScore() {
        assertThrows(
            IllegalArgumentException.class,
            () -> student.calculateFinalScore(80, 101)
        );
    }

    // 7. Test grade classification
    @Test
    @DisplayName("Score 90 should receive grade A")
    void testGradeA() {
        assertEquals("A", student.classifyGrade(90));
    }

    // 8. Test grade boundary
    @Test
    @DisplayName("Score 85 should receive grade A")
    void testGradeBoundaryA() {
        assertEquals("A", student.classifyGrade(85));
    }

    // 9. Test grade classification B
    @Test
    @DisplayName("Score 70 should receive grade B")
    void testGradeB() {
        assertEquals("B", student.classifyGrade(70));
    }

    // 10. Test grade classification F
    @Test
    @DisplayName("Score 30 should receive grade F")
    void testGradeF() {
        assertEquals("F", student.classifyGrade(30));
    }

    // 11. Negative test: grade score exceeds maximum
    @Test
    @DisplayName("Score above 100 should throw exception")
    void testInvalidGradeScore() {
        assertThrows(
            IllegalArgumentException.class,
            () -> student.classifyGrade(101)
        );
    }
}
