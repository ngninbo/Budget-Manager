package budget.core.view;

public abstract class Command {

    protected abstract boolean hasEmptyList();
    protected abstract void sortAndViewItems();
    protected abstract void printListIsEmpty();

    public void execute() {
        if (hasEmptyList()) {
            printListIsEmpty();
            return;
        }

        sortAndViewItems();
    }
}
