package budget.core.sort;

import budget.model.Purchase;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortUtils {

    public static void sort(List<Purchase> purchases) {
        bubbleSort(purchases);
    }

    public static List<Purchase> sort(Map<String, BigDecimal> map) {
        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> new Purchase(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static void bubbleSort(List<Purchase> purchases) {

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
