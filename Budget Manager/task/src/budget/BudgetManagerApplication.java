package budget;

import budget.menu.Menu;
import budget.menu.StartMenu;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BudgetManagerApplication implements Runnable {

    private final Menu menu;

    @Override
    public void run() {
        process();
    }

    private void process() {
        new StartMenu(menu).start();
    }
}
