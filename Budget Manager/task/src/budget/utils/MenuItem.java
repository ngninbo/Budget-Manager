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

    /**
     * Get item by given index/ordinal.
     * Throws {@link IllegalArgumentException} when item by given index doesn't exist
     * @param index {@link Integer} index or ordinal of item to search for
     * @return {@link MenuItem} Menu item corresponding to given index/ordinal
     */
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
        return StringCapitalize.capitalize(name());
    }

    public static List<String> toList() {
        return Arrays.stream(values())
                .map(MenuItem::format)
                .collect(Collectors.toList());
    }

    /**
     * Format given menu item before displaying it in the selection menu
     * @return {@link String} Formatted menu item ready for display
     */
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
