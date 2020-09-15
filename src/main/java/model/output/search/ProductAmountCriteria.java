package model.output.search;

import db.entity.ShopCustomer;
import repository.DataRepository;

import java.util.List;

public class ProductAmountCriteria implements Criteria {

    private final String productName;

    private final int minTimes;

    public ProductAmountCriteria(String productName, int minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    @Override
    public List<ShopCustomer> search(DataRepository repository) {
        return repository.getShopCustomersByProductsAmount(productName, minTimes);
    }

}
