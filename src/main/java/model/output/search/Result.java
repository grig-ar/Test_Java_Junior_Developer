package model.output.search;

import db.entity.ShopCustomer;

import java.util.List;

public class Result {

    private final Criteria criteria;

    private final List<ShopCustomer> results;

    public Result(Criteria criteria, List<ShopCustomer> results) {
        this.criteria = criteria;
        this.results = results;
    }
}
