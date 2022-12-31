package ui;

import model.ListOfSubjects;
import model.Subject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

// Displays the subject menu where user can view their subjects
public class SubjectMenu extends Menu {
    TopicMenu topicMenu = new TopicMenu();
    private ListOfSubjects listOfSubjects;
    private static final String JSON_STORE = "./data/subjectList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //MODIFIES: this
    //EFFECTS: allows for user input, sets up a list for subjects, and allows for handling of topics
    SubjectMenu() {
        init();
    }

    //EFFECTS: displays a list of subjects and other options
    void displaySubjectMenu() {
        LinkedHashMap<String, Subject> subjectList = listOfSubjects.getListOfSubjects();

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
        } else if (userResponse.equals("s")) {
            save();
        } else if (userResponse.equals("l")) {
            load();
        } else {
            for (Subject s: listOfSubjects.getListOfSubjects()) {
                if (userResponse.equals(s.getName())) {

                    processSelectedSubject(s);
                    break;
                }
            }
        }
    }

    private void processSelectedSubject(Subject s) {
        boolean keepGoing = true;

        while (keepGoing) {
            topicMenu.displayTopicMenu(s);
            String userResponseInTopicMenu = input.next().toLowerCase();

            if (userResponseInTopicMenu.equals("s")) {
                keepGoing = false;
            } else {
                topicMenu.processTopicMenuInput(s, userResponseInTopicMenu);
            }
        }
    }

    //REQUIRES: user input non-empty string
    //MODIFIES: this
    //EFFECTS: asks user for subject name and creates subject with given name in list
    private void createNewSubject() {
        System.out.println("Enter name for subject and click Enter:");
        String subjectName = input.next().toLowerCase();

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

        System.out.println("s -> save to file");
        System.out.println("l -> load from file");
        System.out.println("q -> quit");
    }

    //EFFECTS: saves list of subjects to file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfSubjects);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        System.out.println("Data has been saved to file.");
    }

    //MODIFIES: this
    //EFFECTS: loads list of subjects from file
    public void load() {
        try {
            listOfSubjects = jsonReader.read();
            System.out.println("Data has been loaded from file.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    //MODIFIES: this
    //EFFECTS: allows for user input, sets up a list for subjects, and allows for handling of topics
    protected void init() {
        super.init();
        topicMenu = new TopicMenu();
        listOfSubjects = new ListOfSubjects();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }
}
