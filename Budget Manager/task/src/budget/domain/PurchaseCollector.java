package budget.domain;

import budget.core.PurchaseFilter;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseCollector implements Serializable {

    private static final long serialVersionUID = 112454L;

    private List<Purchase> purchases = new ArrayList<>();

    public PurchaseCollector() {
    }

    public PurchaseCollector(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void add(Purchase purchase) {
        purchases.add(purchase);
    }

    public List<Purchase> getItems() {
        return purchases;
    }

    /**
     * Find which category eats the most money.
     * @return {@link Map} category-amount pair - sorted by category.
     */
    public Map<String, BigDecimal> sum() {
        return Arrays.stream(PurchaseType.values())
                .map(PurchaseType::capitalize)
                .collect(Collectors.toMap(type -> type, this::sumOfPurchases, (a, b) -> b));
    }

    public BigDecimal getTotalPrice() {
        return purchases.stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public boolean isEmpty() {
        return purchases.isEmpty();
    }

    public String format(String delimiter) {
        StringBuilder sb = new StringBuilder();
        purchases.forEach(purchase -> sb.append(String.format("%n%s%s$%s", purchase.getName(), delimiter, purchase.getPrice())));
        return sb.toString();
    }

    private BigDecimal sumOfPurchases(String type) {
        return PurchaseFilter.filter(purchases, type)
                .stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
