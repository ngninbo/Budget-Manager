package budget.domain;

import budget.model.Clothes;
import budget.model.Entertainment;
import budget.model.Food;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static budget.utils.BudgetManagerUtils.requestInput;

public class PurchaseFactory {

    public static Purchase getPurchase(PurchaseType type) {

        String name = requestInput("\nEnter purchase name");
        BigDecimal price = new BigDecimal(requestInput("Enter its price")
                .replace("$", ""))
                .setScale(2, RoundingMode.HALF_UP);

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
