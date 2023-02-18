package budget.domain;

import budget.core.*;
import budget.core.view.PurchaseViewerContext;
import budget.core.view.PurchaseViewer;
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

    private BigDecimal budget = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public void showPurchases() {
        if (purchases.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n%n");
            return;
        }

        while (true) {
            try {
                int choice = choiceTypeOfPurchases();

                ShowOption option = ShowOption.getOption(choice - 1);

                PurchaseViewerContext purchaseViewerContext = new PurchaseViewerContext(new PurchaseViewer(purchases));

                switch (option) {
                    case BACK:
                        System.out.println();
                        return;
                    case ALL:
                        purchaseViewerContext.viewAll();
                        purchaseViewerContext.showTotalPrices("Total sum");
                        break;
                    default:
                        final String type = BudgetManagerUtils.capitalize(PurchaseType.getPurchaseType(option.ordinal()).name());
                        purchaseViewerContext.setViewStrategy(new PurchaseViewer(new PurchaseFilter(purchases).filterBy(type)));
                        purchaseViewerContext.viewAllByType(type);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unknown purchase type");
            }
        }
    }

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

    public void analyse() {

        while (true) {
            try {
                int choice = choiceSort();

                if (choice == 4) {
                    System.out.printf("%n");
                    return;
                }

                new PurchaseAnalyzer(new PurchaseViewerContext(new PurchaseViewer(purchases))).withSort(choice - 1).analyse();

            } catch (Exception e) {
                System.out.println("\nUnknown sort strategy");
            }
        }
    }

    public void showBalance() {
        System.out.printf("%nBalance: $%s%n\n", budget);
    }

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
