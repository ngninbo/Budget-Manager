package budget.menu;

import budget.core.sort.PurchaseSortContext;
import budget.core.sort.PurchaseSorter;
import budget.core.view.PurchaseViewerContext;
import budget.domain.PurchaseCollector;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.util.List;

import static budget.utils.BudgetManagerUtils.*;
import static budget.utils.BudgetManagerUtils.LIST_IS_EMPTY;

public class PurchaseView extends AbstractMenu {

    private final PurchaseViewerContext viewerContext;

    public PurchaseView(PurchaseViewerContext viewerContext) {
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
        final List<Purchase> purchases = new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType(type);

        if (purchases.isEmpty()) {
            System.out.println("\n".concat(String.join(" ", THE, PURCHASE, LIST_IS_EMPTY).concat("!")));
            return;
        }

        viewerContext.setViewStrategy(new budget.core.view.PurchaseViewer(new PurchaseCollector(purchases)));
        viewerContext.viewAllByType(type);
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
}
