package ui;

import model.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class NoteManager {
    private Scanner input;
    private ListOfSubjects listOfSubjects;

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
        listOfSubjects = new ListOfSubjects();
    }

    //EFFECTS: displays a list of subjects and other options
    private void displaySubjectMenu() {
        LinkedHashSet<Subject> subjectList = listOfSubjects.getListOfSubjects();

        System.out.println("Subject Menu\n");
        System.out.println("Subject List:");

        if (subjectList.isEmpty()) {
            System.out.println("You have no subjects.");
        } else {
            printNamesInList(subjectList);
        }

        printOptions("subject", "create");
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
            for (Subject s: listOfSubjects.getListOfSubjects()) {
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
    //EFFECTS: asks user for subject name and creates subject with given name in list
    private void createNewSubject() {
        System.out.println("Enter name for subject and click Enter:");
        String subjectName = input.next();

        if (listOfSubjects.containsDuplicateSubject(subjectName)) {
            System.out.println("Error: You cannot have subjects with the same name.\n"
                    + "Please try again.");
        } else {
            listOfSubjects.addSubject(subjectName);
        }
    }

    //MODIFIES: this
    private void removeSubject() {
        System.out.println("Enter name of subject to remove and click Enter:");
        String subjectName = input.next();

        listOfSubjects.removeSubject(subjectName);
    }

    //EFFECTS: displays a list of topics in selected subject and other menu options
    private void displayTopicMenu(Subject selectedSubject) {
        LinkedHashSet<Topic> topicList = selectedSubject.getListOfTopics();

        System.out.println("Topic Menu\n");
        System.out.println("Topic List for " + selectedSubject.getName() + ":");

        if (topicList.isEmpty()) {
            System.out.println("You have no topics.");
        } else {
            printNamesInList(topicList);
        }

        printOptions("topic", "create");
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
            selectedTopic.addNote(Path.of(input.next()));
        } else if (userResponse.equals("r")) {
            removeNote(selectedTopic);
        } else if (userResponse.equals("c")) {
            changeNoteStatus(selectedTopic);
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

    private void changeNoteStatus(Topic selectedTopic) {
        System.out.println("Here are your notes:");
        printListOfNotes(selectedTopic);
        System.out.println("Enter path of note you wish to change:");
        String file = input.next();

        for (Note n: selectedTopic.getListOfNotes()) {
            if (file.equals(n.getFileLocation().toString())) {
                System.out.println("Enter: \n1 to mark as incomplete");
                System.out.println("2 to mark as needing revision");
                System.out.println("3 to mark as complete");
                n.setStatus(input.nextInt());
            }
        }
    }

    private void removeNote(Topic selectedTopic) {
        System.out.println("Enter path of note to remove and click Enter:");
        String filePath = input.next();

        selectedTopic.removeNote(filePath);
    }

    //EFFECTS: displays a list of note in selected topic and other menu options
    private void displayNoteMenu(Topic selectedTopic) {

        System.out.println("Note Menu\n");

        System.out.println("Note List for " + selectedTopic.getName() + ":");

        if (selectedTopic.getListOfNotes().isEmpty()) {
            System.out.println("You have no notes.");
        } else {
            printListOfNotes(selectedTopic);
        }

        printOptions("note", "import");
        System.out.println("c -> change note status");
        System.out.println("s -> return to topic menu");
    }

    private void createNewTopic(Subject selectedSubject) {
        System.out.println("Enter name for topic and click Enter:");
        String topicName = input.next();

        if (selectedSubject.containsDuplicateTopic(topicName)) {
            System.out.println("Error: You cannot have topics with the same name.\n"
                    + "Please try again.");
        } else {
            selectedSubject.addTopic(topicName);
        }
    }

    private void printOptions(String item, String action) {
        System.out.println("\nEnter the name of the " + item + " you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> " + action + " new " + item);
        System.out.println("r -> remove a " + item);
    }

    private void printListOfNotes(Topic selectedTopic) {
        for (Note n: selectedTopic.getListOfNotes()) {
            System.out.println(n.getName() + " - " + n.getStatus() + " - " + n.getFileLocation());
        }
    }

    private void printNamesInList(LinkedHashSet<? extends HasNameAndList> list) {
        for (HasNameAndList e: list) {
            System.out.println(e.getName());
        }
    }
}
