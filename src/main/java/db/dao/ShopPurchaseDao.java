package db.dao;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.entity.ShopPurchase;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class ShopPurchaseDao {
    @NotNull
    private final ShopDatabase database;

    public ShopPurchaseDao(@NotNull ShopDatabase database) {
        Objects.requireNonNull(database);

        this.database = database;
    }

    ShopPurchase getShopPurchaseById(int id) {

    }

    List<ShopPurchase> getAllShopPurchases() {

    }

    int getTotalDaysAmount(Date lowerBound, Date upperBound) {

    }

}
