package budget.core;

import budget.core.sort.PurchaseSortContext;
import budget.core.sort.PurchaseSorter;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.model.Purchase;
import budget.utils.PurchaseType;
import budget.utils.SortOption;

import java.util.List;

import static budget.utils.BudgetManagerUtils.choicePurchaseType;

public class PurchaseAnalyzer {

    private final List<Purchase> purchases;
    private SortOption option;

    public PurchaseAnalyzer(List<Purchase> purchases) {
        this.purchases = purchases;
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

                new PurchaseViewerContext(new PurchaseSorter(new PurchaseSortContext(purchases)).sortByType()).show();
                System.out.println();
                break;
            case SORT_CERTAIN_TYPE:

                int choice = choicePurchaseType();

                String type = PurchaseType.getPurchaseType(choice - 1).capitalize();
                final List<Purchase> purchases = new PurchaseFilter(this.purchases).filterBy(type);

                if (purchases.isEmpty()) {
                    System.out.println("\nPurchase list is empty");
                    return;
                }

                PurchaseViewer viewer = new PurchaseViewer(new PurchaseSorter(new PurchaseSortContext(purchases)).sortByType(type));
                viewer.viewAllByType(type);
                break;
            case SORT_ALL_PURCHASES:
                if (this.purchases.isEmpty()) {
                    System.out.println("\nPurchase list is empty");
                    return;
                }
                viewer = new PurchaseViewer(new PurchaseSorter(new PurchaseSortContext(this.purchases)).sortAll());
                viewer.viewAll();
                viewer.showTotalPrices("Total");
                break;
            default:
        }
    }
}
