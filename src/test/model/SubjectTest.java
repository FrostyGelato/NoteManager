package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testAddTopic() {
        Topic testTopic = new Topic("Logarithm");
        testSubject.addTopic(testTopic);
        assertTrue(testSubject.getListOfTopics().contains(testTopic));
    }

    @Test
    void testAddTopicMultiple() {
        Topic testLogTopic = new Topic("Logarithm");
        testSubject.addTopic(testLogTopic);
        assertTrue(testSubject.getListOfTopics().contains(testLogTopic));

        Topic testExpTopic = new Topic("Exponent");
        testSubject.addTopic(testExpTopic);
        assertTrue(testSubject.getListOfTopics().contains(testExpTopic));
    }

    @Test
    void testAddTopicDuplicate() {
        Topic testTopic = new Topic("Logarithm");

        testSubject.addTopic(testTopic);
        assertTrue(testSubject.getListOfTopics().contains(testTopic));
        assertEquals(1, testSubject.getListOfTopics().size());

        testSubject.addTopic(testTopic);
        assertTrue(testSubject.getListOfTopics().contains(testTopic));
        assertEquals(1, testSubject.getListOfTopics().size());
    }

    @Test
    void testRemoveTopic() {
        Topic testTopic = new Topic("Logarithm");
        testSubject.addTopic(testTopic);
        assertTrue(testSubject.getListOfTopics().contains(testTopic));

        testSubject.removeTopic(testTopic);
        assertFalse(testSubject.getListOfTopics().contains(testTopic));
    }

    @Test
    void testRemoveTopicNotPresent() {
        Topic testTopic1 = new Topic("Logarithm");
        testSubject.addTopic(testTopic1);

        Topic testTopic2 = new Topic("Trigonometry");
        testSubject.removeTopic(testTopic2);
        assertTrue(testSubject.getListOfTopics().contains(testTopic1));
        assertEquals(1, testSubject.getListOfTopics().size());
    }

    @Test
    void testRemoveTopicByName() {
        Topic testTopic = new Topic("Logarithm");
        testSubject.addTopic(testTopic);
        assertTrue(testSubject.getListOfTopics().contains(testTopic));

        testSubject.removeTopic("Logarithm");
        assertFalse(testSubject.getListOfTopics().contains(testTopic));
    }
}
