package budget.core.view;

import budget.model.Purchase;

import java.util.List;

public interface PurchaseViewStrategy {

    void viewAll();
    void viewAllByType(String type);
    void showTotalPrices(String label);
    void show();

    List<Purchase> getItems();
}
