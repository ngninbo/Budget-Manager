package budget.utils;

public class StringCapitalize {

    /**
     * Capitalize given string
     * @param word {@link String}
     * @return {@link String} capitalized string
     */
    public static String capitalize(String word) {
        return word.charAt(0) + word.substring(1).toLowerCase();
    }
}
