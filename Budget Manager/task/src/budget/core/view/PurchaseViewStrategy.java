package budget.core.view;

import budget.domain.PurchaseCollector;

public interface PurchaseViewStrategy {

    void viewAll();
    void viewAllByType(String type);
    void showTotalPrices(String label);
    void show();

    PurchaseCollector getCollector();
}
