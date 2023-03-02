package budget.domain;

import budget.model.Clothes;
import budget.model.Entertainment;
import budget.model.Food;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.math.BigDecimal;

public class PurchaseFactory {

    /**
     * Create a purchase based on given type.
     * @param type {@link PurchaseType} - Type of purchase
     * @param name {@link String} - name of purchase
     * @param price {@link BigDecimal} - price of purchase
     * @return {@link Purchase} if given type is {@link PurchaseType#OTHER},
     * {@link Food} if given type {@link PurchaseType#FOOD} etc.
     */
    public static Purchase create(PurchaseType type, String name, BigDecimal price) {
        switch (type) {
            case FOOD:
                return new Food(name, price);
            case CLOTHES:
                return new Clothes(name, price);
            case ENTERTAINMENT:
                return new Entertainment(name, price);
            default:
                return new Purchase(name, price);
        }
    }
}
