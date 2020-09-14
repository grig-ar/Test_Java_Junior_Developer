package db.entity;

import com.sun.istack.internal.NotNull;
import model.Customer;
import model.Product;
import model.Statistics;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class ShopStatistics implements Statistics {
    @NotNull
    private final Date beginDate;

    @NotNull
    private final Date endDate;

    @NotNull
    private final Customer customer;

    @NotNull
    private final Product product;

    @NotNull
    private final BigDecimal expenses;

    public ShopStatistics(@NotNull Date beginDate, @NotNull Date endDate, @NotNull Customer customer,
                          @NotNull Product product, @NotNull BigDecimal expenses) {
        Objects.requireNonNull(beginDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(customer);
        Objects.requireNonNull(product);
        Objects.requireNonNull(expenses);

        this.beginDate = beginDate;
        this.endDate = endDate;
        this.customer = customer;
        this.product = product;
        this.expenses = expenses;
    }

    @Override
    public Date getBeginDate() {
        return this.beginDate;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public Product getProduct() {
        return this.product;
    }

    @Override
    public BigDecimal getExpenses() {
        return this.expenses;
    }
}
