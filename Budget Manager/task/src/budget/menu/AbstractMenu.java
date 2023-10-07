package budget.menu;

import budget.core.view.Command;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

import static budget.utils.BudgetManagerUtils.VALID_NUMBER_INPUT_REQUIRED_TEXT;
import static budget.utils.StringUtils.createRegex;
import static budget.utils.StringUtils.matches;

public abstract class AbstractMenu extends Command {

    protected abstract String choiceOption();
    protected abstract void process(String input);

    protected List<String> options;
    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PROTECTED)
    private int min;

    public AbstractMenu(List<String> options) {
        this.options = options;
    }

    protected boolean processInput() {
        return processInput(choiceOption());
    }

    protected boolean processInput(String input) {
        if (wrong(input)) {
            printErrorMessage();
        } else if (String.valueOf(getNumberOfOptions()).equals(input)) {
            System.out.println();
            return true;
        } else {
            process(input);
        }
        return false;
    }

    private int getNumberOfOptions() {
        return options.size();
    }

    /**
     * Display options inclusive additional ones and request user to choose one of them
     * @param message {@link String} Message to be displayed
     * @return {@link String} Index of option choose by the user
     */
    protected String choose(String message) {
        displayMenu(options, message);
        return new Scanner(System.in).nextLine();
    }

    private void displayMenu(List<String> items, String message) {
        StringBuilder sb = new StringBuilder(message);

        items.forEach(sb::append);

        System.out.printf("%s", sb);
    }

    protected boolean wrong(String input) {
        return !matches(input, createRegex(min, getNumberOfOptions()));
    }

    protected void printErrorMessage() {
        System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT, min, getNumberOfOptions());
    }
}
