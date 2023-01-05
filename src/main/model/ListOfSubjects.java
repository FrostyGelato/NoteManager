package model;

import org.json.JSONArray;
import persistence.Writable;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

// Represents a list of subjects
public class ListOfSubjects extends HasList implements Writable {

    private LinkedHashMap<String, Subject> mapOfSubjects;

    //EFFECTS: constructs an empty list of subjects
    public ListOfSubjects() {
        mapOfSubjects = new LinkedHashMap<>();
    }

    //REQUIRES: list does not contain subject with same name as given subject
    //MODIFIES: this
    //EFFECTS: adds a subject to list
    public void addSubject(Subject subject) {
        mapOfSubjects.put(subject.getName(), subject);

        EventLog.getInstance().logEvent(
                new Event("Subject " + subject.getName() + " added to list of subjects."));
    }

    //REQUIRES: name is non-empty string, list does not contain subject with same name as given name
    //MODIFIES: this
    //EFFECTS: creates a subject with given name and adds it to list
    public void addSubject(String name) {
        addSubject(new Subject(name));
    }

    //REQUIRES: list contains one subject with same name as given name
    //MODIFIES: this
    //EFFECTS: removes any subject with same name as given name from list
    public void removeSubject(String name) {
        mapOfSubjects.remove(name);

        EventLog.getInstance().logEvent(
                new Event("Subject " + name + " has been removed from list of subjects."));
    }

    //REQUIRES: subjectName is non-empty
    //EFFECTS: return true if list already contains a subject with same name; otherwise return false
    public boolean containsDuplicateSubject(String name) {
        return mapOfSubjects.containsKey(name);
    }

    //REQUIRES: list contains a subject with given name
    //EFFECTS: returns the subject with the given name
    public Subject getSubjectByName(String name) {

        return mapOfSubjects.get(name);
    }

    //EFFECTS: constructs a JsonArray from a list of subjects and returns it
    @Override
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (Subject s : mapOfSubjects.values()) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }

    public LinkedHashSet<Subject> getListOfSubjects() {
        return new LinkedHashSet<Subject>(mapOfSubjects.values());
    }

    public LinkedHashMap<String, Subject> getMapOfSubjects() {
        return mapOfSubjects;
    }

    public LinkedHashSet<String> getListOfSubjectNames() {
        return new LinkedHashSet<String>(mapOfSubjects.keySet());
    }

    public int getLength() {
        return mapOfSubjects.size();
    }
}
