package budget.core.view;

import budget.domain.PurchaseCollector;
import budget.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static budget.utils.BudgetManagerUtils.*;

@AllArgsConstructor
public class PurchaseViewer implements PurchaseViewStrategy {

    @Getter
    private final PurchaseCollector purchaseCollector;

    @Override
    public void viewAll() {
        System.out.print("\n".concat(StringUtils.capitalize(ALL)).concat(":"));
        show(" ");
    }

    @Override
    public void viewAllByType(String type) {
        System.out.printf("%n%s:", type);
        show(" ");
        showTotalPrices(String.join(" ", TOTAL, "sum"));
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
}
