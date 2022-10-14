package ui;

import java.util.Scanner;

public class MainMenu extends Menu {
    private SubjectMenu subjectMenu;

    //EFFECTS: runs the note manager application
    public MainMenu() {
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
            subjectMenu.displaySubjectMenu();
            userResponse = input.next().toLowerCase();

            if (userResponse.equals("q")) {
                keepGoing = false;
            } else {
                subjectMenu.processSubjectMenuInput(userResponse);
            }
        }

        System.out.println("Goodbye!");
    }

    //MODIFIES: this
    //EFFECTS: initialize input and list of subjects
    private void init() {
        input = new Scanner(System.in);
        subjectMenu = new SubjectMenu();
    }
}
