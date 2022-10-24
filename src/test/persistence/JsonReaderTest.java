package persistence;

import model.ListOfSubjects;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    }
}
