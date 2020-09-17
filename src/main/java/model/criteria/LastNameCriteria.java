package model.criteria;

import org.jetbrains.annotations.NotNull;
import db.entity.ShopCustomer;
import repository.DataRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LastNameCriteria implements Criteria {
    @NotNull
    private final String lastName;

    public LastNameCriteria(@NotNull String lastName) {
        Objects.requireNonNull(lastName);

        this.lastName = lastName;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) throws SQLException {
        return repository.getShopCustomersByLastName(lastName);
    }

}
