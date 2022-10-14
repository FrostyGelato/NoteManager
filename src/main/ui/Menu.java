package ui;

import model.HasNameAndList;

import java.util.LinkedHashSet;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner input;

    protected void printMenuHeader(HasNameAndList selectedParent, String item) {
        System.out.println(item + " Menu");
        System.out.println(item + " list for " + selectedParent.getName() + ":");
    }

    protected void printNamesInList(LinkedHashSet<? extends HasNameAndList> list) {
        for (HasNameAndList e: list) {
            System.out.println(e.getName());
        }
    }

    protected void printOptions(String item, String action) {
        System.out.println("\nEnter the name of the " + item + " you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> " + action + " new " + item);
        System.out.println("r -> remove a " + item);
    }
}
