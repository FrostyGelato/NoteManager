package model;

import org.json.JSONObject;

import java.nio.file.Path;

// Represents a note that is linked to a file in the filesystem
public class Note implements HasName {
    private String name;
    private Path fileLocation;
    private Status status;

    //REQUIRES: file is a path to an existing file in the filesystem
    //EFFECTS: constructs a note with a given name of file, location of file, and incomplete status
    public Note(Path file) {
        this(file, Status.INCOMPLETE);
    }

    //REQUIRES: file is a path to an existing file in the filesystem
    //EFFECTS: constructs a note with a given name of file, location of file, and given status
    public Note(Path file, Status status) {
        name = String.valueOf(file.getFileName());
        fileLocation = file;
        this.status = status;
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
            default:
                status = Status.COMPLETE;
                break;
        }
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //EFFECTS: constructs a JsonObject from a note and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("fileLocation", fileLocation);
        json.put("status", status);
        return json;
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
