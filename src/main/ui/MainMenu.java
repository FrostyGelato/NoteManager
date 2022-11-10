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
    //EFFECTS: displays console UI and processes user input
    private void runManager() {

        boolean keepGoing = true;
        String userResponse;

        init();

        System.out.println("Notice: Commands need to be in lowercase, and names are in lower-case.");
        askToLoad();

        while (keepGoing) {
            subjectMenu.displaySubjectMenu();
            userResponse = input.next().toLowerCase();

            if (userResponse.equals("q")) {
                checkToSave();
                keepGoing = false;
            } else {
                subjectMenu.processSubjectMenuInput(userResponse);
            }
        }

        System.out.println("The program has exited.");
    }

    //MODIFIES: this
    //EFFECTS: save list of subjects to file based on user input
    private void askToLoad() {
        System.out.println("Would you like to load your notes collection?");
        System.out.println("Press y to load; n to proceed without loading:");
        String userResponse = input.next().toLowerCase();
        if (userResponse.equals("y")) {
            subjectMenu.load();
        }
    }

    //EFFECTS: save list of subjects to file based on user input
    private void checkToSave() {
        System.out.println("Would you like to save your notes collection?");
        System.out.println("Press y to save; n to discard changes:");
        String userResponse = input.next().toLowerCase();
        if (userResponse.equals("y")) {
            subjectMenu.save();
        }
    }

    //MODIFIES: this
    //EFFECTS: initialize input and constructs a menu for manipulating subjects
    private void init() {
        input = new Scanner(System.in);
        subjectMenu = new SubjectMenu();
    }
}
