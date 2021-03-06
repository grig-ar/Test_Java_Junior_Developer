package db.entity;

import org.jetbrains.annotations.NotNull;
import model.entity.Purchase;

import java.sql.Date;
import java.util.Objects;

public class ShopPurchase implements Purchase {

    private final int id;

    @NotNull
    private final Date purchaseDate;

    @NotNull
    private final int customerId;

    @NotNull
    private final int productId;

    public ShopPurchase(int id, @NotNull Date purchaseDate, @NotNull int customerId, @NotNull int productId) {
        Objects.requireNonNull(purchaseDate);
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(productId);

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
