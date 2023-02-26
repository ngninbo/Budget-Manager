package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PurchaseType {

    FOOD,
    CLOTHES,
    ENTERTAINMENT,
    OTHER;

    public static PurchaseType get(int ordinal) {
        return values()[ordinal];
    }

    public static List<String> toList() {
        return Arrays.stream(PurchaseType.values())
                .map(PurchaseType::capitalize)
                .collect(Collectors.toList());
    }

    public static List<String> toShowList() {
        List<String> options = toList();
        options.add("All");
        return options;
    }

    public String capitalize() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
