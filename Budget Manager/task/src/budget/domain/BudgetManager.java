package budget.domain;

import budget.core.FileManager;
import budget.core.PurchaseFileManager;

import java.io.Serializable;

public class BudgetManager implements Serializable {

    private static final long serialVersionUID = 1222L;
    private ShoppingList shoppingList;
    private final FileManager<ShoppingList> fileManager;

    public BudgetManager(ShoppingList shoppingList, String filename) {
        this.shoppingList = shoppingList;
        this.fileManager = new PurchaseFileManager(filename);
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

    public void showPurchases() {
        shoppingList.show();
    }
}
