package budget.core;

public interface FileManager<T> {

    void save(T t) throws PurchaseSavingException;
    T load() throws PurchaseLoadingException;
}
