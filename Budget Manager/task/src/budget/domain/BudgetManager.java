package budget.domain;

import budget.core.FileManager;
import budget.core.PurchaseFileManager;
import budget.core.PurchaseLoadingException;
import budget.core.PurchaseSavingException;
import budget.menu.Menu;
import budget.menu.PurchaseAddMenu;
import budget.menu.PurchaseAnalyseMenu;
import budget.menu.PurchaseShowMenu;

import java.io.Serializable;

import static budget.utils.BudgetManagerUtils.*;
import static budget.utils.StringUtils.matches;

public class BudgetManager implements Menu, Serializable {

    private static final long serialVersionUID = 1222L;
    private ShoppingList shoppingList;
    private final FileManager<ShoppingList> fileManager;

    public BudgetManager(ShoppingList shoppingList, PurchaseFileManager purchaseFileManager) {
        this.shoppingList = shoppingList;
        this.fileManager = purchaseFileManager;
    }

    public static BudgetManager of(String filename) {
        return new BudgetManager(new ShoppingList(), PurchaseFileManager.of(filename));
    }

    @Override
    public void showBalance() {
        System.out.printf("%nBalance: $%s%n\n", shoppingList.getBudget());
    }

    @Override
    public void addIncome() {

        String income = enterIncome();

        if (!matches(income, "\\d+")) {
            System.out.println("Please enter a valid number!\n");
            return;
        }

        shoppingList.addIncome(toBigDecimal(income));
        System.out.println("Income was added!\n");
    }

    @Override
    public void analyse() {
        new PurchaseAnalyseMenu(shoppingList).start();
    }

    @Override
    public void save() {
        try {
            fileManager.save(shoppingList);
        } catch (PurchaseSavingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try {
            this.shoppingList = fileManager.load();
        } catch (PurchaseLoadingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPurchase() {
        new PurchaseAddMenu(shoppingList).start();
    }

    @Override
    public void showPurchases() {
        new PurchaseShowMenu(shoppingList).start();
    }

    @Override
    public void exit() {
        System.out.println("\nBye!");
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    private String enterIncome() {
        return enter(ENTER_INCOME);
    }
}
