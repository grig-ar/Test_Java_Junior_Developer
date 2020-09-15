package model.output.statistics;

import java.math.BigDecimal;

public class PurchasesStatistics {

    private final String name;

    private final BigDecimal expenses;

    public PurchasesStatistics(String name, BigDecimal expenses) {
        this.name = name;
        this.expenses = expenses;
    }
}
