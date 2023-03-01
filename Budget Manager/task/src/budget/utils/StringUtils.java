package budget.utils;

import static budget.utils.BudgetManagerUtils.NUMBER_RANGE_REGEX;

public class StringUtils {

    /**
     * Capitalize given string
     * @param word {@link String}
     * @return {@link String} capitalized string
     */
    public static String capitalize(String word) {
        return String.join("", word.substring(0, 1).toUpperCase(), word.substring(1).toLowerCase());
    }

    public static String createRegex(int min, int max) {
        return String.format(NUMBER_RANGE_REGEX, min, max);
    }

    public static boolean matches(String input, String regex) {
        return input.matches(regex);
    }
}
