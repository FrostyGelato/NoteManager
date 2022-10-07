package model;

import java.io.File;

// Represents a note that is linked to a file in the filesystem
public class Note {
    private String name;
    private String fileType;
    private File fileLocation;
    private Status status;
}
