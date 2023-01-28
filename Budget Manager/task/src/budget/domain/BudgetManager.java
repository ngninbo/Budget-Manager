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

    @Override
    public void showBalance() {
        shoppingList.showBalance();
    }

    @Override
    public void addIncome() {
        shoppingList.addIncome();
    }

    @Override
    public void analyse() {
        shoppingList.analyse();
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
