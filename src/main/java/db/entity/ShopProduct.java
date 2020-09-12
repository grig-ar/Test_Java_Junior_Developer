package db.entity;

import model.Product;

import java.math.BigDecimal;

public class ShopProduct implements Product {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }
}
