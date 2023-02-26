package budget.utils;

import budget.domain.ShoppingList;

import java.io.*;

public class PurchaseSerializer {

    /**
     * Serialize shopping list and store the result to given file
     * @param shoppingList {@link ShoppingList}
     * @param fileName {@link String} file name
     * @throws IOException Exception thrown in case file doesn't exist
     */
    public static void serialize(ShoppingList shoppingList, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(shoppingList);
        oos.close();
    }

    /**
     * Deserialize shopping list from given file
     * @param fileName {@link String} File name
     * @return {@link ShoppingList} Restored shopping list
     * @throws IOException Exception thrown when file doesn't exist
     * @throws ClassNotFoundException Exception thrown when class not founded.
     * Occurred specially when urged modification was made in the serialized class
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
