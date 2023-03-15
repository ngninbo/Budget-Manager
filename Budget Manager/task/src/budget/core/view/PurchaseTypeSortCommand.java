package budget.core.view;

import budget.core.sort.PurchaseSortContext;
import budget.core.sort.PurchaseSorter;
import budget.domain.PurchaseCollector;
import budget.utils.StringUtils;

import static budget.utils.BudgetManagerUtils.LIST_IS_EMPTY;

public class PurchaseTypeSortCommand extends Command {

    private final PurchaseViewerContext viewerContext;

    public PurchaseTypeSortCommand(PurchaseViewerContext viewerContext) {
        this.viewerContext = viewerContext;
    }

    @Override
    public boolean hasEmptyList() {
        return viewerContext.hasEmptyList();
    }

    @Override
    public void sortAndViewItems() {
        viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType())));
        viewerContext.show();
        System.out.println();
    }

    @Override
    protected void printListIsEmpty() {
        System.out.println("\n".concat(StringUtils.capitalize(LIST_IS_EMPTY)));
    }
}
