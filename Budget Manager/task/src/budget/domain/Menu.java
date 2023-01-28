package budget.domain;

public interface Menu extends ShoppingListAction {

    void load();
    void save();
    void showPurchases();
    void exit();
}
