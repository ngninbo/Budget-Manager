package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BudgetManagerUtils {

    public static final String BACK = "Back";
    public static final String ALL = "All";
    public static final String PURCHASE_TYPE_CHOICE_MESSAGE = "Choose the type of purchase";

    public static String requestInput(String command) {
        System.out.printf("%s:\n", command);
        return new Scanner(System.in).nextLine();
    }

    public static String choiceAction() {
        System.out.println("Choose your action:");
        MenuItem
                .toList()
                .forEach(System.out::print);
        return new Scanner(System.in).nextLine();
    }

    /**
     * Display options inclusive additional ones and request user to choose one of them
     * @param options {@link List} List of options to be displayed
     * @param message {@link String} Message to be displayed
     * @param additionalOptions {@link String[]} additional options - optional
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

        System.out.printf("%n%s%n", sb);
    }
}
