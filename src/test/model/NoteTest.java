package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteTest {
    private Note testNote;
    private Note testNote2;
    private Path testFilePath;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setup() {
        try {
            testFilePath = tempDir.resolve( "Vocabulary.txt" );
        }
        catch( InvalidPathException ipe ) {
            System.err.println("Could not create test file");
        }

        testNote = new Note(testFilePath);
        testNote2 = new Note(testFilePath, Status.NEED_REVISION);
    }

    @Test
    void testConstructor() {
        assertEquals("Vocabulary.txt", testNote.getName());
        assertEquals(testFilePath, testNote.getFileLocation());
        assertEquals(Status.INCOMPLETE, testNote.getStatus());
    }

    @Test
    void testConstructorWithStatus() {
        assertEquals("Vocabulary.txt", testNote2.getName());
        assertEquals(testFilePath, testNote2.getFileLocation());
        assertEquals(Status.NEED_REVISION, testNote2.getStatus());
    }

    @Test
    void testSetStatusIntChangeToRevision() {
        testNote.setStatus(2);
        assertEquals(Status.NEED_REVISION, testNote.getStatus());
    }

    @Test
    void testSetStatusIntChangeToComplete() {
        testNote.setStatus(3);
        assertEquals(Status.COMPLETE, testNote.getStatus());
    }

    @Test
    void testSetStatusIntNoChange() {
        testNote.setStatus(1);
        assertEquals(Status.INCOMPLETE, testNote.getStatus());
    }

    @Test
    void testSetStatusChangeToRevision() {
        testNote.setStatus(Status.NEED_REVISION);
        assertEquals(Status.NEED_REVISION, testNote.getStatus());
    }

    @Test
    void testToJson() {
        JSONObject testNoteInJson = testNote.toJson();

        assertEquals("Vocabulary.txt", testNoteInJson.getString("name"));
        assertEquals(testFilePath, testNoteInJson.get("fileLocation"));
        assertEquals(Status.INCOMPLETE, testNoteInJson.get("status"));
    }
}