package budget;

import budget.domain.BudgetManager;

import static budget.utils.BudgetManagerUtils.MENU_ITEMS;
import static budget.utils.BudgetManagerUtils.choiceAction;

public class BudgetManagerApplication implements Runnable {

    private final BudgetManager budgetManager;

    public BudgetManagerApplication(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    @Override
    public void run() {

        while (true) {

            String input = choiceAction();

            if (!input.matches("\\d")) {
                System.out.println("Action must be a number between 0 and 7!");
            } else {
                int choice = Integer.parseInt(input);
                String item = choice == 0 ? "Exit" : MENU_ITEMS.get(choice - 1);

                switch (item) {
                    case "Add income":
                        budgetManager.addIncome();
                        break;
                    case "Add purchase":
                        budgetManager.addPurchase();
                        break;
                    case "Show list of purchases":
                        budgetManager.showPurchases();
                        break;
                    case "Balance":
                        budgetManager.showBalance();
                        break;
                    case "Save":
                        budgetManager.save();
                        break;
                    case "Load":
                        this.budgetManager.load();
                        break;
                    case "Analyze (Sort)":
                        budgetManager.analyse();
                        break;
                    case "Exit":
                        System.out.println("\nBye!");
                        return;
                    default:
                        // Implement me
                }
            }
        }
    }
}
