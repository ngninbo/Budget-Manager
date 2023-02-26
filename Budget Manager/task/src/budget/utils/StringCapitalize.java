package budget.utils;

public class StringCapitalize {

    /**
     * Capitalize given string
     * @param word {@link String}
     * @return {@link String} capitalized string
     */
    public static String capitalize(String word) {
        return String.join("", word.substring(0, 1), word.substring(1).toLowerCase());
    }
}
