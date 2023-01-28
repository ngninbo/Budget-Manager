package budget.core.sort;

import budget.core.PurchaseFilter;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseSortContext implements PurchaseSortStrategy {

    private final List<Purchase> purchases;

    public PurchaseSortContext(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public List<Purchase> sortAll() {
        new SortAlgorithm(purchases).perform();
        return purchases;
    }

    @Override
    public List<Purchase> sortByCertainType(String type) {
        new SortAlgorithm(purchases).perform();
        return purchases;
    }

    @Override
    public List<Purchase> sortByType() {

        Map<String, BigDecimal> map = new HashMap<>();

        for (PurchaseType type : PurchaseType.values()) {
            List<Purchase> tmp = new PurchaseFilter(purchases).filterBy(type.capitalize());
            BigDecimal sum = tmp.stream()
                    .map(Purchase::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            map.put(type.capitalize(), sum);
        }

        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> new Purchase(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
