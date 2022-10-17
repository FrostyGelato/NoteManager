package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteTest {
    private Note testNote;
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
    }

    @Test
    void testConstructor() {
        assertEquals("Vocabulary.txt", testNote.getName());
        assertEquals(testFilePath, testNote.getFileLocation());
        assertEquals(Status.INCOMPLETE, testNote.getStatus());
    }

    @Test
    void testSetStatusChangeToRevision() {
        testNote.setStatus(2);
        assertEquals(Status.NEED_REVISION, testNote.getStatus());
    }

    @Test
    void testSetStatusChangeToComplete() {
        testNote.setStatus(3);
        assertEquals(Status.COMPLETE, testNote.getStatus());
    }

    @Test
    void testSetStatusNoChange() {
        testNote.setStatus(1);
        assertEquals(Status.INCOMPLETE, testNote.getStatus());
    }
}