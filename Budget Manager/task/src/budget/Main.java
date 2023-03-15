package budget;

import budget.domain.BudgetManager;

public class Main {
    public static void main(String[] args) {
        String filename = args.length < 1 ? "purchases.txt" : args[1];
        new BudgetManagerApplication(BudgetManager.of(filename)).run();
    }
}
