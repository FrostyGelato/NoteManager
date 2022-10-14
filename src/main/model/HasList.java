package model;

import java.util.LinkedHashSet;

public abstract class HasList {

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
