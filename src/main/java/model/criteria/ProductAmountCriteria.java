package model.criteria;

import org.jetbrains.annotations.NotNull;
import db.entity.ShopCustomer;
import repository.DataRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ProductAmountCriteria implements Criteria {
    @NotNull
    private final String productName;

    private final int minTimes;

    public ProductAmountCriteria(@NotNull String productName, int minTimes) {
        Objects.requireNonNull(productName);

        this.productName = productName;
        this.minTimes = minTimes;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) throws SQLException {
        return repository.getShopCustomersByProductsAmount(productName, minTimes);
    }

}
