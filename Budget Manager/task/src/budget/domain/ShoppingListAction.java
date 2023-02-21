package budget.domain;

import budget.model.Purchase;

import java.math.BigDecimal;

public interface ShoppingListAction {

    void addIncome(BigDecimal income);

    void addPurchase(Purchase purchase);
}
