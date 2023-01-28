package budget.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PurchaseType {

    FOOD,
    CLOTHES,
    ENTERTAINMENT,
    OTHER;

    public static List<String> toList() {
        return Arrays.stream(values())
                .map(PurchaseType::name)
                .map(BudgetManagerUtils::capitalize)
                .collect(Collectors.toList());
    }

    public static PurchaseType getPurchaseType(int ordinal) {
        return Arrays.stream(values())
                .filter(purchaseType -> purchaseType.ordinal() == ordinal)
                .findFirst()
                .orElseThrow();
    }
}
