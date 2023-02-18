package budget.core.view;

import budget.domain.PurchaseCollector;
import budget.model.Purchase;

import java.util.List;

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
        try {
            System.out.printf("%n%s:", type);

            show();
            showTotalPrices("Total sum");
        } catch (Exception e) {
            System.out.println("Unknown purchase type");
        }
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
        purchaseCollector.show();
    }

    @Override
    public List<Purchase> getItems() {
        return purchaseCollector.getItems();
    }

    @Override
    public PurchaseCollector getCollector() {
        return purchaseCollector;
    }
}
