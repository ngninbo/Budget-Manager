package budget.menu;

import budget.core.sort.PurchaseSortContext;
import budget.core.sort.PurchaseSorter;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.core.view.Command;
import budget.domain.PurchaseCollector;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.util.List;

import static budget.utils.BudgetManagerUtils.*;
import static budget.utils.BudgetManagerUtils.LIST_IS_EMPTY;

public class PurchaseSortByCertainTypeViewer extends AbstractMenu {

    private final PurchaseViewerContext viewerContext;

    public PurchaseSortByCertainTypeViewer(PurchaseViewerContext viewerContext) {
        super(PurchaseType.toList());
        this.viewerContext = viewerContext;
    }

    @Override
    protected String choiceOption() {
        final int index = options.size() - 1;
        options.set(index, options.get(index).concat("\n"));
        return choose(PURCHASE_TYPE_CHOICE_MESSAGE);
    }

    @Override
    protected void process(String input) {
        String type = PurchaseType.get(Integer.parseInt(input) - 1).capitalize();
        getViewerCommand(type).execute();
    }

    @Override
    public boolean processInput() {
        String input = choiceOption();
        if (wrong(input)) {
            printErrorMessage();
        } else {
            process(input);
        }

        return true;
    }

    public void execute() {
        processInput();
    }

    private Command getViewerCommand(String type) {
        final List<Purchase> purchases = new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType(type);

        return new Command() {
            @Override
            public boolean hasEmptyList() {
                return purchases.isEmpty();
            }

            @Override
            public void sortAndViewItems() {
                viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(purchases)));
                viewerContext.viewAllByType(type);
            }

            @Override
            protected void printListIsEmpty() {
                System.out.println("\n".concat(String.join(" ", THE, PURCHASE, LIST_IS_EMPTY).concat("!")));
            }
        };
    }
}
