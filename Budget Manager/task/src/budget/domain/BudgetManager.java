package budget.domain;

import budget.core.FileManager;
import budget.core.PurchaseAnalyzer;
import budget.core.PurchaseFileManager;
import budget.core.PurchaseFilter;
import budget.core.view.PurchaseViewer;
import budget.core.view.PurchaseViewerContext;
import budget.model.Purchase;
import budget.utils.PurchaseType;
import budget.utils.SortOption;

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
            String input = choose(SortOption.toList(), "How do you want to sort?", BACK);

            if (!input.matches("[1-4]")) {
                System.out.println("\nPlease enter a number between 1 and 4");
            } else if ("4".equals(input)) {
                System.out.println();
                return;
            } else {
                new PurchaseAnalyzer(new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector())))
                        .withSort(Integer.parseInt(input) - 1)
                        .analyse();
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
            String input = choose(PurchaseType.toList(), PURCHASE_TYPE_CHOICE_MESSAGE, BACK);;

            if (!input.matches("[1-5]")) {
                System.out.println("\nPlease enter a number between 1 and 5!\n");
            } else if ("5".equals(input)){
                System.out.println();
                return;
            } else {
                int choice = Integer.parseInt(input);
                final PurchaseType purchaseType = PurchaseType.get(choice - 1);
                addPurchase(purchaseType);
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
            String input = choose(PurchaseType.toList(), "Choose the type of purchases", ALL, BACK);

            if (!input.matches("[1-6]")) {
                System.out.println("Please enter a number between 1 and 6.");
            } else if ("6".equals(input)) {
                System.out.println();
                return;
            } else {
                process(input);
            }
        }
    }

    private void process(String input) {
        int choice = Integer.parseInt(input);
        PurchaseViewerContext purchaseViewerContext = new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector()));

        final int length = PurchaseType.values().length;
        if (choice <= length) {
            final String type = PurchaseType.get(choice - 1).capitalize();
            purchaseViewerContext.setViewStrategy(new PurchaseViewer(new PurchaseCollector(PurchaseFilter
                    .filter(shoppingList.getPurchaseCollector().getItems(), type))));
            purchaseViewerContext.viewAllByType(type);
        } else {
            purchaseViewerContext.viewAll();
            purchaseViewerContext.showTotalPrices("Total sum");
        }
    }

    @Override
    public void exit() {
        System.out.println("\nBye!");
    }

    private void addPurchase(PurchaseType type) {
        String name = requestInput("\nEnter purchase name");
        BigDecimal price = toBigDecimal(requestInput("Enter its price"));

        Purchase purchase = PurchaseFactory.getPurchase(type, name, price);

        shoppingList.addPurchase(purchase);
        System.out.println("Purchase was added!");
    }

    private BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input.replace("$", "")).setScale(2, RoundingMode.HALF_UP);
    }
}
