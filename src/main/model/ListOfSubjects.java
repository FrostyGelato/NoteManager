package model;

import org.json.JSONArray;
import persistence.Writable;

import java.util.LinkedHashSet;

// Represents a list of subjects
public class ListOfSubjects extends HasList implements Writable {

    private LinkedHashSet<Subject> listOfSubjects;

    //EFFECTS: constructs an empty list of subjects
    public ListOfSubjects() {
        listOfSubjects = new LinkedHashSet<>();
    }

    //REQUIRES: name is non-empty string, list does not contain subject with same name as given name
    //MODIFIES: this
    //EFFECTS: creates a subject with given name and adds it to list
    public void addSubject(String name) {
        listOfSubjects.add(new Subject(name));
    }

    //REQUIRES: list contains one subject with same name as given name
    //MODIFIES: this
    //EFFECTS: removes any subject with same name as given name from list
    public void removeSubject(String name) {
        listOfSubjects.removeIf(subject -> subject.getName().equals(name));
    }

    //REQUIRES: subjectName is non-empty
    //EFFECTS: return true if list already contains a subject with same name; otherwise return false
    public boolean containsDuplicateSubject(String name) {
        return isDuplicateName(name, listOfSubjects);
    }

    @Override
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (Subject s : listOfSubjects) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }

    public LinkedHashSet<Subject> getListOfSubjects() {
        return listOfSubjects;
    }
}
