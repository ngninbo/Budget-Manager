package budget.menu;

import budget.utils.MenuItem;

import static budget.utils.BudgetManagerUtils.*;

public class StartMenu extends ShoppingMenu {

    private final Menu menu;
    private MenuItem item = MenuItem.UNKNOWN;

    public StartMenu(Menu menu) {
        super(menu.getShoppingList(), MenuItem.toList());
        this.menu = menu;
        setMin(0);
    }

    @Override
    protected String choiceOption() {
        return choose(ACTION_CHOICE_MESSSAGE.concat(":"));
    }

    @Override
    protected void process(String input) {
        int choice = Integer.parseInt(input);
        item = MenuItem.get(choice - 1);
    }

    @Override
    public void start() {
        while (menu.process(item)) {

            String input = choiceOption();
            if (wrong(input)) {
                printErrorMessage();
                item = MenuItem.UNKNOWN;
            } else {
                process(input);
            }
        }
    }

    @Override
    protected void printErrorMessage() {
        System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT.concat("%n"), getMin(), options.size() - 1);
    }
}
