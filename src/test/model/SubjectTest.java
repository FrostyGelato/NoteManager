package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {
    private Subject testSubject;
    private Iterator<Event> eventIter;

    @BeforeEach
    void setup() {
        testSubject = new Subject("Math");

        EventLog.getInstance().clear();
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
        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
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
        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
        assertEquals("Topic Exponent added to subject Math.", eventIter.next().getDescription());
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
        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
    }

    @Test
    void testAddTopicByName() {
        testSubject.addTopic("Logarithm");

        LinkedHashSet<Topic> topicList = testSubject.getListOfTopics();
        Iterator<Topic> iter = topicList.iterator();
        assertEquals("Logarithm", iter.next().getName());
        assertEquals(1, topicList.size());
        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
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
        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
        assertEquals("Topic Exponent added to subject Math.", eventIter.next().getDescription());
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
        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
        assertEquals("Topic Logarithm has been removed from subject Math.", eventIter.next().getDescription());
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

        initialiseIterator();
        assertEquals("Topic Logarithm added to subject Math.", eventIter.next().getDescription());
        assertEquals("Topic Exponent added to subject Math.", eventIter.next().getDescription());
        assertEquals("Topic Exponent has been removed from subject Math.", eventIter.next().getDescription());
        assertEquals("Topic Logarithm has been removed from subject Math.", eventIter.next().getDescription());
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

    @Test
    void testGetTopicByNameFirst() {
        Topic logarithmTopic = new Topic("Logarithm");
        testSubject.addTopic(logarithmTopic);
        testSubject.addTopic("Exponent");
        assertEquals(logarithmTopic, testSubject.getTopicByName("Logarithm"));
    }

    @Test
    void testGetTopicByNameSecond() {
        testSubject.addTopic("Logarithm");
        Topic exponentTopic = new Topic("Exponent");
        testSubject.addTopic(exponentTopic);
        assertEquals(exponentTopic, testSubject.getTopicByName("Exponent"));
    }

    @Test
    void testToJsonEmpty() {
        JSONObject testSubjectInJson = testSubject.toJson();
        assertEquals("Math", testSubjectInJson.getString("name"));
        assertEquals(0, testSubjectInJson.getJSONArray("listOfTopics").length());
    }

    @Test
    void testToJson() {
        testSubject.addTopic("Logarithm");
        JSONObject testSubjectInJson = testSubject.toJson();
        assertEquals("Math", testSubjectInJson.getString("name"));
        JSONArray testTopicListInJson = testSubjectInJson.getJSONArray("listOfTopics");
        assertEquals(1, testTopicListInJson.length());
        assertEquals("Logarithm", testTopicListInJson.getJSONObject(0).getString("name"));
    }

    @Test
    void testToJsonMultiple() {
        testSubject.addTopic("Logarithm");
        testSubject.addTopic("Exponent");
        JSONObject testSubjectInJson = testSubject.toJson();
        assertEquals("Math", testSubjectInJson.getString("name"));
        JSONArray testTopicListInJson = testSubjectInJson.getJSONArray("listOfTopics");
        assertEquals(2, testTopicListInJson.length());
        assertEquals("Logarithm", testTopicListInJson.getJSONObject(0).getString("name"));
        assertEquals("Exponent", testTopicListInJson.getJSONObject(1).getString("name"));
    }

    private void initialiseIterator() {
        eventIter = EventLog.getInstance().iterator();
        eventIter.next();
    }
}
