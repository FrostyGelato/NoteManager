package model;

import java.util.HashSet;
import java.util.LinkedHashSet;

// Represents a subject that holds a list of related topics
public class Subject {
    private String name;
    private LinkedHashSet<Topic> listOfTopics;

    //REQUIRES: name is non-null string
    //MODIFIES: this
    //EFFECTS: create a topic with given name and no notes
    public void addTopic(String name) {

    }

    //REQUIRES: id that corresponds to an existing topic
    //MODIFIES: this
    //EFFECTS: removes a topic with given id
    public void deleteTopic(int id) {

    }

    public String getName() {
        return "";
    }

    public LinkedHashSet<Topic> getListOfTopics() {
        return new LinkedHashSet<Topic>();
    }
}
