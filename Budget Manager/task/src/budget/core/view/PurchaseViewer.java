package budget.core.view;

import budget.domain.PurchaseCollector;
import budget.utils.StringUtils;

import static budget.utils.BudgetManagerUtils.*;

public class PurchaseViewer implements PurchaseViewStrategy {

    private final PurchaseCollector purchaseCollector;

    public PurchaseViewer(PurchaseCollector purchaseCollector) {
        this.purchaseCollector = purchaseCollector;
    }

    @Override
    public void viewAll() {
        System.out.print("\n".concat(StringUtils.capitalize(ALL)).concat(":"));
        show(" ");
    }

    @Override
    public void viewAllByType(String type) {
        System.out.printf("%n%s:", type);
        show(" ");
        showTotalPrices(TOTAL.concat(" sum"));
    }

    @Override
    public void showTotalPrices(String label) {
        System.out.printf("%n%s: $%s\n", label, purchaseCollector.getTotalPrice());
    }

    @Override
    public void show(String delimiter) {
        if (purchaseCollector.isEmpty()) {
            System.out.println("\n".concat(String.join(" ", THE, PURCHASE, LIST_IS_EMPTY)));
            return;
        }
        System.out.print(purchaseCollector.format(delimiter));
    }

    @Override
    public PurchaseCollector getCollector() {
        return purchaseCollector;
    }
}
