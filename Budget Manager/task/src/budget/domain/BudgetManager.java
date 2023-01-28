package budget.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static budget.utils.BudgetManagerUtils.requestInput;

public class BudgetManager implements Serializable {

    private static final long serialVersionUID = 1222L;
    private BigDecimal balance = BigDecimal.ZERO;
    private final ShoppingList shoppingList;

    public BudgetManager(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void showBalance() {

        BigDecimal tmp = balance
                .subtract(shoppingList.getTotalPrice())
                .setScale(2, RoundingMode.HALF_UP);

        System.out.printf("%nBalance: $%s%n\n", tmp);
    }

    public void addIncome() {
        String input = requestInput("\nEnter income");

        if (!input.matches("\\d+")) {
            return;
        }

        balance = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Income was added!\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BudgetManager)) return false;
        BudgetManager that = (BudgetManager) o;
        return Objects.equals(balance, that.balance) && Objects.equals(shoppingList, that.shoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, shoppingList);
    }

    @Override
    public String toString() {
        return "BudgetManager{" +
                "balance=" + balance +
                ", shoppingList=" + shoppingList +
                '}';
    }
}
