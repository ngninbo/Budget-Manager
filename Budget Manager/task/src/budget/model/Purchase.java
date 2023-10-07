package budget.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String name;
    private final BigDecimal price;
}
