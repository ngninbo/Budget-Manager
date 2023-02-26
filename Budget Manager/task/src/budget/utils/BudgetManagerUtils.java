package budget.utils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class BudgetManagerUtils {

    public static final String BACK = "Back";

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

    public static String chooseSortType() {
        return choose(SortOption.toList(), "How do you want to sort?");
    }

    public static String chooseTypeOfPurchases() {
        return choose(PurchaseType.toShowList(), "Choose the type of purchases");
    }

    public static String chooseTypeOfPurchase() {
        return choose(PurchaseType.toList(), "Choose the type of purchase");
    }

    public static int choicePurchaseType() {
        displayMenu(PurchaseType.toList(), "Choose the type of purchase");
        return new Scanner(System.in).nextInt();
    }

    private static void displayMenu(List<String> items, String message) {
        StringBuilder sb = new StringBuilder(message);

        formatOptions(items).forEach(sb::append);

        System.out.printf("%n%s%n", sb);
    }

    private static Stream<String> formatOptions(List<String> options) {
        return options.stream().map(option -> String.format("%n%s) %s", options.indexOf(option) + 1, option));
    }

    private static String choose(List<String> options, String message) {
        options.add(BACK);
        displayMenu(options, message);
        return new Scanner(System.in).nextLine();
    }
}
