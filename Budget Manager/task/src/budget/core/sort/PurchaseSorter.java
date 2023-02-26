package budget.core.sort;

import budget.core.PurchaseFilter;
import budget.domain.PurchaseCollector;
import budget.model.Purchase;

import java.util.*;

public class PurchaseSorter implements PurchaseSortStrategy {

    private final PurchaseCollector purchaseCollector;

    public PurchaseSorter(PurchaseCollector purchaseCollector) {
        this.purchaseCollector = purchaseCollector;
    }

    @Override
    public List<Purchase> sortAll() {
        List<Purchase> purchases = purchaseCollector.getItems();
        SortUtils.sort(purchases);
        return purchases;
    }

    @Override
    public List<Purchase> sortByCertainType(String type) {
        List<Purchase> purchases = PurchaseFilter.filter(purchaseCollector.getItems(), type);
        SortUtils.sort(purchases);
        return purchases;
    }

    @Override
    public List<Purchase> sortByType() {
        return SortUtils.sort(purchaseCollector.sum());
    }
}
