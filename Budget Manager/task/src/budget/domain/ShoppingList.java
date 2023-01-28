package budget.domain;

import budget.core.*;
import budget.core.view.PurchaseViewer;
import budget.model.Purchase;
import budget.utils.PurchaseType;
import budget.utils.ShowOption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static budget.utils.BudgetManagerUtils.*;

public class ShoppingList implements ShoppingListAction, Serializable {

    private static final long serialVersionUID = 11234L;

    private final List<Purchase> purchases = new ArrayList<>();

    @Override
    public void show() {
        if (purchases.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n%n");
            return;
        }

        while (true) {
            try {
                int choice = choiceTypeOfPurchases();

                ShowOption option = ShowOption.getOption(choice - 1);

                PurchaseViewer viewer = new PurchaseViewer(purchases);

                switch (option) {
                    case BACK:
                        System.out.println();
                        return;
                    case ALL:
                        viewer.viewAll();
                        viewer.showTotalPrices("Total sum");
                        break;
                    default:
                        final String type = PurchaseType.getPurchaseType(option.ordinal()).capitalize();
                        viewer = new PurchaseViewer(new PurchaseFilter(purchases).filterBy(type));
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

                Purchase purchase = PurchaseFactory.getPurchase(PurchaseType.getPurchaseType(choice - 1));
                purchases.add(purchase);
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

                new PurchaseAnalyzer(purchases).withSort(choice - 1).analyse();

            } catch (Exception e) {
                System.out.println("Unknown sort strategy");
            }
        }
    }

    public BigDecimal getTotalPrice() {
        return purchases.stream()
                .map(Purchase::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
