package model.output.search;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.math.BigDecimal;
import java.util.List;

public class ExpensesCriteria implements Criteria {

    private final BigDecimal minExpenses;

    private final BigDecimal maxExpenses;

    public ExpensesCriteria(BigDecimal minExpenses, BigDecimal maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) {
        return repository.getShopCustomersByPriceSum(minExpenses, maxExpenses);
    }
}
