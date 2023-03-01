package budget.domain;

import budget.utils.MenuItem;

public interface Menu {

    void showPurchases();
    void addPurchase();
    void analyse();
    void showBalance();
    void addIncome();
    void load();
    void save();
    void exit();

    default boolean process(MenuItem item) {

        if (item != null) {
            switch (item) {
                case INCOME:
                    addIncome();
                    break;
                case PURCHASE:
                    addPurchase();
                    break;
                case SHOW:
                    showPurchases();
                    break;
                case BALANCE:
                    showBalance();
                    break;
                case SAVE:
                    save();
                    break;
                case LOAD:
                    load();
                    break;
                case SORT:
                    analyse();
                    break;
                case EXIT:
                    exit();
                    return false;
                default:
                    // Implement me
            }
        }

        return true;
    }
}
