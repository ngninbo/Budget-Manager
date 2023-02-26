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

        final int idx = getIndex();
        final String name = getName();
        String format = getFormat();

        return String.format(format, idx, name);
    }

    private String getFormat() {
        switch (this) {
            case INCOME:
            case PURCHASE:
                return "%s) Add %s\n";
            case SHOW:
                return "%s) %s list of purchases\n";
            case SORT:
                return "%s) Analyse (%s)\n";
            default:
                return "%s) %s\n";
        }
    }

    private int getIndex() {
        return (ordinal() + 1) % values().length;
    }

    private String getName() {
        switch (this) {
            case INCOME:
            case PURCHASE:
                return name().toLowerCase();
            default:
                return capitalize();
        }
    }
}
