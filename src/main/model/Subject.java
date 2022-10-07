package model;

import java.util.HashSet;
import java.util.LinkedHashSet;

// Represents a subject that holds a list of related topics
public class Subject {
    private String name;
    private LinkedHashSet<Topic> listOfTopics;
}
