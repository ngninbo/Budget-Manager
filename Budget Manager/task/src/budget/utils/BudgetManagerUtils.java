package budget.utils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BudgetManagerUtils {

    public static final List<String> MENU_ITEMS = List.of("Add income", "Add purchase", "Show list of purchases",
            "Balance", "Save", "Load", "Analyze (Sort)", "Exit");

    public static String requestInput(String command) {
        System.out.printf("%s:\n", command);
        return new Scanner(System.in).nextLine();
    }

    public static String choiceAction() {
        StringBuilder sb = new StringBuilder("Choose your action:\n");
        MENU_ITEMS.stream()
                .map(BudgetManagerUtils::formatItem)
                .forEach(sb::append);

        System.out.println(sb);
        return new Scanner(System.in).nextLine();
    }

    public static int choiceSort() {
        List<String> options = Stream.concat(SortOption.toList().stream(), Stream.of("Back")).collect(Collectors.toList());
        displayMenu(options, "How do you want to sort?");
        return new Scanner(System.in).nextInt();
    }


    public static int choiceTypeOfPurchases() {
        displayMenu(ShowOption.toList(), "Choose the type of purchases");
        return new Scanner(System.in).nextInt();
    }

    public static int choiceTypeOfPurchase() {
        List<String> options = Stream.concat(PurchaseType.toList().stream(), Stream.of("Back")).collect(Collectors.toList());
        displayMenu(options, "Choose the type of purchase");
        return new Scanner(System.in).nextInt();
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

    private static String formatItem(String item) {
        return "Exit".equals(item) ? String.format("%s) %s", 0, item) : String.format("%s) %s\n", MENU_ITEMS.indexOf(item) + 1, item);
    }

    public static String capitalize(String purchaseType) {
        return purchaseType.charAt(0) + purchaseType.substring(1).toLowerCase();
    }
}
