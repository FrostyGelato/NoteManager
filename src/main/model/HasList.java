package model;

import java.util.LinkedHashSet;

public abstract class HasList {

    //REQUIRES: name is non-empty string
    //EFFECTS: returns true if list contains item with same name as given name; otherwise, return false
    protected boolean isDuplicateName(String name, LinkedHashSet<? extends HasName> list) {
        boolean isDuplicate = false;

        for (HasName e: list) {
            if (name.equals(e.getName())) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }
}
