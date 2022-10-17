package ui;

import model.Note;
import model.Topic;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;

public class NoteMenu extends Menu {

    //EFFECTS: allows for user input
    NoteMenu() {
        init();
    }

    //EFFECTS: displays a list of note in selected topic and other menu options
    void displayNoteMenu(Topic selectedTopic) {

        printMenuHeader(selectedTopic, "Note");

        if (selectedTopic.getListOfNotes().isEmpty()) {
            System.out.println("You have no notes.");
        } else {
            printNotesList(selectedTopic.getListOfNotes());
        }

        printAllOptions("note", "import");
    }

    //EFFECTS: handles user input
    void processNoteMenuInput(Topic selectedTopic, String userResponse) {
        if (userResponse.equals("n")) {
            createNote(selectedTopic);
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

    private void createNote(Topic selectedTopic) {
        System.out.println("Enter the full path of the note:");
        String noteStringPath = input.next();
        Path notePath = Paths.get(noteStringPath);
        if (selectedTopic.isDuplicatePath(notePath)) {
            System.out.println("Error: You cannot have notes with the same path.\n"
                    + "Please try again.");
        } else {
            selectedTopic.addNote(notePath);
        }
    }

    private void changeNoteStatus(Topic selectedTopic) {
        System.out.println("Here are your notes:");
        printNotesList(selectedTopic.getListOfNotes());
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

    private void printNotesList(LinkedHashSet<Note> listOfNotes) {
        for (Note n: listOfNotes) {
            System.out.println(n.getName() + " - " + n.getStatus() + " - " + n.getFileLocation());
        }
    }

    protected void printExtraOptions() {
        System.out.println("c -> change note status");
        System.out.println("s -> return to topic menu");
    }
}
