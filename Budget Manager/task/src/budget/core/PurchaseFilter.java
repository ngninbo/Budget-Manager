package budget.core;

import budget.model.Purchase;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PurchaseFilter {

    /**
     * Filter given list of purchase by given type
     * @param purchases {@link List} List of purchases
     * @param type {@link String} Type of purchase to filter for.
     * @return {@link List} List of purchase of given type
     */
    public static List<Purchase> filter(List<Purchase> purchases, String type) {
        return purchases.stream()
                .filter(purchase -> filter().apply(type, purchase.getClass().getSimpleName()))
                .collect(Collectors.toList());
    }

    private static BiFunction<String, String, Boolean> filter() {
        return (type, simpleName) -> "Other".equals(type) ? "Purchase".equals(simpleName) : type.equals(simpleName);
    }
}
