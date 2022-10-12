package model;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;

// Represents a note that is linked to a file in the filesystem
public class Note {
    private String name;
    private Path fileLocation;
    private Status status;

    //REQUIRES: file is a valid path to a file
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

    //REQUIRES: statusNum = 1, 2, or 3
    //MODIFIES: this
    //EFFECTS: changes status based on statusNum
    // 1 -> INCOMPLETE
    // 2 -> NEED_REVISION
    // 3 -> COMPLETE
    public void setStatus(int statusNum) {

        switch (statusNum) {
            case 1:
                status = Status.INCOMPLETE;
                break;
            case 2:
                status = Status.NEED_REVISION;
                break;
            case 3:
                status = Status.COMPLETE;
                break;
        }
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
}
