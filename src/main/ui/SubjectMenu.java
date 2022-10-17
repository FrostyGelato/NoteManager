package ui;

import model.ListOfSubjects;
import model.Subject;

import java.util.LinkedHashSet;

public class SubjectMenu extends Menu {
    TopicMenu topicMenu = new TopicMenu();
    private ListOfSubjects listOfSubjects;

    //MODIFIES: this
    //EFFECTS: allows for user input, sets up a list for subjects, and allows for handling of topics
    SubjectMenu() {
        init();
    }

    //EFFECTS: displays a list of subjects and other options
    void displaySubjectMenu() {
        LinkedHashSet<Subject> subjectList = listOfSubjects.getListOfSubjects();

        System.out.println("Subject Menu\n");
        System.out.println("Subject List:");

        printNamesInListOrNone(subjectList, "subject");

        printAllOptions("subject", "create");
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    void processSubjectMenuInput(String userResponse) {
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
                        topicMenu.displayTopicMenu(s);
                        userResponseInTopicMenu = input.next().toLowerCase();

                        if (userResponseInTopicMenu.equals("s")) {
                            keepGoing = false;
                        } else {
                            topicMenu.processTopicMenuInput(s, userResponseInTopicMenu);
                        }
                    }
                    break;
                }
            }
        }
    }

    //REQUIRES: user input non-empty string
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
    //EFFECTS: removes a subject based on user input, if subject with given name exists; otherwise, do nothing
    private void removeSubject() {
        System.out.println("Enter name of subject to remove and click Enter:");
        String subjectName = input.next();

        listOfSubjects.removeSubject(subjectName);
    }

    //EFFECTS: prints quit option
    protected void printExtraOptions() {
        System.out.println("q -> quit");
    }

    //MODIFIES: this
    //EFFECTS: allows for user input, sets up a list for subjects, and allows for handling of topics
    protected void init() {
        super.init();
        topicMenu = new TopicMenu();
        listOfSubjects = new ListOfSubjects();
    }
}
