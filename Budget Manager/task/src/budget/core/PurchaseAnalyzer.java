package budget.core;

import budget.core.sort.PurchaseSorter;
import budget.core.sort.PurchaseSortContext;
import budget.core.view.PurchaseViewerContext;
import budget.core.view.PurchaseViewer;
import budget.domain.PurchaseCollector;
import budget.model.Purchase;
import budget.utils.PurchaseType;
import budget.utils.SortOption;

import java.util.List;

import static budget.utils.BudgetManagerUtils.choicePurchaseType;

public class PurchaseAnalyzer {

    private SortOption option;

    private final PurchaseViewerContext viewerContext;

    public PurchaseAnalyzer(PurchaseViewerContext viewerContext) {
        this.viewerContext = viewerContext;
    }

    public PurchaseAnalyzer withSort(int choice) {
        this.option = SortOption.get(choice);
        return this;
    }

    public void analyse() {

        switch (option) {
            case SORT_BY_TYPE:
                if (viewerContext.hasEmptyList()) {
                    System.out.println("\nList is empty");
                    return;
                }

                viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType())));
                viewerContext.show();
                System.out.println();
                break;
            case SORT_CERTAIN_TYPE:

                int choice = choicePurchaseType();

                String type = PurchaseType.get(choice - 1).capitalize();
                final List<Purchase> purchases = new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType(type);

                if (purchases.isEmpty()) {
                    System.out.printf("%nThe purchase list is empty!\n");
                    return;
                }

                viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(purchases)));
                viewerContext.viewAllByType(type);
                break;
            case SORT_ALL_PURCHASES:
                if (viewerContext.hasEmptyList()) {
                    System.out.println("\nPurchase list is empty");
                    return;
                }
                viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortAll())));
                viewerContext.viewAll();
                viewerContext.showTotalPrices("Total");
                break;
            default:
        }
    }
}
