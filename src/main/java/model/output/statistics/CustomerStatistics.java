package model.output.statistics;

import java.math.BigDecimal;
import java.util.List;

public class CustomerStatistics {

    private final String name;  // full_name

    private final List<PurchasesStatistics> purchases;

    private final BigDecimal totalExpenses;

    public CustomerStatistics(String name, List<PurchasesStatistics> purchases, BigDecimal totalExpenses) {
        this.name = name;
        this.purchases = purchases;
        this.totalExpenses = totalExpenses;
    }

    public String getName() {
        return name;
    }

    public List<PurchasesStatistics> getPurchases() {
        return purchases;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }
}
