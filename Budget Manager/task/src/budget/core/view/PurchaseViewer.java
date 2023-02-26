package budget.core.view;

import budget.domain.PurchaseCollector;

public class PurchaseViewer implements PurchaseViewStrategy {

    private final PurchaseCollector purchaseCollector;

    public PurchaseViewer(PurchaseCollector purchaseCollector) {
        this.purchaseCollector = purchaseCollector;
    }

    @Override
    public void viewAll() {
        System.out.print("\nAll:");
        show();
    }

    @Override
    public void viewAllByType(String type) {
        System.out.printf("%n%s:", type);
        show();
        showTotalPrices("Total sum");
    }

    @Override
    public void showTotalPrices(String label) {
        System.out.printf("%n%s: $%s\n", label, purchaseCollector.getTotalPrice());
    }

    @Override
    public void show() {
        if (purchaseCollector.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n");
            return;
        }
        System.out.print(purchaseCollector);
    }

    @Override
    public PurchaseCollector getCollector() {
        return purchaseCollector;
    }
}
