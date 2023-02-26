package budget.domain;

import budget.core.PurchaseFilter;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, BigDecimal> map = new HashMap<>();

        for (PurchaseType purchaseType : PurchaseType.values()) {
            String type = purchaseType.capitalize();
            List<Purchase> tmp = PurchaseFilter.filter(purchases, type);
            BigDecimal sum = tmp.stream()
                    .map(Purchase::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            map.put(type, sum);
        }

        return map;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        purchases.forEach(purchase -> sb.append(String.format("%n%s $%s", purchase.getName(), purchase.getPrice())));
        return sb.toString();
    }
}
