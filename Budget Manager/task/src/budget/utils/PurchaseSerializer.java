package budget.utils;

import budget.domain.ShoppingList;

import java.io.*;

public class PurchaseSerializer {

    /**
     * Serialize the given object to the file
     */
    public static void serialize(ShoppingList shoppingList, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(shoppingList);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static ShoppingList deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        ShoppingList obj = (ShoppingList) ois.readObject();
        ois.close();
        return obj;
    }
}
