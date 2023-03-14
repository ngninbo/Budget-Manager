package budget.core;

import budget.core.sort.PurchaseSorter;
import budget.core.sort.PurchaseSortContext;
import budget.core.view.PurchaseViewerContext;
import budget.core.view.PurchaseViewer;
import budget.domain.PurchaseCollector;
import budget.menu.PurchaseView;
import budget.utils.SortOption;
import budget.utils.StringUtils;

import static budget.utils.BudgetManagerUtils.*;

public class PurchaseAnalyzer {

    private final PurchaseViewerContext viewerContext;

    public PurchaseAnalyzer(PurchaseViewerContext viewerContext) {
        this.viewerContext = viewerContext;
    }

    public void sort(SortOption option) {
        switch (option) {
            case SORT_BY_TYPE:
                sortByType();
                break;
            case SORT_CERTAIN_TYPE:
                sortByCertainType();
                break;
            case SORT_ALL_PURCHASES:
                sortAll();
                break;
            default:
                throw new IllegalArgumentException("Invalid sort type provided");
        }
    }

    private void sortByType() {
        if (viewerContext.hasEmptyList()) {
            System.out.println("\n".concat(StringUtils.capitalize(LIST_IS_EMPTY)));
            return;
        }

        viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType())));
        viewerContext.show();
        System.out.println();
    }

    private void sortAll() {
        if (viewerContext.hasEmptyList()) {
            System.out.println("\n".concat(StringUtils.capitalize(PURCHASE.concat(" ".concat(LIST_IS_EMPTY)))));
            return;
        }
        viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortAll())));
        viewerContext.viewAll();
        viewerContext.showTotalPrices(TOTAL);
    }

    private void sortByCertainType() {
        new PurchaseView(viewerContext).processInput();
    }
}
