package model.output.search;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.util.List;

public interface Criteria {

    List<ShopCustomer> search(DataRepository repository);
}
