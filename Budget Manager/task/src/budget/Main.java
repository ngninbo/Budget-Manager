package budget;

import budget.domain.BudgetManager;
import budget.domain.ShoppingList;

public class Main {
    public static void main(String[] args) {
        String filename = args.length < 1 ? "purchases.txt" : args[1];
        new BudgetManagerApplication(new BudgetManager(new ShoppingList(), filename)).run();
    }
}
