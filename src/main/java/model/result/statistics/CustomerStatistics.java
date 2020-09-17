package model.result.statistics;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CustomerStatistics {
    @NotNull
    private final String name;  // full_name

    @NotNull
    private final List<PurchasesStatistics> purchases;

    @NotNull
    private final BigDecimal totalExpenses;

    public CustomerStatistics(@NotNull String name,
                              @NotNull List<PurchasesStatistics> purchases, @NotNull BigDecimal totalExpenses) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(purchases);
        Objects.requireNonNull(totalExpenses);

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
