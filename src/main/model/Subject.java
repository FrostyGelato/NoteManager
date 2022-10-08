package model;

import java.util.LinkedHashSet;

// Represents a subject that holds a list of related topics
public class Subject {
    private String name;
    private LinkedHashSet<Topic> listOfTopics;

    //REQUIRES: name is non-empty string
    //EFFECTS: constructs a subject with given name and empty list of topics
    public Subject(String name) {
        this.name = name;
        listOfTopics = new LinkedHashSet<>();
    }

    //MODIFIES: this
    //EFFECTS: adds the given topic to list of topics unless it's already there, in which case do nothing
    public void addTopic(Topic topic) {
        listOfTopics.add(topic);
    }

    //MODIFIES: this
    //EFFECTS: if the given topic is in list of topics, removes it; otherwise, do nothing
    public void removeTopic(Topic topic) {
        listOfTopics.remove(topic);
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Topic> getListOfTopics() {
        return listOfTopics;
    }
}
