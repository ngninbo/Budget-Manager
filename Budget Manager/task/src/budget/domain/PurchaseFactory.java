package budget.domain;

import budget.model.Clothes;
import budget.model.Entertainment;
import budget.model.Food;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.math.BigDecimal;

public class PurchaseFactory {

    public static Purchase getPurchase(PurchaseType type, String name, BigDecimal price) {
        switch (type) {
            case FOOD:
                return new Food(name, price);
            case CLOTHES:
                return new Clothes(name, price);
            case ENTERTAINMENT:
                return new Entertainment(name, price);
            case OTHER:
            default:
                return new Purchase(name, price);
        }
    }
}
