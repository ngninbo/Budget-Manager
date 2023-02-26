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

import static budget.utils.BudgetManagerUtils.PURCHASE_TYPE_CHOICE_MESSAGE;
import static budget.utils.BudgetManagerUtils.choose;

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
            System.out.println("\nList is empty");
            return;
        }

        viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType())));
        viewerContext.show();
        System.out.println();
    }

    private void sortAll() {
        if (viewerContext.hasEmptyList()) {
            System.out.println("\nPurchase list is empty");
            return;
        }
        viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortAll())));
        viewerContext.viewAll();
        viewerContext.showTotalPrices("Total");
    }

    private void sortByCertainType() {
        final List<String> options = PurchaseType.toList();
        final int index = options.size() - 1;
        options.set(index, options.get(index).concat("\n"));
        String input = choose(options, PURCHASE_TYPE_CHOICE_MESSAGE);
        if (!input.matches("[1-4]")) {
            System.out.println("\nPlease enter a number between 1 and 4");
        } else {
            String type = PurchaseType.get(Integer.parseInt(input) - 1).capitalize();
            final List<Purchase> purchases = new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType(type);

            if (purchases.isEmpty()) {
                System.out.printf("%nThe purchase list is empty!\n");
                return;
            }

            viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(purchases)));
            viewerContext.viewAllByType(type);
        }
    }
}
