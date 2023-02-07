package budget;

import budget.domain.BudgetManager;
import budget.domain.Menu;

import static budget.utils.BudgetManagerUtils.MENU_ITEMS;
import static budget.utils.BudgetManagerUtils.choiceAction;

public class BudgetManagerApplication implements Runnable {

    private final Menu menu;

    public BudgetManagerApplication(BudgetManager menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        process();
    }

    private void process() {
        while (true) {

            String input = choiceAction();

            if (!input.matches("\\d")) {
                System.out.println("\nAction must be a number between 0 and 7!\n");
            } else {
                int choice = Integer.parseInt(input);
                String item = choice == 0 ? "Exit" : MENU_ITEMS.get(choice - 1);

                switch (item) {
                    case "Add income":
                        menu.addIncome();
                        break;
                    case "Add purchase":
                        menu.addPurchase();
                        break;
                    case "Show list of purchases":
                        menu.showPurchases();
                        break;
                    case "Balance":
                        menu.showBalance();
                        break;
                    case "Save":
                        menu.save();
                        break;
                    case "Load":
                        this.menu.load();
                        break;
                    case "Analyze (Sort)":
                        menu.analyse();
                        break;
                    case "Exit":
                        menu.exit();
                        return;
                    default:
                        // Implement me
                }
            }
        }
    }
}
