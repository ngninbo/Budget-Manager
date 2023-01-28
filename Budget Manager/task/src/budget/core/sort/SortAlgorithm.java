package budget.core.sort;

import budget.model.Purchase;

import java.util.List;

public class SortAlgorithm {

    private final List<Purchase> purchases;

    public SortAlgorithm(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void perform() {
        int n = purchases.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Purchase current = purchases.get(i);
                Purchase next = purchases.get(j);

                if (current.getPrice().compareTo(next.getPrice()) < 0) {
                    purchases.set(i, next);
                    purchases.set(j, current);
                }
            }
        }
    }
}
