package model.criteria;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.sql.SQLException;
import java.util.List;

public class AmountCriteria implements Criteria {

    private final int badCustomers;

    public AmountCriteria(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) throws SQLException {
        return repository.getShopCustomersByLeastProduct(badCustomers);
    }

}
