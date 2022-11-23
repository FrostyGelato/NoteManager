package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

        EventLog.getInstance().logEvent(
                new Event("Topic " + topic.getName() + " added to subject " + name));
    }

    //REQUIRES: topicName is non-empty string, there is no topic with same name in list
    //MODIFIES: this
    //EFFECTS: constructs a topic with topicName and adds it to list
    public void addTopic(String topicName) {
        addTopic(new Topic(topicName));
    }

    //REQUIRES: topicName is non-empty string, list contains a topic with same name as given name
    //MODIFIES: this
    //EFFECTS: remove topic with given name from list of topics
    public void removeTopic(String topicName) {
        listOfTopics.removeIf(topic -> topic.getName().equals(topicName));

        EventLog.getInstance().logEvent(
                new Event("Topic " + topicName + " has been removed from subject " + name));
    }

    //REQUIRES: name is non-empty
    //EFFECTS: return true is list already contains a topic with same name; otherwise return false
    public boolean containsDuplicateTopic(String name) {
        return isDuplicateName(name, listOfTopics);
    }

    //REQUIRES: list contains a topic with given name
    //EFFECTS: returns the topic with the given name
    public Topic getTopicByName(String name) {

        Topic namedTopic = new Topic("temp");

        for (Topic t: listOfTopics) {
            if (name.equals(t.getName())) {
                namedTopic = t;
            }
        }

        return namedTopic;
    }

    //EFFECTS: constructs a JsonObject from a subject and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("listOfTopics", topicsToJson());
        return json;
    }

    //EFFECTS: constructs a JsonArray from the list of topics and returns it
    private JSONArray topicsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Topic t: listOfTopics) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<Topic> getListOfTopics() {
        return listOfTopics;
    }
}
