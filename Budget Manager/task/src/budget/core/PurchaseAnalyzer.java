package budget.core;

import budget.core.sort.PurchaseSorter;
import budget.core.sort.PurchaseSortContext;
import budget.core.view.PurchaseViewerContext;
import budget.core.view.PurchaseViewer;
import budget.domain.PurchaseCollector;
import budget.model.Purchase;
import budget.utils.PurchaseType;
import budget.utils.SortOption;
import budget.utils.StringUtils;

import java.util.List;

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
        final List<String> options = PurchaseType.toList();
        final int index = options.size() - 1;
        options.set(index, options.get(index).concat("\n"));
        String input = choose(options, PURCHASE_TYPE_CHOICE_MESSAGE);
        if (!input.matches("[1-4]")) {
            System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT.concat("\n"), 1, PurchaseType.size());
        } else {
            String type = PurchaseType.get(Integer.parseInt(input) - 1).capitalize();
            final List<Purchase> purchases = new PurchaseSortContext(new PurchaseSorter(viewerContext.getCollector())).sortByType(type);

            if (purchases.isEmpty()) {
                System.out.println("\n".concat(String.join(" ", THE, PURCHASE, LIST_IS_EMPTY).concat("!")));
                return;
            }

            viewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(purchases)));
            viewerContext.viewAllByType(type);
        }
    }
}
