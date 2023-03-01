package budget.core;

import budget.domain.PurchaseFactory;
import budget.model.Purchase;
import budget.utils.PurchaseType;

import java.math.BigDecimal;

public class PurchaseBuilder {

    private PurchaseType type;
    private String name;
    private BigDecimal price;

    private PurchaseBuilder() {
    }

    public static PurchaseBuilder init() {
        return new PurchaseBuilder();
    }

    public PurchaseBuilder withType(PurchaseType type) {
        this.type = type;
        return this;
    }

    public PurchaseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PurchaseBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Purchase build() {
        return PurchaseFactory.of(type, name, price);
    }
}
