package model;

import java.io.File;

// Represents a note that is linked to a file in the filesystem
public class Note {
    private String name;
    private String fileType;
    private File fileLocation;
    //private int id;
    private Status status;

    //REQUIRES: a file with a valid path
    //EFFECTS: constructs a note with a given name of file, type of file, location of file, and incomplete status
    public Note(File file) {

    }

    //EFFECTS: open the file linked to this note
    public void openFile() {

    }

    public String getName() {
        return "";
    }

    public String getFileType() {
        return "";
    }

    public File getFileLocation() {
        return null;
    }

    /*public int getId() {
        return 0;
    }*/

    public Status getStatus() {
        return Status.INCOMPLETE;
    }
}
