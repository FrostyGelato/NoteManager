package ui;

import model.Subject;
import model.Topic;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class TopicMenu extends Menu {
    NoteMenu noteMenu = new NoteMenu();

    TopicMenu() {
        init();
    }

    //EFFECTS: displays a list of topics in selected subject and other menu options
    void displayTopicMenu(Subject selectedSubject) {

        LinkedHashSet<Topic> topicList = selectedSubject.getListOfTopics();

        printMenuHeader(selectedSubject, "Topic");

        printNamesInListOrNone(topicList, "topic");

        printAllOptions("topic", "create");
    }

    void processTopicMenuInput(Subject selectedSubject, String userResponse) {

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
                        noteMenu.displayNoteMenu(t);
                        userResponseInNoteMenu = input.next().toLowerCase();

                        if (userResponseInNoteMenu.equals("s")) {
                            keepGoing = false;
                        } else {
                            noteMenu.processNoteMenuInput(t, userResponseInNoteMenu);
                        }
                    }
                    break;
                }
            }
        }
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

    private void removeTopic(Subject selectedSubject) {
        System.out.println("Enter name of topic to remove and click Enter:");
        String topicName = input.next();

        selectedSubject.removeTopic(topicName);
    }

    protected void printExtraOptions() {
        System.out.println("s -> return to subject menu");
    }

    protected void init() {
        super.init();
        noteMenu = new NoteMenu();
    }
}
