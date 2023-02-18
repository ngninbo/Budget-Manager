package budget.core;

import budget.core.sort.PurchaseSorter;
import budget.core.sort.PurchaseSortContext;
import budget.core.view.PurchaseViewerContext;
import budget.core.view.PurchaseViewer;
import budget.model.Purchase;
import budget.utils.BudgetManagerUtils;
import budget.utils.PurchaseType;
import budget.utils.SortOption;

import java.util.List;

import static budget.utils.BudgetManagerUtils.choicePurchaseType;

public class PurchaseAnalyzer {

    private final List<Purchase> purchases;
    private SortOption option;

    private final PurchaseViewerContext viewerContext;

    public PurchaseAnalyzer(PurchaseViewerContext viewerContext) {
        this.viewerContext = viewerContext;
        this.purchases = viewerContext.getPurchases();
    }

    public PurchaseAnalyzer withSort(int choice) {
        this.option = SortOption.getOptionBy(choice);
        return this;
    }

    public void analyse() {

        switch (option) {
            case SORT_BY_TYPE:
                if (purchases.isEmpty()) {
                    System.out.println("\nList is empty");
                    return;
                }

                viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseSortContext(new PurchaseSorter(purchases)).sortByType()));
                viewerContext.show();
                System.out.println();
                break;
            case SORT_CERTAIN_TYPE:

                int choice = choicePurchaseType();

                String type = BudgetManagerUtils.capitalize(PurchaseType.getPurchaseType(choice - 1).name());
                final List<Purchase> purchases = new PurchaseSortContext(new PurchaseSorter(this.purchases)).sortByType(type);

                if (purchases.isEmpty()) {
                    System.out.printf("%nThe purchase list is empty!\n");
                    return;
                }

                viewerContext.setViewStrategy(new PurchaseViewer(purchases));
                viewerContext.viewAllByType(type);
                break;
            case SORT_ALL_PURCHASES:
                if (this.purchases.isEmpty()) {
                    System.out.println("\nPurchase list is empty");
                    return;
                }
                viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseSortContext(new PurchaseSorter(this.purchases)).sortAll()));
                viewerContext.viewAll();
                viewerContext.showTotalPrices("Total");
                break;
            default:
        }
    }
}
