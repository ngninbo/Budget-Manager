package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SortOption {

    SORT_ALL_PURCHASES,
    SORT_BY_TYPE,
    SORT_CERTAIN_TYPE;

    /**
     * Get purchase type by given index/ordinal.
     * @param ordinal {@link Integer} index/ordinal of option to look for
     * @return {@link SortOption} Sort option
     */
    public static SortOption get(int ordinal) {

        if (ordinal >= 0 && ordinal < values().length) {
            return values()[ordinal];
        } else {
            throw new IllegalArgumentException(String.format("Option by index = %s doesn't exist", ordinal));
        }
    }

    /**
     * Get list from this enums values.
     * It replaces all underscores with whitespaces and capitalize elements.
     * @return {@link List} List of sort options
     */
    public static List<String> toList() {
        return Arrays.stream(values())
                .map(SortOption::format)
                .collect(Collectors.toList());
    }

    public String replaceUnderscores() {
        return name().replace("_", " ");
    }

    public String format() {
        return String.format("%n%s) %s", ordinal() + 1, StringCapitalize.capitalize(replaceUnderscores()));
    }
}
