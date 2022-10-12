package model;

import java.util.LinkedHashSet;

public class ListOfSubjects {

    private LinkedHashSet<Subject> listOfSubjects;

    //EFFECTS: constructs an empty list of subjects
    public ListOfSubjects() {
        listOfSubjects = new LinkedHashSet<>();
    }

    //REQUIRES: name is non-empty string
    //MODIFIES: this
    //EFFECTS: creates a subject with given name and adds it to list
    // unless there is already a subject with same name, in which case, do nothing
    public void addSubject(String name) {
        listOfSubjects.add(new Subject(name));
    }

    //REQUIRES:
    public void removeSubject(String name) {
        listOfSubjects.removeIf(subject -> subject.getName().equals(name));
    }

    public LinkedHashSet<Subject> getListOfSubjects() {
        return listOfSubjects;
    }
}
