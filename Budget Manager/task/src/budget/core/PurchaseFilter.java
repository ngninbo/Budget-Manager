package budget.core;

import budget.model.Purchase;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseFilter {

    private final List<Purchase> purchases;

    public PurchaseFilter(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Purchase> filterBy(String type) {
        return purchases.stream()
                .filter(purchase -> isOther(type, purchase))
                .collect(Collectors.toList());
    }

    private boolean isOther(String type, Purchase purchase) {
        final String simpleName = purchase.getClass().getSimpleName();
        return "Other".equals(type) ? "Purchase".equals(simpleName) : type.equals(simpleName);
    }
}
