package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Credit: taken from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a list of subjects from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfSubjects read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseListOfSubjects(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses a list of subjects from JSON array and returns it
    private ListOfSubjects parseListOfSubjects(JSONArray listOfSubjectsInJson) {
        ListOfSubjects listFromJson = new ListOfSubjects();

        for (Object json : listOfSubjectsInJson) {
            Subject subject = parseSubject((JSONObject) json);
            listFromJson.addSubject(subject);
        }

        return listFromJson;
    }

    // EFFECTS: parses a subject from JSON object and returns it
    private Subject parseSubject(JSONObject subjectInJson) {
        String name = subjectInJson.getString("name");
        LinkedHashSet<Topic> listFromJson = parseListOfTopics(subjectInJson.getJSONArray("listOfTopics"));
        Subject subject = new Subject(name);
        for (Topic t: listFromJson) {
            subject.addTopic(t);
        }
        return subject;
    }

    // EFFECTS: parses a list of topics from JSON array and returns it
    private LinkedHashSet<Topic> parseListOfTopics(JSONArray listOfTopicsInJson) {
        LinkedHashSet<Topic> list = new LinkedHashSet<>();

        for (Object j: listOfTopicsInJson) {
            Topic topic = parseTopic((JSONObject) j);
            list.add(topic);
        }

        return list;
    }

    // EFFECTS: parses a topic from JSON object and returns it
    private Topic parseTopic(JSONObject topicInJson) {
        String name = topicInJson.getString("name");
        LinkedHashSet<Note> listFromJson = parseListOfNotes(topicInJson.getJSONArray("listOfNotes"));
        Topic topic = new Topic(name);
        for (Note n: listFromJson) {
            topic.addNote(n);
        }
        return topic;
    }

    // EFFECTS: parses a list of notes from JSON array and returns it
    private LinkedHashSet<Note> parseListOfNotes(JSONArray listOfNotesInJson) {
        LinkedHashSet<Note> list = new LinkedHashSet<>();

        for (Object j: listOfNotesInJson) {
            Note note = parseNote((JSONObject) j);
            list.add(note);
        }
        return list;
    }

    // EFFECTS: parses a note from JSON object and returns it
    private Note parseNote(JSONObject noteInJson) {
        String name = noteInJson.getString("name");
        Path fileLocation = Paths.get(noteInJson.getString("fileLocation"));
        Status status = Status.valueOf(noteInJson.getString("status"));
        return new Note(fileLocation, status);
    }
}
