package budget.core;

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
        switch (option) {
            case SORT_BY_TYPE:
                new PurchaseTypeSortCommand(viewerContext).execute();
                break;
            case SORT_CERTAIN_TYPE:
                new PurchaseSortByCertainTypeViewer(viewerContext).execute();
                break;
            case SORT_ALL_PURCHASES:
                new PurchaseSortAllCommand(viewerContext).execute();
                break;
            default:
                throw new IllegalArgumentException("Invalid sort type provided");
        }
    }
}
