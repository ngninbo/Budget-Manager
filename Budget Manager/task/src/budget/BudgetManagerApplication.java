package budget;

import budget.domain.BudgetManager;
import budget.domain.Menu;
import budget.utils.BudgetManagerUtils;
import budget.utils.MenuItem;

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

            String input = BudgetManagerUtils.choose(MenuItem.toList(), "Choose your action:");

            if (!input.matches("[0-7]")) {
                System.out.println("\nAction must be a number between 0 and 7!\n");
            } else {
                int choice = Integer.parseInt(input);
                MenuItem item = MenuItem.get(choice - 1);

                switch (item) {
                    case INCOME:
                        menu.addIncome();
                        break;
                    case PURCHASE:
                        menu.addPurchase();
                        break;
                    case SHOW:
                        menu.showPurchases();
                        break;
                    case BALANCE:
                        menu.showBalance();
                        break;
                    case SAVE:
                        menu.save();
                        break;
                    case LOAD:
                        this.menu.load();
                        break;
                    case SORT:
                        menu.analyse();
                        break;
                    case EXIT:
                        menu.exit();
                        return;
                    default:
                        // Implement me
                }
            }
        }
    }
}
