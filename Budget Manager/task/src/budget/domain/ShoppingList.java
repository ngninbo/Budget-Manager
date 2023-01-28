package budget.domain;

import budget.core.*;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.model.Purchase;
import budget.utils.BudgetManagerUtils;
import budget.utils.PurchaseType;
import budget.utils.ShowOption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static budget.utils.BudgetManagerUtils.*;

public class ShoppingList implements ShoppingListAction, Serializable {

    private static final long serialVersionUID = 11234L;

    private final List<Purchase> purchases = new ArrayList<>();

    private BigDecimal budget = BigDecimal.ONE;

    @Override
    public void showPurchases() {
        if (purchases.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n%n");
            return;
        }

        while (true) {
            try {
                int choice = choiceTypeOfPurchases();

                ShowOption option = ShowOption.getOption(choice - 1);

                PurchaseViewer viewer = new PurchaseViewer(new PurchaseViewerContext(purchases));

                switch (option) {
                    case BACK:
                        System.out.println();
                        return;
                    case ALL:
                        viewer.viewAll();
                        viewer.showTotalPrices("Total sum");
                        break;
                    default:
                        final String type = BudgetManagerUtils.capitalize(PurchaseType.getPurchaseType(option.ordinal()).name());
                        viewer.setViewStrategy(new PurchaseViewerContext(new PurchaseFilter(purchases).filterBy(type)));
                        viewer.viewAllByType(type);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unknown purchase type");
            }
        }
    }

    @Override
    public void addPurchase() {

        while (true) {
            try {
                int choice = choiceTypeOfPurchase();
                final int back = 5;
                if (choice == back) {
                    System.out.println();
                    return;
                }

                final PurchaseType purchaseType = PurchaseType.getPurchaseType(choice - 1);
                String name = requestInput("\nEnter purchase name");
                BigDecimal price = new BigDecimal(requestInput("Enter its price")
                        .replace("$", ""))
                        .setScale(2, RoundingMode.HALF_UP);

                Purchase purchase = PurchaseBuilder.init()
                        .withType(purchaseType)
                        .withName(name).withPrice(price).build();

                purchases.add(purchase);
                budget = budget.subtract(purchase.getPrice()).setScale(2, RoundingMode.HALF_UP);
                System.out.println("Purchase was added!");
            } catch (Exception e) {
                System.out.println("Unknown purchase type");
            }
        }
    }

    @Override
    public void analyse() {

        while (true) {
            try {
                int choice = choiceSort();

                if (choice == 4) {
                    System.out.printf("%n");
                    return;
                }

                new PurchaseAnalyzer(new PurchaseViewer(new PurchaseViewerContext(purchases))).withSort(choice - 1).analyse();

            } catch (Exception e) {
                System.out.println("Unknown sort strategy");
            }
        }
    }

    @Override
    public void showBalance() {
        System.out.printf("%nBalance: $%s%n\n", budget);
    }

    @Override
    public void addIncome() {
        String input = requestInput("\nEnter income");

        if (!input.matches("\\d+")) {
            System.out.println();
            return;
        }

        budget = budget.add(new BigDecimal(input).setScale(2, RoundingMode.HALF_UP));

        System.out.println("Income was added!\n");
    }
}
