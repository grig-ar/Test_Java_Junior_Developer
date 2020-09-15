package model.output.search;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.util.List;

public class AmountCriteria implements Criteria {

    private final int badCustomers;

    public AmountCriteria(int badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) {
        return repository.getShopCustomersByLeastProduct(badCustomers);
    }

}
