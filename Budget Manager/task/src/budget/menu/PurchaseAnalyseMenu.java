package budget.menu;

import budget.core.PurchaseAnalyzer;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.domain.ShoppingList;
import budget.utils.SortOption;

import static budget.utils.BudgetManagerUtils.BACK;

public class PurchaseAnalyseMenu extends ShoppingMenu {

    public PurchaseAnalyseMenu(ShoppingList shoppingList) {
        super(shoppingList, SortOption.toList());
        addAdditionalOptions(BACK);
    }

    @Override
    protected String choiceOption() {
        return choose("\nHow do you want to sort?");
    }

    @Override
    protected void process(String input) {
        new PurchaseAnalyzer(new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector())))
                .sort(SortOption.get(Integer.parseInt(input) - 1));
    }
}
