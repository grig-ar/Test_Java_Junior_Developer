package db.entity;

import com.sun.istack.internal.NotNull;
import model.Customer;
import model.Product;
import model.CustomerInfo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class ShopCustomerInfo implements CustomerInfo {

    @NotNull
    private final Customer customer;

    @NotNull
    private final List<Product> products;

    @NotNull
    private final List<BigDecimal> expenses;

    public ShopCustomerInfo(@NotNull Customer customer, @NotNull List<Product> products,
                            @NotNull List<BigDecimal> expenses) {
        Objects.requireNonNull(customer);
        Objects.requireNonNull(products);
        Objects.requireNonNull(expenses);

        this.customer = customer;
        this.products = products;
        this.expenses = expenses;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public List<Product> getProducts() {
        return this.products;
    }

    @Override
    public List<BigDecimal> getExpenses() {
        return this.expenses;
    }
}
