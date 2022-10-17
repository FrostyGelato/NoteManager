package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;

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

        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        assertTrue(topicList.contains(testTopic));
        assertEquals(1, topicList.size());
    }

    @Test
    void testAddTopicMultiple() {
        Topic testLogTopic = new Topic("Logarithm");
        testSubject.addTopic(testLogTopic);

        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        assertTrue(topicList.contains(testLogTopic));
        assertEquals(1, topicList.size());

        Topic testExpTopic = new Topic("Exponent");
        testSubject.addTopic(testExpTopic);

        topicList = testSubject.getListOfTopics();
        assertTrue(topicList.contains(testExpTopic));
        assertTrue(topicList.contains(testLogTopic));
        assertEquals(2, topicList.size());
    }

    @Test
    void testAddTopicDuplicate() {
        Topic testTopic = new Topic("Logarithm");
        testSubject.addTopic(testTopic);

        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        assertTrue(topicList.contains(testTopic));
        assertEquals(1, topicList.size());

        testSubject.addTopic(testTopic);

        topicList = testSubject.getListOfTopics();
        assertTrue(topicList.contains(testTopic));
        assertEquals(1, topicList.size());
    }

    @Test
    void testAddTopicByName() {
        testSubject.addTopic("Logarithm");

        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        Iterator<Topic> iter = topicList.iterator();
        assertEquals("Logarithm", iter.next().getName());
        assertEquals(1, topicList.size());
    }

    @Test
    void testAddTopicByNameMultiple() {
        testSubject.addTopic("Logarithm");
        testSubject.addTopic("Exponent");

        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        Iterator<Topic> iter = topicList.iterator();
        assertEquals("Logarithm", iter.next().getName());
        assertEquals("Exponent", iter.next().getName());
        assertEquals(2, topicList.size());
    }

    @Test
    void testRemoveTopic() {
        Topic testTopic = new Topic("Logarithm");
        testSubject.addTopic(testTopic);
        assertTrue(testSubject.getListOfTopics().contains(testTopic));

        testSubject.removeTopic("Logarithm");
        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        assertFalse(topicList.contains(testTopic));
        assertEquals(0, topicList.size());
    }

    @Test
    void testRemoveTopicMultiple() {
        Topic testTopic = new Topic("Logarithm");
        testSubject.addTopic(testTopic);
        Topic testTopic2 = new Topic("Exponent");
        testSubject.addTopic(testTopic2);

        testSubject.removeTopic("Exponent");
        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        assertFalse(topicList.contains(testTopic2));
        assertEquals(1, topicList.size());

        testSubject.removeTopic("Logarithm");
        topicList = testSubject.getListOfTopics();
        assertFalse(topicList.contains(testTopic));
        assertEquals(0, topicList.size());
    }

    @Test
    void testContainsDuplicateSubjectEmptyList() {
        assertFalse(testSubject.containsDuplicateTopic("Logarithm"));
    }

    @Test
    void testContainsDuplicateSubject() {
        testSubject.addTopic("Logarithm");
        testSubject.addTopic("Exponent");
        assertTrue(testSubject.containsDuplicateTopic("Logarithm"));
    }

    @Test
    void testContainsDuplicateSubjectNoDuplicate() {
        testSubject.addTopic("Logarithm");
        testSubject.addTopic("Exponent");
        assertFalse(testSubject.containsDuplicateTopic("Probability"));
    }
}
