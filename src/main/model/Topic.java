package model;

import java.util.LinkedHashSet;

// Represents a topic that can hold a list of notes related to that topic
public class Topic implements HasName {
    private String name;
    private LinkedHashSet<Note> listOfNotes;

    //REQUIRES: name is non-null string
    //EFFECTS: constructs a topic with given name, and empty list of notes
    public Topic(String name) {
        this.name = name;
        listOfNotes = new LinkedHashSet<>();
    }

    //MODIFIES: this
    //EFFECTS: adds given note to list of notes unless it's already there, in which case do nothing
    public void addNote(Note note) {
        listOfNotes.add(note);
    }

    //MODIFIES: this
    //EFFECTS: if the given note is in list of notes, removes it; otherwise, do nothing
    public void removeNote(Note note) {
        listOfNotes.remove(note);
    }

    //REQUIRES: filePath is a valid path to a file
    //MODIFIES: this
    //EFFECTS: if there is a note with the given path in list of notes, removes it; otherwise, do nothing
    public void removeNote(String filePath) {
        listOfNotes.removeIf(note -> note.getFileLocation().toString().equals(filePath));
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Note> getListOfNotes() {
        return listOfNotes;
    }
}
