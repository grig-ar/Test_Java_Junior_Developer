package model.criteria;

import org.jetbrains.annotations.NotNull;
import db.entity.ShopCustomer;
import repository.DataRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ExpensesCriteria implements Criteria {
    @NotNull
    private final BigDecimal minExpenses;

    @NotNull
    private final BigDecimal maxExpenses;

    public ExpensesCriteria(@NotNull BigDecimal minExpenses, @NotNull BigDecimal maxExpenses) {
        Objects.requireNonNull(minExpenses);
        Objects.requireNonNull(maxExpenses);

        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) throws SQLException {
        return repository.getShopCustomersByPriceSum(minExpenses, maxExpenses);
    }
}
