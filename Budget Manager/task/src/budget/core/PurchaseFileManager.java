package budget.core;

import budget.domain.ShoppingList;
import budget.utils.PurchaseSerializer;

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

        try {
            final ShoppingList shoppingList = PurchaseSerializer.deserialize(filename);
            System.out.println("\nLoading purchases succeed!\n");
            return shoppingList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("%nLoading purchases failed: %s%n\n", e.getMessage());
        }

        return new ShoppingList();
    }
}
