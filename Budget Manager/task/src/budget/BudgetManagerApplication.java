package budget;

import budget.domain.BudgetManager;
import budget.domain.Menu;
import budget.utils.MenuItem;

import static budget.utils.BudgetManagerUtils.VALID_NUMBER_INPUT_REQUIRED_TEXT;
import static budget.utils.BudgetManagerUtils.choiceMenuItem;

public class BudgetManagerApplication implements Runnable {

    private final Menu menu;
    private MenuItem item;

    public BudgetManagerApplication(BudgetManager menu) {
        this.menu = menu;
    }

    @Override
    public void run() {
        process();
    }

    private void process() {
        while (!menu.process(item)) {

            String input = choiceMenuItem();

            if (!input.matches("[0-7]")) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT.concat("%n\n"), 0, MenuItem.size() - 1);
            } else {
                int choice = Integer.parseInt(input);
                item = MenuItem.get(choice - 1);
            }
        }
    }
}
