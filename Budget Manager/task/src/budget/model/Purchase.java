package budget.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String name;
    private final BigDecimal price;

    public Purchase(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(getName(), purchase.getName()) && Objects.equals(getPrice(), purchase.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
