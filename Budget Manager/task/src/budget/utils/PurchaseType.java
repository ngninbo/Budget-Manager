package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PurchaseType {

    FOOD,
    CLOTHES,
    ENTERTAINMENT,
    OTHER;

    /**
     * Get purchase type by given index/ordinal.
     * Throws {@link IllegalArgumentException} when item by given index doesn't exist
     * @param ordinal {@link Integer} index or ordinal of item to search for
     * @return {@link PurchaseType} Purchase type corresponding to given index/ordinal
     */
    public static PurchaseType get(int ordinal) {
        return values()[ordinal];
    }

    /**
     * Get values of {@link PurchaseType} as list
     * @return {@link List} - List of capitalized names of values from this enum.
     */
    public static List<String> toList() {
        return Arrays.stream(PurchaseType.values())
                .map(PurchaseType::capitalize)
                .collect(Collectors.toList());
    }

    /**
     * Same as {@link #toList()} with difference that item <i>Back</i> is added to the list
     * @return {@link List} - Extended list from {@link #toList()}
     */
    public static List<String> toShowList() {
        List<String> options = toList();
        options.add("All");
        return options;
    }

    /**
     * Capitalize name of this purchase type.
     * See {@link StringCapitalize#capitalize(String)} for more details
     * @return {@link String} Capitalized name of this purchase type
     */
    public String capitalize() {
        return StringCapitalize.capitalize(name());
    }
}
