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
public class JsonWriterTest {

    private Path testFilePath;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setup() {
        try {
            testFilePath = tempDir.resolve( "Enzyme_Types.txt" );
        }
        catch( InvalidPathException ipe ) {
            System.err.println("Could not create test file");
        }
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfSubjects list = new ListOfSubjects();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfSubjects() {
        try {
            ListOfSubjects list = new ListOfSubjects();

            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            list = reader.read();
            assertEquals(0, list.getLength());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfSubjects() {
        try {
            ListOfSubjects listOfSubjects = new ListOfSubjects();
            Note types = new Note(testFilePath);
            Topic enzymes = new Topic("Enzymes");
            enzymes.addNote(types);
            Subject biology = new Subject("Biology");
            biology.addTopic(enzymes);

            listOfSubjects.addSubject("Physics");
            listOfSubjects.addSubject(biology);


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralList.json");
            writer.open();
            writer.write(listOfSubjects);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralList.json");
            listOfSubjects = reader.read();
            assertEquals(2, listOfSubjects.getLength());
            LinkedHashSet<Subject> readSubjects = listOfSubjects.getListOfSubjects();
            Iterator<Subject> subjectIter = readSubjects.iterator();
            Subject readPhysics = subjectIter.next();
            assertEquals("Physics", readPhysics.getName());
            assertTrue(readPhysics.getListOfTopics().isEmpty());
            Subject readBiology = subjectIter.next();
            assertEquals("Biology", readBiology.getName());
            LinkedHashSet<Topic> readTopics = readBiology.getListOfTopics();
            Iterator<Topic> topicIter = readTopics.iterator();
            Topic readEnzymes = topicIter.next();
            assertEquals("Enzymes", readEnzymes.getName());
            LinkedHashSet<Note> readNotes = readEnzymes.getListOfNotes();
            Iterator<Note> noteIter = readNotes.iterator();
            Note readNote = noteIter.next();
            assertEquals("Enzyme_Types.txt", readNote.getName());
            assertEquals(testFilePath, readNote.getFileLocation());
            assertEquals(Status.INCOMPLETE, readNote.getStatus());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}
