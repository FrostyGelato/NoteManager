package model;

import java.io.File;

// Represents a note that is linked to a file in the filesystem
public class Note {
    private String name;
    private String fileType;
    private File fileLocation;
    private int id;
    private Status status;

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

    public int getId() {
        return 0;
    }

    public Status getStatus() {
        return Status.INCOMPLETE;
    }
}
