package ui;

import model.HasName;

import java.util.LinkedHashSet;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner input;

    protected void init() {
        input = new Scanner(System.in);
    }

    //EFFECTS: prints title for menu
    protected void printMenuHeader(HasName selectedParent, String item) {
        System.out.println(item + " Menu");
        System.out.println(item + " list for " + selectedParent.getName() + ":");
    }

    protected void printNamesInListOrNone(LinkedHashSet<?extends HasName> list, String item) {
        if (list.isEmpty()) {
            System.out.println("You have no " + item + "s.");
        } else {
            for (HasName e: list) {
                System.out.println(e.getName());
            }
        }
    }

    protected void printAllOptions(String item, String action) {
        System.out.println("\nEnter the name of the " + item + " you wish to open\n"
                + "Or select from the following options:");
        System.out.println("n -> " + action + " new " + item);
        System.out.println("r -> remove a " + item);
        printExtraOptions();
    }

    protected abstract void printExtraOptions();
}
