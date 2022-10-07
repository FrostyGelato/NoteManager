package model;

import org.junit.jupiter.api.BeforeEach;

public class SubjectTest {
    private Subject testSubject;

    @BeforeEach

    void setup() {
        testSubject = new Subject("Math");
    }
}
