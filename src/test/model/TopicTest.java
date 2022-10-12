package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

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
        assertTrue(testTopic.getListOfNotes().contains(testNote));
    }

    @Test
    void testAddNoteMultiple() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);
        assertTrue(testTopic.getListOfNotes().contains(testNote1));

        Note testNote2 = new Note(testFile2Path);
        testTopic.addNote(testNote2);
        assertTrue(testTopic.getListOfNotes().contains(testNote2));
    }

    @Test
    void testAddNoteDuplicate() {
        Note testNote = new Note(testFile1Path);
        testTopic.addNote(testNote);
        assertTrue(testTopic.getListOfNotes().contains(testNote));
        assertEquals(1, testTopic.getListOfNotes().size());

        testTopic.addNote(testNote);
        assertTrue(testTopic.getListOfNotes().contains(testNote));
        assertEquals(1, testTopic.getListOfNotes().size());
    }

    @Test
    void testRemoveNote() {
        Note testNote = new Note(testFile1Path);
        testTopic.addNote(testNote);
        assertTrue(testTopic.getListOfNotes().contains(testNote));

        testTopic.removeNote(testNote);
        assertFalse(testTopic.getListOfNotes().contains(testNote));
    }

    @Test
    void testRemoveNoteNotPresent() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);
        assertTrue(testTopic.getListOfNotes().contains(testNote1));

        Note testNote2 = new Note(testFile1Path);
        testTopic.removeNote(testNote2);
        assertTrue(testTopic.getListOfNotes().contains(testNote1));
    }

    @Test
    void testRemoveNoteString() {
        Note testNote1 = new Note(testFile1Path);
        testTopic.addNote(testNote1);
        assertTrue(testTopic.getListOfNotes().contains(testNote1));

        testTopic.removeNote(testFile1Path.toString());
        assertFalse(testTopic.getListOfNotes().contains(testNote1));
    }
}
