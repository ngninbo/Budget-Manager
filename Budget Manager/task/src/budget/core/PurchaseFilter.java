package budget.core;

import budget.model.Purchase;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PurchaseFilter {

    private final List<Purchase> purchases;

    public PurchaseFilter(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Purchase> filterBy(String type) {
        return purchases.stream()
                .filter(purchase -> filter().apply(type, purchase.getClass().getSimpleName()))
                .collect(Collectors.toList());
    }

    private BiFunction<String, String, Boolean> filter() {
        return (type, simpleName) -> "Other".equals(type) ? "Purchase".equals(simpleName) : type.equals(simpleName);
    }
}
