package model;

import java.awt.*;
import java.io.IOException;
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
        name = String.valueOf(file.getFileName());
        fileLocation = file;
        status = Status.INCOMPLETE;
    }

    //EFFECTS: open the file linked to this note
    public void openFile() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(fileLocation.toFile());
    }

    //MODIFIES: this
    //EFFECTS: changes this status to given status
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Path getFileLocation() {
        return fileLocation;
    }

    public Status getStatus() {
        return status;
    }

    /*public int getId() {
        return 0;
    }*/

    /*public String getFileType() {
        return "";
    }*/
}
