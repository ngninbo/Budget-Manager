package budget.domain;

import budget.core.FileManager;
import budget.core.PurchaseAnalyzer;
import budget.core.PurchaseFileManager;
import budget.core.PurchaseFilter;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.model.Purchase;
import budget.utils.BudgetManagerUtils;
import budget.utils.PurchaseType;
import budget.utils.ShowOption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static budget.utils.BudgetManagerUtils.*;

public class BudgetManager implements Menu, Serializable {

    private static final long serialVersionUID = 1222L;
    private ShoppingList shoppingList;
    private final FileManager<ShoppingList> fileManager;

    public BudgetManager(ShoppingList shoppingList, PurchaseFileManager purchaseFileManager) {
        this.shoppingList = shoppingList;
        this.fileManager = purchaseFileManager;
    }

    @Override
    public void showBalance() {
        System.out.printf("%nBalance: $%s%n\n", shoppingList.getBudget());
    }

    @Override
    public void addIncome() {

        String input = requestInput("\nEnter income");

        if (!input.matches("\\d+")) {
            System.out.println();
            return;
        }

        shoppingList.addIncome(toBigDecimal(input));
        System.out.println("Income was added!\n");
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

                new PurchaseAnalyzer(new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector())))
                        .withSort(choice - 1)
                        .analyse();

            } catch (Exception e) {
                System.out.println("\nUnknown sort strategy");
            }
        }
    }

    @Override
    public void save() {
        fileManager.save(shoppingList);
    }

    @Override
    public void load() {
        this.shoppingList = fileManager.load();
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
                BigDecimal price = toBigDecimal(requestInput("Enter its price"));

                Purchase purchase = PurchaseFactory.getPurchase(purchaseType, name, price);

                shoppingList.addPurchase(purchase);
                System.out.println("Purchase was added!");
            } catch (Exception e) {
                System.out.println("Unknown purchase type");
            }
        }
    }

    @Override
    public void showPurchases() {

        if (shoppingList.isEmpty()) {
            System.out.printf("%nThe purchase list is empty!\n%n");
            return;
        }

        while (true) {
            try {
                int choice = choiceTypeOfPurchases();

                ShowOption option = ShowOption.getOption(choice - 1);

                PurchaseViewerContext purchaseViewerContext = new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector()));

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
                        purchaseViewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(
                                new PurchaseFilter(shoppingList.getPurchaseCollector().getItems()).filterBy(type))));
                        purchaseViewerContext.viewAllByType(type);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unknown purchase type");
            }
        }
    }

    @Override
    public void exit() {
        System.out.println("\nBye!");
    }

    private BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input.replace("$", "")).setScale(2, RoundingMode.HALF_UP);
    }
}
