package ui;

import java.util.Scanner;

//Note manager application
public class MainMenu {
    private Scanner input;
    private SubjectMenu subjectMenu;

    //EFFECTS: runs the note manager application
    public MainMenu() {
        runManager();
    }

    //MODIFIES: this
    //EFFECTS: displays console UI and receives user input
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

        System.out.println("The program has exited.");
    }

    //MODIFIES: this
    //EFFECTS: initialize input and constructs a menu for manipulating subjects
    private void init() {
        input = new Scanner(System.in);
        subjectMenu = new SubjectMenu();
    }
}
