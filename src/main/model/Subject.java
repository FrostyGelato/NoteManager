package model;

import java.util.LinkedHashSet;

// Represents a subject that holds a list of related topics
public class Subject {
    private String name;
    private LinkedHashSet<Topic> listOfTopics;

    //REQUIRES: name is non-empty string
    //EFFECTS: constructs a subject with given name and empty list of topics
    public Subject(String name) {

    }

    //MODIFIES: this
    //EFFECTS: adds the given topic to list of topics unless it's already there, in which case do nothing
    public void addTopic(Topic topic) {

    }

    //MODIFIES: this
    //EFFECTS: if the given topic is in list of topics, removes it; otherwise, do nothing
    public void removeTopic(Topic topic) {

    }

    public String getName() {
        return "";
    }

    public LinkedHashSet<Topic> getListOfTopics() {
        return new LinkedHashSet<Topic>();
    }
}
