package budget.domain;

import budget.model.Purchase;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class ShoppingList implements ShoppingListAction, Serializable {

    private static final long serialVersionUID = 11234L;

    private final PurchaseCollector purchaseCollector = new PurchaseCollector();

    private BigDecimal budget = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Override
    public void addIncome(BigDecimal income) {
        budget = budget.add(income);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseCollector.add(purchase);
        addIncome(purchase.getPrice().negate());
    }

    public boolean isEmpty() {
        return purchaseCollector.isEmpty();
    }
}
