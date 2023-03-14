package budget.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class BudgetManagerUtils {

    public static final String BACK = "Back\n";
    public static final String ALL = "All";
    public static final String PURCHASE_TYPE_CHOICE_MESSAGE = "\nChoose the type of purchase";
    public static final String VALID_NUMBER_INPUT_REQUIRED_TEXT = "\nPlease enter a number between %s and %s!\n";
    public static final String ACTION_CHOICE_MESSSAGE = "Choose your action";
    public static final String PURCHASE= "purchase";
    public static final String TOTAL = "Total";
    public static final String THE = "The";
    public static final String LIST_IS_EMPTY = "list is empty";
    public static final String NUMBER_RANGE_REGEX = "[%d-%d]";
    public static final String ENTER_INCOME = "\nEnter income";
    public static final String ENTER_PURCHASE_NAME = "\nEnter purchase name";
    public static final String ENTER_PRICE = "Enter its price";

    public static String enter(String command) {
        System.out.printf("%s:\n", command);
        return new Scanner(System.in).nextLine();
    }

    public static BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input.replace("$", "")).setScale(2, RoundingMode.HALF_UP);
    }
}
