package budget.core.sort;

import budget.model.Purchase;
import java.util.List;

public interface PurchaseSortStrategy {

    List<Purchase> sortAll();
    List<Purchase> sortByCertainType(String type);
    List<Purchase> sortByType();
}
