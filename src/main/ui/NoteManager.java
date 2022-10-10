package ui;

import model.Subject;
import model.Topic;

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
    private void runManager() {

        boolean keepGoing = true;
        String userResponse = null;

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
        System.out.println("Note Manager");
        System.out.println("Subject List:");

        if (listOfSubjects.isEmpty()) {
            System.out.println("You have no subjects.");
        } else {
            for (Subject s: listOfSubjects) {
                System.out.println(s.getName());
            }
        }

        System.out.println("Enter the name of the subject you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> create new subject");
        System.out.println("q -> quit");
    }

    private void processSubjectMenuInput(String userResponse) {
        if (userResponse.equals("n")) {
            createNewSubject();
        } else {
            for (Subject s: listOfSubjects) {
                if (userResponse.equals(s.getName())) {

                    displayTopicMenu(s);

                    break;
                }
            }
        }
    }

    //EFFECTS: displays a list of topics in selected subject
    private void displayTopicMenu(Subject selectedSubject) {

        System.out.println("Topic List:");

        if (selectedSubject.getListOfTopics().isEmpty()) {
            System.out.println("You have no topics.");
        } else {
            for (Topic t: selectedSubject.getListOfTopics()) {
                System.out.println(t.getName());
            }
        }

        System.out.println("Enter the name of the topic you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> create new topic");
        System.out.println("s -> return to subject menu");
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
        boolean isDuplicate = false;

        for (Subject s: listOfSubjects) {
            if (subjectName.equals(s.getName())) {
                isDuplicate = true;
            }
        }

        return isDuplicate;
    }
}
