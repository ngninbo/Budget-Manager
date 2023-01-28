package budget.core;

import budget.model.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public abstract class Viewer {

    public BigDecimal getTotalPrice(List<Purchase> purchases) {
        return purchases.stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
