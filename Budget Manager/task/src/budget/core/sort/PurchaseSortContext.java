package budget.core.sort;

import budget.model.Purchase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PurchaseSortContext {

    private final PurchaseSortStrategy sortStrategy;

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
