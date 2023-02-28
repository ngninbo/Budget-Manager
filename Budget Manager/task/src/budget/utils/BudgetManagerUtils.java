package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BudgetManagerUtils {

    public static final String BACK = "Back\n";
    public static final String ALL = "All";
    public static final String PURCHASE_TYPE_CHOICE_MESSAGE = "\nChoose the type of purchase";
    public static final String VALID_NUMBER_INPUT_REQUIRED_TEXT = "\nPlease enter a number between %s and %s!";
    public static final String ACTION_CHOICE_MESSSAGE = "Choose your action";
    public static final String PURCHASE= "purchase";
    public static final String TOTAL = "Total";
    public static final String THE = "The";
    public static final String LIST_IS_EMPTY = "list is empty";

    public static String enter(String command) {
        System.out.printf("%s:\n", command);
        return new Scanner(System.in).nextLine();
    }

    /**
     * Display options inclusive additional ones and request user to choose one of them
     * @param options {@link List} List of options to be displayed
     * @param message {@link String} Message to be displayed
     * @param additionalOptions Array of {@link String} - additional options - optional
     * @return {@link String} Index of option choose by the user
     */
    public static String choose(List<String> options, String message, String... additionalOptions) {

        if (additionalOptions.length > 0) {
            Arrays.stream(additionalOptions).forEach(s -> options.add(String.format("\n%s) %s", options.size() + 1, s)));
        }

        displayMenu(options, message);
        return new Scanner(System.in).nextLine();
    }

    private static void displayMenu(List<String> items, String message) {
        StringBuilder sb = new StringBuilder(message);

        items.forEach(sb::append);

        System.out.printf("%s", sb);
    }
}
