package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubjectTest {
    private Subject testSubject;

    @BeforeEach
    void setup() {
        testSubject = new Subject("Math");
    }

    @Test
    void testConstructor() {
        assertEquals("Math", testSubject.getName());
        assertTrue(testSubject.getListOfTopics().isEmpty());
    }
}
