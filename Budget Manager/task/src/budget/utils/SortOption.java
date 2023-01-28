package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SortOption {

    SORT_ALL_PURCHASES ("Sort all purchases"),
    SORT_BY_TYPE ("Sort by type"),
    SORT_CERTAIN_TYPE ("Sort certain type");

    private final String description;

    SortOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static SortOption getOptionBy(int ordinal) {
        return Arrays.stream(values())
                .filter(sortOption -> ordinal == sortOption.ordinal())
                .findFirst()
                .orElseThrow();
    }

    public static List<String> toList() {
        return Arrays.stream(values())
                .map(SortOption::getDescription)
                .collect(Collectors.toList());
    }
}
