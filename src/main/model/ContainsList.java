package model;

import java.util.LinkedHashSet;

public abstract class ContainsList {

    protected boolean isDuplicateName(String name, LinkedHashSet<? extends HasNameAndList> list) {
        boolean isDuplicate = false;

        for (HasNameAndList e: list) {
            if (name.equals(e.getName())) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }
}
