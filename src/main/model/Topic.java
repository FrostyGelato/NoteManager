package model;

import java.io.File;
import java.util.LinkedHashSet;

// Represents a topic that can hold a list of notes related to that topic
public class Topic {
    private String name;
    private LinkedHashSet<Note> listOfNotes;
    private int id;

    //REQUIRES: name is non-null string
    //EFFECTS: constructs a topic with given name, random id, and empty list of notes
    public Topic(String name) {

    }

    //REQUIRES: a file with a valid path
    //MODIFIES: this
    //EFFECTS: create a note with given file and add it to list of notes
    public void addNote(File file) {

    }

    //REQUIRES: id corresponding to an existing note
    //MODIFIES: this
    //EFFECTS: remove the note with given id from list of notes
    public void deleteNote(int id) {

    }

    public String getName() {
        return "";
    }

    public LinkedHashSet<Note> getListOfNotes() {
        return new LinkedHashSet<Note>();
    }

    public int getId() {
        return 0;
    }
}
