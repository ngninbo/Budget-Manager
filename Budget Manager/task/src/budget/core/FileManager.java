package budget.core;

public interface FileManager<T> {

    void save(T t);
    T load();
}
