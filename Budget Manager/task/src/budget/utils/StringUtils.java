package budget.utils;

public class StringUtils {

    /**
     * Capitalize given string
     * @param word {@link String}
     * @return {@link String} capitalized string
     */
    public static String capitalize(String word) {
        return String.join("", word.substring(0, 1).toUpperCase(), word.substring(1).toLowerCase());
    }
}
