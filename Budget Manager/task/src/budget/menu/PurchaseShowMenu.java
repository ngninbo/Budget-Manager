package budget.menu;

import budget.core.PurchaseFilter;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.domain.PurchaseCollector;
import budget.domain.ShoppingList;
import budget.utils.PurchaseType;

import static budget.utils.BudgetManagerUtils.*;

public class PurchaseShowMenu extends ShoppingMenu {

    public PurchaseShowMenu(ShoppingList shoppingList) {
        super(shoppingList);
        addAdditionalOptions(ALL, BACK);
    }

    @Override
    protected String choiceOption() {
        return choose(PURCHASE_TYPE_CHOICE_MESSAGE.concat("s"));
    }

    @Override
    protected void process(String input) {
        int choice = Integer.parseInt(input);
        PurchaseViewerContext purchaseViewerContext = new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector()));

        if (choice <= PurchaseType.size()) {
            final String type = PurchaseType.get(choice - 1).capitalize();
            purchaseViewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(PurchaseFilter
                    .filter(shoppingList.getPurchaseCollector().getItems(), type))));
            purchaseViewerContext.viewAllByType(type);
        } else {
            purchaseViewerContext.viewAll();
            purchaseViewerContext.showTotalPrices(String.join(" ", TOTAL, "sum"));
        }
    }

    @Override
    public void start() {
        if (shoppingList.isEmpty()) {
            System.out.println("\n".concat(String.join(" ", THE, PURCHASE, LIST_IS_EMPTY.concat("!\n"))));
            return;
        }

        super.start();
    }
}
