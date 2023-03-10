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

        if (ordinal >= 0 && ordinal < values().length) {
            return values()[ordinal];
        } else {
            throw new IllegalArgumentException(String.format("Option by index = %s doesn't exist", ordinal));
        }
    }

    /**
     * Get values of {@link PurchaseType} as list
     * @return {@link List} - List of capitalized names of values from this enum.
     */
    public static List<String> toList() {
        return Arrays.stream(PurchaseType.values())
                .map(PurchaseType::format)
                .collect(Collectors.toList());
    }

    public static int size() {
        return values().length;
    }

    /**
     * Capitalize name of this purchase type.
     * See {@link StringUtils#capitalize(String)} for more details
     * @return {@link String} Capitalized name of this purchase type
     */
    public String capitalize() {
        return StringUtils.capitalize(name());
    }

    public String format() {
        return String.format("%n%s) %s", ordinal() + 1, capitalize());
    }
}
