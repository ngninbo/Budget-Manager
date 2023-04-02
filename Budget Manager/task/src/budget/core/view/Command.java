package budget.core.view;

public abstract class Command {

    public void execute() {
        if (hasEmptyList()) {
            printListIsEmpty();
            return;
        }

        sortAndViewItems();
    }

    protected boolean hasEmptyList() {
        return false;
    }

    protected void sortAndViewItems() {
        System.out.println("I should be implemented to sort and view items!");
    }

    protected void printListIsEmpty() {
        System.out.println("I should be implemented to print if a list is empty!");
    }
}
