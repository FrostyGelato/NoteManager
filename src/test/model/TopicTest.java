package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TopicTest {
    private Topic testTopic;
    private Path testFile1Path;
    private Path testFile2Path;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setup() {
        testTopic = new Topic("Probability");

        try {
            testFile1Path = tempDir.resolve( "probability.txt" );
            testFile2Path = tempDir.resolve( "statistics.txt" );
        }
        catch( InvalidPathException ipe ) {
            System.err.println("could not create test files");
        }
    }

    @Test
    void testConstructor() {
        assertEquals("Probability", testTopic.getName());
        assertTrue(testTopic.getListOfNotes().isEmpty());
    }

    @Test
    void testAddNote() {
        Note testNote = new Note(testFile1Path);
        testTopic.addNote(testNote);
        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        assertTrue(noteList.contains(testNote));
        assertEquals(1, noteList.size());
    }

    @Test
    void testAddNoteMultiple() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);
        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        assertTrue(noteList.contains(testNote1));
        assertEquals(1, noteList.size());

        Note testNote2 = new Note(testFile2Path);
        testTopic.addNote(testNote2);
        assertTrue(noteList.contains(testNote2));
        assertEquals(2, noteList.size());
    }

    @Test
    void testAddNoteDuplicate() {
        Note testNote = new Note(testFile1Path);
        testTopic.addNote(testNote);
        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        assertTrue(noteList.contains(testNote));
        assertEquals(1, noteList.size());

        testTopic.addNote(testNote);
        assertTrue(noteList.contains(testNote));
        assertEquals(1, noteList.size());
    }

    @Test
    void testAddByPathNote() {
        testTopic.addNote(testFile1Path);
        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        Iterator<Note> iter = noteList.iterator();
        assertEquals(testFile1Path, iter.next().getFileLocation());
        assertEquals(1, noteList.size());
    }

    @Test
    void testAddNoteByPathMultiple() {
        testTopic.addNote(testFile1Path);
        testTopic.addNote(testFile2Path);

        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        Iterator<Note> iter = noteList.iterator();
        assertEquals(testFile1Path, iter.next().getFileLocation());
        assertEquals(testFile2Path, iter.next().getFileLocation());
        assertEquals(2, noteList.size());
    }

    @Test
    void testRemoveNote() {
        Note testNote = new Note(testFile1Path);
        testTopic.addNote(testNote);
        assertTrue(testTopic.getListOfNotes().contains(testNote));

        testTopic.removeNote(testFile1Path.toString());
        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        assertFalse(noteList.contains(testNote));
        assertEquals(0, noteList.size());
    }

    @Test
    void testRemoveNoteMultiple() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);
        Note testNote2 = new Note(testFile2Path);
        testTopic.addNote(testNote2);

        assertTrue(testTopic.getListOfNotes().contains(testNote1));
        assertTrue(testTopic.getListOfNotes().contains(testNote2));

        testTopic.removeNote(testFile1Path.toString());
        LinkedHashSet<Note> noteList = testTopic.getListOfNotes();
        assertFalse(noteList.contains(testNote1));
        assertTrue(noteList.contains(testNote2));
        assertEquals(1, noteList.size());

        testTopic.removeNote(testFile2Path.toString());
        assertFalse(testTopic.getListOfNotes().contains(testNote2));
        assertEquals(0, noteList.size());
    }

    @Test
    void testIsDuplicatePath() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);

        assertTrue(testTopic.isDuplicatePath(testFile1Path));
    }

    @Test
    void testIsDuplicatePathNoDuplicate() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);

        assertFalse(testTopic.isDuplicatePath(testFile2Path));
    }

    @Test
    void testToJsonEmpty() {
        JSONObject testTopicInJson = testTopic.toJson();
        assertEquals("Probability", testTopicInJson.getString("name"));
        assertEquals(0, testTopicInJson.getJSONArray("listOfNotes").length());
    }

    @Test
    void testToJson() {
        testTopic.addNote(testFile1Path);
        JSONObject testTopicInJson = testTopic.toJson();
        assertEquals("Probability", testTopicInJson.getString("name"));
        assertEquals(1, testTopicInJson.getJSONArray("listOfNotes").length());
    }

    @Test
    void testToJsonMultiple() {
        testTopic.addNote(testFile1Path);
        testTopic.addNote(testFile2Path);
        JSONObject testTopicInJson = testTopic.toJson();
        assertEquals("Probability", testTopicInJson.getString("name"));
        assertEquals(2, testTopicInJson.getJSONArray("listOfNotes").length());
    }
}
