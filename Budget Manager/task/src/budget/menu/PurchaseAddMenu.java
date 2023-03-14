package budget.menu;

import budget.domain.PurchaseFactory;
import budget.domain.ShoppingList;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.math.BigDecimal;

import static budget.utils.BudgetManagerUtils.*;

public class PurchaseAddMenu extends ShoppingMenu {

    public PurchaseAddMenu(ShoppingList shoppingList) {
        super(shoppingList);
        addAdditionalOptions(BACK);
    }

    @Override
    protected String choiceOption() {
        return choose(PURCHASE_TYPE_CHOICE_MESSAGE);
    }

    @Override
    protected void process(String input) {
        int choice = Integer.parseInt(input);
        final PurchaseType purchaseType = PurchaseType.get(choice - 1);
        addPurchase(purchaseType);
    }

    private void addPurchase(PurchaseType type) {
        String name = enterPurchaseName();
        BigDecimal price = toBigDecimal(enterPrice());
        Purchase purchase = PurchaseFactory.create(type, name, price);

        shoppingList.addPurchase(purchase);
        System.out.println("Purchase was added!");
    }

    private String enterPurchaseName() {
        return enter(ENTER_PURCHASE_NAME);
    }

    private String enterPrice() {
        return enter(ENTER_PRICE);
    }
}
