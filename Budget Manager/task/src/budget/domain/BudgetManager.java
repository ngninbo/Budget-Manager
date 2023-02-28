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

        String input = enter("\nEnter income");

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
            String input = choose(SortOption.toList(), "\nHow do you want to sort?", BACK);

            if (!input.matches("[1-4]")) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT.concat("\n"), 1, SortOption.size() + 1);
            } else if ("4".equals(input)) {
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

            if (!input.matches("[1-5]")) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT.concat("\n"), 1, PurchaseType.size() + 1);
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
            System.out.println("\n".concat(THE.concat(" ").concat(PURCHASE.concat(" ").concat(LIST_IS_EMPTY.concat("!\n")))));
            return;
        }

        while (true) {
            String input = choose(PurchaseType.toList(), PURCHASE_TYPE_CHOICE_MESSAGE.concat("s"), ALL, BACK);
            final int numberOfItems = PurchaseType.size() + 2;
            if (!input.matches("[1-6]")) {
                System.out.printf(VALID_NUMBER_INPUT_REQUIRED_TEXT.concat("\n"), 1, numberOfItems);
            } else if (String.valueOf(numberOfItems).equals(input)) {
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
            purchaseViewerContext.showTotalPrices(TOTAL.concat(" sum"));
        }
    }

    @Override
    public void exit() {
        System.out.println("\nBye!");
    }

    private void addPurchase(PurchaseType type) {
        String name = enter("\nEnter purchase name");
        BigDecimal price = toBigDecimal(enter("Enter its price"));

        Purchase purchase = PurchaseFactory.getPurchase(type, name, price);

        shoppingList.addPurchase(purchase);
        System.out.println("Purchase was added!");
    }

    private BigDecimal toBigDecimal(String input) {
        return new BigDecimal(input.replace("$", "")).setScale(2, RoundingMode.HALF_UP);
    }
}
