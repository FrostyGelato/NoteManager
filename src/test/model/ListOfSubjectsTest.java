package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfSubjectsTest {

    private ListOfSubjects testList;

    @BeforeEach
    void setup() {
        testList = new ListOfSubjects();
    }

    @Test
    void testConstructor() {
        assertTrue(testList.getListOfSubjects().isEmpty());
    }

    @Test
    void testAddSubject() {
        assertTrue(testList.getListOfSubjects().isEmpty());
        testList.addSubject("Math");

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(1, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());
    }

    @Test
    void testAddSubjectMultiple() {
        assertTrue(testList.getListOfSubjects().isEmpty());
        testList.addSubject("Math");
        testList.addSubject("English");

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(2, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());
        assertEquals("English", iter.next().getName());
    }

    @Test
    void testRemoveSubject() {
        testList.addSubject("Math");

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(1, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());

        testList.removeSubject("Math");
        assertTrue(testList.getListOfSubjects().isEmpty());
    }

    @Test
    void testRemoveSubjectMultiple() {
        testList.addSubject("Math");
        testList.addSubject("English");

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(2, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());
        assertEquals("English", iter.next().getName());

        testList.removeSubject("Math");
        testList.removeSubject("English");
        assertTrue(testList.getListOfSubjects().isEmpty());
    }

    @Test
    void testContainsDuplicateSubjectEmptyList() {
        assertFalse(testList.containsDuplicateSubject("Math"));
    }

    @Test
    void testContainsDuplicateSubject() {
        testList.addSubject("Math");
        testList.addSubject("Biology");
        assertTrue(testList.containsDuplicateSubject("Math"));
    }

    @Test
    void testContainsDuplicateSubjectNoDuplicate() {
        testList.addSubject("Physics");
        testList.addSubject("Biology");
        assertFalse(testList.containsDuplicateSubject("Math"));
    }

    @Test
    void testToJsonEmpty() {
        JSONArray testJsonArray = testList.toJson();
        assertTrue(testJsonArray.isEmpty());
    }

    @Test
    void testToJson() {
        testList.addSubject("Math");
        JSONArray testJsonArray = testList.toJson();
        assertEquals(1, testJsonArray.length());
    }

    @Test
    void testToJsonMultiple() {
        testList.addSubject("Math");
        testList.addSubject("Spanish");
        JSONArray testJsonArray = testList.toJson();
        assertEquals(2, testJsonArray.length());
    }
}
