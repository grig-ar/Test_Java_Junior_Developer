package db.entity;

import org.jetbrains.annotations.NotNull;
import model.entity.CustomerInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ShopCustomerInfo implements CustomerInfo {

    @NotNull
    private final List<String> productNames;

    @NotNull
    private final List<BigDecimal> expenses;

    public ShopCustomerInfo(@NotNull List<String> productNames, @NotNull List<BigDecimal> expenses) {
        Objects.requireNonNull(productNames);
        Objects.requireNonNull(expenses);

        this.productNames = productNames;
        this.expenses = expenses;
    }

    @Override
    public List<String> getProductNames() {
        return this.productNames;
    }

    @Override
    public List<BigDecimal> getExpenses() {
        return this.expenses;
    }
}
