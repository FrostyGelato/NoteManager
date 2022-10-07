package model;

import java.io.File;
import java.util.LinkedHashSet;

// Represents a topic that can hold a list of notes related to that topic
public class Topic {
    private String name;
    private LinkedHashSet<Note> listOfNotes;
    private int id;

    //REQUIRES: a file with a valid path
    //MODIFIES: this
    //EFFECTS: create a note with name of file, type of file, location of file, and incomplete status
    public void addNote(File file) {

    }

    //REQUIRES: id corresponding to an existing note
    //MODIFIES: this
    //EFFECTS: remove the note with given id
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
