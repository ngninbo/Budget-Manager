package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SortOption {

    SORT_ALL_PURCHASES,
    SORT_BY_TYPE,
    SORT_CERTAIN_TYPE;

    public static SortOption get(int ordinal) {
        return values()[ordinal];
    }

    public static List<String> toList() {
        return Arrays.stream(values())
                .map(SortOption::capitalize)
                .collect(Collectors.toList());
    }

    public String replaceUnderscores() {
        return name().replace("_", " ");
    }

    public String capitalize() {
        return capitalize(replaceUnderscores());
    }

    private static String capitalize(String name) {
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
