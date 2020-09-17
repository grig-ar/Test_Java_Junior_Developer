package model.criteria;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.sql.SQLException;
import java.util.List;

public interface Criteria {

    List<ShopCustomer> search(DataRepository repository) throws SQLException;
}
