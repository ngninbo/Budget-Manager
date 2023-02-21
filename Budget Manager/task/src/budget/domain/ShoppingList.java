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

import static budget.utils.BudgetManagerUtils.*;

public class ShoppingList implements ShoppingListAction, Serializable {

    private static final long serialVersionUID = 11234L;

    private final PurchaseCollector purchaseCollector = new PurchaseCollector();

    private BigDecimal budget = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public void showPurchases() {
        if (purchaseCollector.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n%n");
            return;
        }

        while (true) {
            try {
                int choice = choiceTypeOfPurchases();

                ShowOption option = ShowOption.getOption(choice - 1);

                PurchaseViewerContext purchaseViewerContext = new PurchaseViewerContext(new PurchaseViewer(purchaseCollector));

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
                        purchaseViewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(new PurchaseFilter(purchaseCollector.getItems()).filterBy(type))));
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
                BigDecimal price = toBigDecimal(requestInput("Enter its price"));

                Purchase purchase = PurchaseFactory.getPurchase(purchaseType, name, price);

                addPurchase(purchase);
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

                new PurchaseAnalyzer(new PurchaseViewerContext(new PurchaseViewer(purchaseCollector))).withSort(choice - 1).analyse();

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

        addIncome(toBigDecimal(input));

        System.out.println("Income was added!\n");
    }

    public void addIncome(BigDecimal income) {
        budget = budget.add(income);
    }

    public void addPurchase(Purchase purchase) {
        purchaseCollector.add(purchase);
        addIncome(purchase.getPrice().negate());
    }

    private BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input.replace("$", "")).setScale(2, RoundingMode.HALF_UP);
    }
}
