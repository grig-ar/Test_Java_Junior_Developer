package db.entity;

import model.Purchase;

import java.sql.Date;

public class ShopPurchase implements Purchase {
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public int getCustomerId() {
        return 0;
    }

    @Override
    public int getProductId() {
        return 0;
    }
}
