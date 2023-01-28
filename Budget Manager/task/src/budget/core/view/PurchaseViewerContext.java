package budget.core.view;

import budget.model.Purchase;

import java.util.List;

public class PurchaseViewerContext implements PurchaseViewStrategy {

    private List<Purchase> purchases;

    public PurchaseViewerContext(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
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
        } catch (Exception e) {
            System.out.println("Unknown purchase type");
        }
    }


    public void show() {
        purchases.forEach(purchase -> System.out.printf("%n%s $%s", purchase.getName(), purchase.getPrice()));
    }
}
