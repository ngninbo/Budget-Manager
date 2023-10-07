package budget.core;

import budget.domain.ShoppingList;
import budget.utils.PurchaseSerializer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PurchaseFileManager implements FileManager<ShoppingList> {

    private final String filename;

    public static PurchaseFileManager of(String filename) {
        return new PurchaseFileManager(filename);
    }

    public void save(ShoppingList shoppingList) throws PurchaseSavingException {
        try {
            PurchaseSerializer.serialize(shoppingList, filename);
            System.out.println("\nPurchases were saved!\n");
        } catch (Exception e) {
            throw new PurchaseSavingException("Saving purchases failed!: " + e.getMessage());
        }
    }

    public ShoppingList load() throws PurchaseLoadingException {

        try {
            final ShoppingList shoppingList = PurchaseSerializer.deserialize(filename);
            System.out.println("\nLoading purchases succeed!\n");
            return shoppingList;
        } catch (Exception e) {
            System.out.printf("%nLoading purchases failed: %s%n\n", e.getMessage());
            throw new PurchaseLoadingException(String.format("%nLoading purchases failed: %s%n\n", e.getMessage()));
        }
    }
}
