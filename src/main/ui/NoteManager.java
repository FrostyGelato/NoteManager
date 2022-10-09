package ui;

import model.Subject;

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
            displayMainMenu();
            userResponse = input.next().toLowerCase();

            if (userResponse.equals("q")) {
                keepGoing = false;
            } else {
                subjectSelector(userResponse);
            }
        }

        System.out.println("Goodbye!");
    }

    //MODIFIES: this
    //EFFECTS: initialize input
    private void init() {
        input = new Scanner(System.in);
    }

    //EFFECTS: displays a list of subjects and other options
    private void displayMainMenu() {
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

    private void subjectSelector(String userResponse) {
        if (userResponse.equals("n")) {
            // createNewSubject();
        } else {
            for (Subject s: listOfSubjects) {
                if (userResponse.equals(s.getName())) {
                    break;
                    // displayTopicsInSubject();
                }
            }
        }
    }
}
