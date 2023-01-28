package budget.core.view;

import budget.model.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PurchaseViewer {

    private List<Purchase> purchases;

    private final PurchaseViewStrategy viewStrategy;

    public PurchaseViewer(List<Purchase> purchases) {
        this.purchases = purchases;
        this.viewStrategy = new PurchaseViewerContext(purchases);
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void viewAll() {
        viewStrategy.viewAll();
    }

    public void viewAllByType(String type) {
        if (purchases.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n");
            return;
        }
        viewStrategy.viewAllByType(type);
        showTotalPrices("Total sum");
    }

    public void showTotalPrices(String label) {
        System.out.printf("%n%s: $%s\n", label, getTotalPrice(purchases));
    }

    public BigDecimal getTotalPrice(List<Purchase> purchases) {
        return purchases.stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
