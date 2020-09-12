package db.entity;

import com.sun.istack.internal.NotNull;
import model.Purchase;

import java.sql.Date;

public class ShopPurchase implements Purchase {
    @NotNull
    private final int id;

    @NotNull
    private final Date purchaseDate;

    @NotNull
    private final int customerId;

    @NotNull
    private final int productId;

    ShopPurchase(@NotNull int id, @NotNull Date purchaseDate, @NotNull int customerId, @NotNull int productId) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.customerId = customerId;
        this.productId = productId;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public Date getDate() {
        return this.purchaseDate;
    }

    @Override
    public int getCustomerId() {
        return this.customerId;
    }

    @Override
    public int getProductId() {
        return this.productId;
    }
}
