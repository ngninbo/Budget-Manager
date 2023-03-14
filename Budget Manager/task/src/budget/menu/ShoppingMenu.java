package budget.menu;

import budget.domain.ShoppingList;
import budget.utils.PurchaseType;

import java.util.Arrays;
import java.util.List;

public abstract class ShoppingMenu extends AbstractMenu {

    protected final ShoppingList shoppingList;

    public ShoppingMenu(ShoppingList shoppingList) {
        super(PurchaseType.toList());
        this.shoppingList = shoppingList;
    }

    public ShoppingMenu(ShoppingList shoppingList, List<String> options) {
        super(options);
        this.shoppingList = shoppingList;
    }

    public void start() {

        while (true) {
            if (processInput()) {
                return;
            }
        }
    }

    protected void addAdditionalOptions(String... additionalOptions) {
        Arrays.stream(additionalOptions).forEach(s -> options.add(String.format("\n%s) %s", options.size() + 1, s)));
        super.size = options.size();
    }
}
