package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

//Based off of JsonSerializationDemo
public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfSubjects ls = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfSubjects() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            ListOfSubjects ls = reader.read();
            assertEquals(0, ls.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListOfSubjects() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralList.json");
        try {
            ListOfSubjects listOfSubjects = reader.read();
            assertEquals(2, listOfSubjects.getLength());
            LinkedHashSet<Subject> readSubjects = listOfSubjects.getListOfSubjects();
            Iterator<Subject> subjectIter = readSubjects.iterator();
            assertEquals("Physics", subjectIter.next().getName());
            Subject readBiology = subjectIter.next();
            assertEquals("Biology", readBiology.getName());
            LinkedHashSet<Topic> readTopics = readBiology.getListOfTopics();
            Iterator<Topic> topicIter = readTopics.iterator();
            Topic readEnzymes = topicIter.next();
            assertEquals("Enzymes", readEnzymes.getName());
            LinkedHashSet<Note> readNotes = readEnzymes.getListOfNotes();
            assertTrue(readNotes.isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
