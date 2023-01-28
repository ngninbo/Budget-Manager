package budget.core;

import budget.domain.BudgetManager;
import budget.domain.ShoppingList;
import budget.utils.PurchaseSerializer;

import java.io.File;
import java.io.IOException;

public class PurchaseFileManger {

    private final String filename;
    private BudgetManager budgetManager;

    public PurchaseFileManger(String filename) {
        this.filename = filename;
    }

    public void save(BudgetManager budgetManager) {
        try {
            PurchaseSerializer.serialize(budgetManager, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BudgetManager load() {
        File file = new File(filename);
        if (file.exists()) {
            try {
                budgetManager = PurchaseSerializer.deserialize(filename);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            budgetManager = new BudgetManager(new ShoppingList());
        }

        return budgetManager;
    }
}
