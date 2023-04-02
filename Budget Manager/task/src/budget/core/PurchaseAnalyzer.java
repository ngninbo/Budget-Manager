package budget.core;

import budget.core.view.Command;
import budget.core.view.PurchaseSortAllCommand;
import budget.core.view.PurchaseTypeSortCommand;
import budget.core.view.PurchaseViewerContext;
import budget.menu.PurchaseSortByCertainTypeViewer;
import budget.utils.SortOption;

public class PurchaseAnalyzer {

    private final PurchaseViewerContext viewerContext;

    public PurchaseAnalyzer(PurchaseViewerContext viewerContext) {
        this.viewerContext = viewerContext;
    }

    public void sort(SortOption option) {
        getSortStrategy(option).execute();
    }

    private Command getSortStrategy(SortOption option) {
        switch (option) {
            case SORT_BY_TYPE:
                return new PurchaseTypeSortCommand(viewerContext);
            case SORT_CERTAIN_TYPE:
                return new PurchaseSortByCertainTypeViewer(viewerContext);
            case SORT_ALL_PURCHASES:
                return new PurchaseSortAllCommand(viewerContext);
            default:
                throw new IllegalArgumentException("Invalid sort type provided");
        }
    }
}
