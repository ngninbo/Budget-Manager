package budget.utils;

import budget.domain.BudgetManager;

import java.io.*;

public class PurchaseSerializer {

    /**
     * Serialize the given object to the file
     */
    public static void serialize(BudgetManager budgetManager, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(budgetManager);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static BudgetManager deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        BudgetManager obj = (BudgetManager) ois.readObject();
        ois.close();
        return obj;
    }
}
