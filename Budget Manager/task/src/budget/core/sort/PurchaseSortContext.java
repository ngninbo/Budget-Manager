package budget.core.sort;

import budget.model.Purchase;

import java.util.List;

public class PurchaseSortContext {

    private final PurchaseSortStrategy sortStrategy;

    public PurchaseSortContext(PurchaseSortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public List<Purchase> sortAll() {
        return sortStrategy.sortAll();
    }

    public List<Purchase> sortByType(String type) {
        return sortStrategy.sortByCertainType(type);
    }

    public List<Purchase> sortByType() {
        return sortStrategy.sortByType();
    }
}
