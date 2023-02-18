package budget.core.sort;

import budget.core.PurchaseFilter;
import budget.model.Purchase;
import budget.utils.BudgetManagerUtils;
import budget.utils.PurchaseType;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseSorter implements PurchaseSortStrategy {

    private final List<Purchase> purchases;

    public PurchaseSorter(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public List<Purchase> sortAll() {
        new SortAlgorithm(purchases).perform();
        return purchases;
    }

    @Override
    public List<Purchase> sortByCertainType(String type) {
        List<Purchase> purchases = new PurchaseFilter(this.purchases).filterBy(type);
        new SortAlgorithm(purchases).perform();
        return purchases;
    }

    @Override
    public List<Purchase> sortByType() {
        return toMap().entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> new Purchase(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<String, BigDecimal> toMap() {
        Map<String, BigDecimal> map = new HashMap<>();

        for (PurchaseType purchaseType : PurchaseType.values()) {
            String type = BudgetManagerUtils.capitalize(purchaseType.name());
            List<Purchase> tmp = new PurchaseFilter(purchases).filterBy(type);
            BigDecimal sum = tmp.stream()
                    .map(Purchase::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            map.put(type, sum);
        }

        return map;
    }
}
