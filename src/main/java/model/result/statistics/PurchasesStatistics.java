package model.result.statistics;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public class PurchasesStatistics {
    @NotNull
    private final String name;

    @NotNull
    private final BigDecimal expenses;

    public PurchasesStatistics(@NotNull String name, @NotNull BigDecimal expenses) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(expenses);

        this.name = name;
        this.expenses = expenses;
    }
}
