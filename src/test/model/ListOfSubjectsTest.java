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
        Subject mathSubject = new Subject("Math");
        testList.addSubject(mathSubject);

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(1, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());
    }

    @Test
    void testAddSubjectMultiple() {
        Subject mathSubject = new Subject("Math");
        Subject filmSubject = new Subject("Film");
        testList.addSubject(mathSubject);
        testList.addSubject(filmSubject);

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(2, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());
        assertEquals("Film", iter.next().getName());
    }

    @Test
    void testAddSubjectString() {
        assertTrue(testList.getListOfSubjects().isEmpty());
        testList.addSubject("Math");

        LinkedHashSet<Subject> actualList = testList.getListOfSubjects();
        assertEquals(1, actualList.size());
        Iterator<Subject> iter = actualList.iterator();
        assertEquals("Math", iter.next().getName());
    }

    @Test
    void testAddSubjectStringMultiple() {
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
    void testGetSubjectByNameFirst() {
        Subject subject1 = new Subject("Math");
        testList.addSubject(subject1);
        testList.addSubject("Biology");
        assertEquals(subject1, testList.getSubjectByName("Math"));
    }

    @Test
    void testGetSubjectByNameSecond() {
        Subject subject1 = new Subject("Math");
        testList.addSubject(subject1);
        Subject subject2 = new Subject("Biology");
        testList.addSubject(subject2);
        assertEquals(subject2, testList.getSubjectByName("Biology"));
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
        assertEquals("Math", testJsonArray.getJSONObject(0).getString("name"));
    }

    @Test
    void testToJsonMultiple() {
        testList.addSubject("Math");
        testList.addSubject("Spanish");
        JSONArray testJsonArray = testList.toJson();
        assertEquals(2, testJsonArray.length());
        assertEquals("Math", testJsonArray.getJSONObject(0).getString("name"));
        assertEquals("Spanish", testJsonArray.getJSONObject(1).getString("name"));
    }
}
