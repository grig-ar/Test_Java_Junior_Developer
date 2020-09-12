package db.entity;

import model.Customer;

public class ShopCustomer implements Customer {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }
}
