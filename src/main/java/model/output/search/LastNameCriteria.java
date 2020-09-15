package model.output.search;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.util.List;

public class LastNameCriteria implements Criteria {

    private final String lastName;

    public LastNameCriteria(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) {
        return repository.getShopCustomersByLastName(lastName);
    }

}
