package db.dao;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.entity.ShopProduct;

import java.util.List;
import java.util.Objects;

public class ShopProductDao {
    @NotNull
    private final ShopDatabase database;

    public ShopProductDao(@NotNull ShopDatabase database) {
        Objects.requireNonNull(database);

        this.database = database;
    }

    ShopProduct getShopProductById(int id) {

    }

    List<ShopProduct> getAllShopProducts() {

    }

}
