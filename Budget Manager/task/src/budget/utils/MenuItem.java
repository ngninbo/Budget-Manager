package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuItem {

    INCOME,
    PURCHASE,
    SHOW,
    BALANCE,
    SAVE,
    LOAD,
    SORT,
    EXIT;

    public static MenuItem get(int index) {

        if (index == -1) {
            return EXIT;
        } else if (index < values().length) {
            return values()[index];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String capitalize() {
        return capitalize(name());
    }

    private static String capitalize(String name) {
        return name.charAt(0) + name.substring(1).toLowerCase();
    }

    public static List<String> toList() {
        return Arrays.stream(values())
                .map(MenuItem::format)
                .collect(Collectors.toList());
    }

    private String format() {

        switch (this) {
            case INCOME:
            case PURCHASE:
                return String.format("%s) Add %s\n", ordinal() + 1, name().toLowerCase());
            case SHOW:
                return String.format("%s) %s list of purchases\n", ordinal() + 1, capitalize());
            case SORT:
                return String.format("%s) Analyse (%s)\n", ordinal() + 1, capitalize());
            case EXIT:
               return String.format("%s) %s\n", 0, capitalize());
            default:
                return String.format("%s) %s\n", ordinal() + 1, capitalize());
        }
    }
}
