package ui;

import model.HasName;
import model.Note;
import model.Subject;
import model.Topic;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class NoteManager {
    private Scanner input;
    private LinkedHashSet<Subject> listOfSubjects;

    //EFFECTS: runs the note manager application
    public NoteManager() {
        runManager();
    }

    //MODIFIES: this
    //EFFECTS: displays console UI and receives user input
    // from TellerApp
    private void runManager() {

        boolean keepGoing = true;
        String userResponse;

        init();

        while (keepGoing) {
            displaySubjectMenu();
            userResponse = input.next().toLowerCase();

            if (userResponse.equals("q")) {
                keepGoing = false;
            } else {
                processSubjectMenuInput(userResponse);
            }
        }

        System.out.println("Goodbye!");
    }

    //MODIFIES: this
    //EFFECTS: initialize input and list of subjects
    private void init() {
        input = new Scanner(System.in);
        listOfSubjects = new LinkedHashSet<>();
    }

    //EFFECTS: displays a list of subjects and other options
    private void displaySubjectMenu() {
        System.out.println("Subject Menu\n");
        System.out.println("Subject List:");

        if (listOfSubjects.isEmpty()) {
            System.out.println("You have no subjects.");
        } else {
            for (Subject s: listOfSubjects) {
                System.out.println(s.getName());
            }
        }

        System.out.println("\nEnter the name of the subject you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> create new subject");
        System.out.println("r -> remove a subject");
        System.out.println("q -> quit");
    }

    //MODIFIES: this
    //EFFECTS: allows user to create new subject and displays the topic menu with a subject
    private void processSubjectMenuInput(String userResponse) {
        if (userResponse.equals("n")) {
            createNewSubject();
        } else if (userResponse.equals("r")) {
            removeSubject();
        } else {
            for (Subject s: listOfSubjects) {
                if (userResponse.equals(s.getName())) {

                    boolean keepGoing = true;
                    String userResponseInTopicMenu;

                    while (keepGoing) {
                        displayTopicMenu(s);
                        userResponseInTopicMenu = input.next().toLowerCase();

                        if (userResponseInTopicMenu.equals("s")) {
                            keepGoing = false;
                        } else {
                            processTopicMenuInput(s, userResponseInTopicMenu);
                        }
                    }
                    break;
                }
            }
        }
    }

    //MODIFIES: this
    private void removeSubject() {
        System.out.println("Enter name of subject to remove and click Enter:");
        String subjectName = input.next();
        Subject subjectToBeRemoved = null;

        for (Subject s: listOfSubjects) {
            if (subjectName.equals(s.getName())) {
                subjectToBeRemoved = s;
            }
        }
        
        listOfSubjects.remove(subjectToBeRemoved);
    }

    //MODIFIES: this
    //EFFECTS: asks user for subject name and creates subject with given name in list
    private void createNewSubject() {
        System.out.println("Enter name for subject and click Enter:");
        String subjectName = input.next();

        if (isDuplicateSubject(subjectName)) {
            System.out.println("Error: You cannot have subjects with the same name.\n"
                    + "Please try again.");
        } else {
            listOfSubjects.add(new Subject(subjectName));
        }
    }

    //REQUIRES: subjectName is non-empty
    //EFFECTS: return true is list already contains a subject with same name; otherwise return false
    public boolean isDuplicateSubject(String subjectName) {

        return hasDuplicateName(subjectName, listOfSubjects);
    }

    //EFFECTS: displays a list of topics in selected subject and other menu options
    private void displayTopicMenu(Subject selectedSubject) {

        System.out.println("Topic Menu\n");

        System.out.println("Topic List for " + selectedSubject.getName() + ":");

        if (selectedSubject.getListOfTopics().isEmpty()) {
            System.out.println("You have no topics.");
        } else {
            for (Topic t: selectedSubject.getListOfTopics()) {
                System.out.println(t.getName());
            }
        }

        System.out.println("\nEnter the name of the topic you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> create new topic");
        System.out.println("r -> remove a topic");
        System.out.println("s -> return to subject menu");
    }

    private void processTopicMenuInput(Subject selectedSubject, String userResponse) {

        if (userResponse.equals("n")) {
            createNewTopic(selectedSubject);
        } else if (userResponse.equals("r")) {
            removeTopic(selectedSubject);
        } else {
            for (Topic t: selectedSubject.getListOfTopics()) {
                if (userResponse.equals(t.getName())) {

                    boolean keepGoing = true;
                    String userResponseInNoteMenu;

                    while (keepGoing) {
                        displayNoteMenu(t);
                        userResponseInNoteMenu = input.next().toLowerCase();

                        if (userResponseInNoteMenu.equals("s")) {
                            keepGoing = false;
                        } else {
                            processNoteMenuInput(t, userResponseInNoteMenu);
                        }
                    }
                    break;
                }
            }
        }
    }

    private void removeTopic(Subject selectedSubject) {
        System.out.println("Enter name of topic to remove and click Enter:");
        String topicName = input.next();

        selectedSubject.removeTopic(topicName);
    }

    private void processNoteMenuInput(Topic selectedTopic, String userResponse) {
        if (userResponse.equals("n")) {
            System.out.println("Enter the full path of the note:");
            selectedTopic.addNote(new Note(Path.of(input.next())));
        } else {
            for (Note n: selectedTopic.getListOfNotes()) {
                if (userResponse.equals(n.getName())) {
                    try {
                        System.out.println("Opening file...");
                        n.openFile();
                    } catch (IOException e) {
                        System.out.println("Error: Unable to open file");
                    }
                    break;
                }
            }
        }
    }

    //EFFECTS: displays a list of note in selected topic and other menu options
    private void displayNoteMenu(Topic selectedTopic) {

        System.out.println("Note Menu\n");

        System.out.println("Note List for " + selectedTopic.getName() + ":");

        if (selectedTopic.getListOfNotes().isEmpty()) {
            System.out.println("You have no notes.");
        } else {
            for (Note n: selectedTopic.getListOfNotes()) {
                System.out.println(n.getName() + " - " + n.getStatus() + " - " + n.getFileLocation());
            }
        }

        System.out.println("\nEnter the name of the note you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> import new note");
        System.out.println("s -> return to topic menu");
    }

    private void createNewTopic(Subject selectedSubject) {
        System.out.println("Enter name for topic and click Enter:");
        String topicName = input.next();

        if (isDuplicateTopic(topicName, selectedSubject.getListOfTopics())) {
            System.out.println("Error: You cannot have topics with the same name.\n"
                    + "Please try again.");
        } else {
            selectedSubject.addTopic(new Topic(topicName));
        }
    }

    //REQUIRES: topicName is non-empty
    //EFFECTS: return true is list already contains a topic with same name; otherwise return false
    public boolean isDuplicateTopic(String topicName, LinkedHashSet<Topic> listOfTopics) {

        return hasDuplicateName(topicName, listOfTopics);
    }

    public boolean hasDuplicateName(String name, LinkedHashSet<? extends HasName> list) {
        boolean isDuplicate = false;

        for (HasName e: list) {
            if (name.equals(e.getName())) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

}
