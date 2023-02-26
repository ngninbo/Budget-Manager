package budget.core.view;


import budget.domain.PurchaseCollector;

public class PurchaseViewerContext {

    private PurchaseViewStrategy viewStrategy;

    public PurchaseViewerContext(PurchaseViewStrategy viewStrategy) {
        this.viewStrategy = viewStrategy;
    }

    public void setViewStrategy(PurchaseViewStrategy viewStrategy) {
        this.viewStrategy = viewStrategy;
    }

    public void viewAll() {
        viewStrategy.viewAll();
    }

    public void viewAllByType(String type) {
        viewStrategy.viewAllByType(type);
    }

    public void showTotalPrices(String label) {
        viewStrategy.showTotalPrices(label);
    }

    public void show() {
        viewStrategy.show(" - ");
    }

    public boolean hasEmptyList() {
        return getCollector().isEmpty();
    }

    public PurchaseCollector getCollector() {
        return viewStrategy.getCollector();
    }
}
