package budget.core.view;

import budget.model.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PurchaseViewerContext implements PurchaseViewStrategy {

    private final List<Purchase> purchases;

    public PurchaseViewerContext(List<Purchase> purchases) {
        this.purchases = purchases;
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
        System.out.printf("%n%s: $%s\n", label, getTotalPrice());
    }

    @Override
    public void show() {
        if (purchases.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n");
            return;
        }
        purchases.forEach(purchase -> System.out.printf("%n%s $%s", purchase.getName(), purchase.getPrice()));
    }

    @Override
    public List<Purchase> getItems() {
        return purchases;
    }

    public BigDecimal getTotalPrice() {
        return purchases.stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
