package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.LinkedHashSet;

// Represents a topic that can hold a list of notes related to that topic
public class Topic extends HasList implements HasName {
    private String name;
    private LinkedHashSet<Note> listOfNotes;

    //REQUIRES: name is non-null string
    //EFFECTS: constructs a topic with given name and empty list of notes
    public Topic(String name) {
        this.name = name;
        listOfNotes = new LinkedHashSet<>();
    }

    //MODIFIES: this
    //EFFECTS: adds given note to list of notes unless it's already there, in which case do nothing
    public void addNote(Note note) {
        listOfNotes.add(note);
    }

    //REQUIRES: filePath is the path of a file in the filesystem, there is no note with same path in list
    //MODIFIES: this
    //EFFECTS: constructs a new note with filePath and adds it to list of notes
    public void addNote(Path filePath) {
        addNote(new Note(filePath));
    }

    //REQUIRES: filePath is a valid path to a file, assumes note with filePath exists in list
    //MODIFIES: this
    //EFFECTS: removes note with the given path in list of notes
    public void removeNote(String filePath) {
        listOfNotes.removeIf(note -> note.getFileLocation().toString().equals(filePath));
    }

    //EFFECTS: returns true if list contains note with same path as filePath; otherwise, return false
    public boolean isDuplicatePath(Path filePath) {
        boolean isDuplicate = false;

        for (Note n: listOfNotes) {
            if (filePath.equals(n.getFileLocation())) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Note> getListOfNotes() {
        return listOfNotes;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("listOfNotes", notesToJson());
        return json;
    }

    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Note n: listOfNotes) {
            jsonArray.put(n.toJson());
        }

        return jsonArray;
    }
}
