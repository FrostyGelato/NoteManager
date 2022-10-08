package model;

import java.nio.file.Path;

// Represents a note that is linked to a file in the filesystem
public class Note {
    private String name;
    private Path fileLocation;
    private Status status;
    //private String fileType;
    //private int id;

    //REQUIRES: a file with a valid path
    //EFFECTS: constructs a note with a given name of file, location of file, and incomplete status
    public Note(Path file) {

    }

    //EFFECTS: open the file linked to this note
    public void openFile() {

    }

    //MODIFIES: this
    //EFFECTS: changes this status to given status
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return "";
    }
    
    public Path getFileLocation() {
        return null;
    }

    public Status getStatus() {
        return Status.INCOMPLETE;
    }

    /*public int getId() {
        return 0;
    }*/

    /*public String getFileType() {
        return "";
    }*/
}
