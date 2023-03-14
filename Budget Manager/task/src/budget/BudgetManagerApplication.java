package budget;

import budget.domain.BudgetManager;
import budget.menu.Menu;
import budget.menu.StartMenu;

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
        new StartMenu(menu).start();
    }
}
