package model;

import java.util.LinkedHashSet;

// Represents a topic that can hold a list of notes related to that topic
public class Topic {
    private String name;
    private LinkedHashSet<Note> listOfNotes;
}
