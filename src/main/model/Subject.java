package model;

import java.util.LinkedHashSet;

// Represents a subject that holds a list of related topics
public class Subject extends HasList implements HasName {
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

    //REQUIRES: topicName is non-empty string, there is no topic with same name in list
    //MODIFIES: this
    //EFFECTS: constructs a topic with topicName and adds it to list
    public void addTopic(String topicName) {
        addTopic(new Topic(topicName));
    }

    //MODIFIES: this
    //EFFECTS: if the given topic is in list of topics, removes it; otherwise, do nothing
    public void removeTopic(Topic topic) {
        listOfTopics.remove(topic);
    }

    //REQUIRES: topicName is non-empty string, list contains a topic with same name as given name
    //MODIFIES: this
    //EFFECTS: remove topic with given name from list of topics
    public void removeTopic(String topicName) {
        listOfTopics.removeIf(topic -> topic.getName().equals(topicName));
    }

    //REQUIRES: name is non-empty
    //EFFECTS: return true is list already contains a topic with same name; otherwise return false
    public boolean containsDuplicateTopic(String name) {
        return isDuplicateName(name, listOfTopics);
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Topic> getListOfTopics() {
        return listOfTopics;
    }
}
