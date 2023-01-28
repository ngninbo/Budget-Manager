package budget.core;

import budget.domain.ShoppingList;
import budget.utils.PurchaseSerializer;

import java.io.File;
import java.io.IOException;

public class PurchaseFileManager implements FileManager<ShoppingList> {

    private final String filename;

    public PurchaseFileManager(String filename) {
        this.filename = filename;
    }

    public void save(ShoppingList shoppingList) {
        try {
            PurchaseSerializer.serialize(shoppingList, filename);
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ShoppingList load() {
        File file = new File(filename);

        if (file.exists()) {
            try {
                return PurchaseSerializer.deserialize(filename);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                System.out.println("\nPurchases were loaded!\n");
            }
        }

        return new ShoppingList();
    }
}
