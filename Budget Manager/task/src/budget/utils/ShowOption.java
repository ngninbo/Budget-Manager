package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ShowOption {

    FOOD,
    CLOTHES,
    ENTERTAINMENT,
    OTHER,
    ALL,
    BACK;

    public static ShowOption getOption(int ordinal) {
        return Arrays.stream(values())
                .filter(showOption -> ordinal == showOption.ordinal())
                .findFirst().orElseThrow();
    }

    public static List<String> toList() {
        return Arrays.stream(values())
                .map(Enum::name)
                .map(BudgetManagerUtils::capitalize)
                .collect(Collectors.toList());
    }
}
