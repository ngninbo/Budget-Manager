package budget.domain;

import budget.core.FileManager;
import budget.core.PurchaseFileManager;

import java.io.Serializable;

public class BudgetManager implements Menu, Serializable {

    private static final long serialVersionUID = 1222L;
    private ShoppingList shoppingList;
    private final FileManager<ShoppingList> fileManager;

    public BudgetManager(ShoppingList shoppingList, PurchaseFileManager purchaseFileManager) {
        this.shoppingList = shoppingList;
        this.fileManager = purchaseFileManager;
    }

    public void showBalance() {
        shoppingList.showBalance();
    }

    public void addIncome() {
        shoppingList.addIncome();
    }

    public void analyse() {
        shoppingList.analyse();
    }

    public void save() {
        fileManager.save(shoppingList);
    }

    public void load() {
        this.shoppingList = fileManager.load();
    }

    public void addPurchase() {
        shoppingList.addPurchase();
    }

    @Override
    public void showPurchases() {
        shoppingList.showPurchases();
    }

    @Override
    public void exit() {
        System.out.println("\nBye!");
    }
}
