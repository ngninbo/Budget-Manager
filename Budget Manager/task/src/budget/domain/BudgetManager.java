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
import static budget.utils.StringUtils.createRegex;
import static budget.utils.StringUtils.matches;

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

        String income = enterIncome();

        if (!matches(income, "\\d+")) {
            System.out.println("Please enter a valid number!");
            return;
        }

        shoppingList.addIncome(toBigDecimal(income));
        System.out.println("Income was added!\n");
    }

    @Override
    public void analyse() {
        while (true) {
            String input = choose(SortOption.toList(), "\nHow do you want to sort?", BACK);
            final int min = 1;
            final int max = SortOption.size() + 1;

            if (!matches(input, createRegex(min, max))) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT, 1, max);
            } else if (String.valueOf(max).equals(input)) {
                System.out.println();
                return;
            } else {
                new PurchaseAnalyzer(new PurchaseViewerContext(new PurchaseViewer(shoppingList.getPurchaseCollector())))
                        .sort(SortOption.get(Integer.parseInt(input) - 1));
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
            String input = choose(PurchaseType.toList(), PURCHASE_TYPE_CHOICE_MESSAGE, BACK);
            final int min = 1;
            final int max = PurchaseType.size() + 1;
            if (!matches(input, createRegex(min, max))) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT, min, max);
            } else if (String.valueOf(max).equals(input)){
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
            System.out.println("\n".concat(String.join(" ", THE, PURCHASE, LIST_IS_EMPTY.concat("!\n"))));
            return;
        }

        while (true) {
            String input = chooseTypeOfPurchases();
            final int max = PurchaseType.size() + 2;
            final int min = 1;
            if (!matches(input, createRegex(min, max))) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT, min, max);
            } else if (String.valueOf(max).equals(input)) {
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
            purchaseViewerContext.showTotalPrices(String.join(" ", TOTAL, "sum"));
        }
    }

    @Override
    public void exit() {
        System.out.println("\nBye!");
    }

    private void addPurchase(PurchaseType type) {
        String name = enterPurchaseName();
        BigDecimal price = toBigDecimal(enterPrice());
        Purchase purchase = PurchaseFactory.create(type, name, price);

        shoppingList.addPurchase(purchase);
        System.out.println("Purchase was added!");
    }

    private BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input.replace("$", "")).setScale(2, RoundingMode.HALF_UP);
    }
}
