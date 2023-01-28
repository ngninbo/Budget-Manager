package budget;

import budget.core.PurchaseFileManger;
import budget.domain.BudgetManager;
import budget.domain.ShoppingList;

import static budget.utils.BudgetManagerUtils.MENU_ITEMS;
import static budget.utils.BudgetManagerUtils.choiceAction;

public class BudgetManagerApplication implements Runnable {

    private BudgetManager budgetManager;
    private ShoppingList shoppingList = new ShoppingList();
    private final PurchaseFileManger fileManger;

    public BudgetManagerApplication(String filename) {
        this.fileManger = new PurchaseFileManger(filename);
        this.budgetManager = new BudgetManager(shoppingList);
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
                        shoppingList.addPurchase();
                        break;
                    case "Show list of purchases":
                        shoppingList.show();
                        break;
                    case "Balance":
                        budgetManager.showBalance();
                        break;
                    case "Save":
                        save();
                        break;
                    case "Load":
                        load();
                        break;
                    case "Analyze (Sort)":
                        shoppingList.analyse();
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

    public void save() {
        fileManger.save(budgetManager);
        System.out.println("\nPurchases were saved!\n");
    }

    public void load() {
        this.budgetManager = fileManger.load();
        this.shoppingList = budgetManager.getShoppingList();
        System.out.println("\nPurchases were loaded!\n");
    }
}
