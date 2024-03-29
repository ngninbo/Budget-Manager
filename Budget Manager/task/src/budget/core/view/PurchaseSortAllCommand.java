package budget.core.view;

import budget.core.sort.PurchaseSortContext;
import budget.core.sort.PurchaseSorter;
import budget.domain.PurchaseCollector;
import budget.utils.StringUtils;
import lombok.AllArgsConstructor;

import static budget.utils.BudgetManagerUtils.*;

@AllArgsConstructor
public class PurchaseSortAllCommand extends Command {

    private final PurchaseViewerContext viewerContext;

    @Override
    public boolean hasEmptyList() {
        return viewerContext.hasEmptyList();
    }

    @Override
    public void sortAndViewItems() {
        viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortAll())));
        viewerContext.viewAll();
        viewerContext.showTotalPrices(TOTAL);
    }

    @Override
    protected void printListIsEmpty() {
        System.out.println("\n".concat(StringUtils.capitalize(PURCHASE.concat(" ".concat(LIST_IS_EMPTY)))));
    }
}
