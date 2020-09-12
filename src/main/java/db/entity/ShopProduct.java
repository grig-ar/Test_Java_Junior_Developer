package db.entity;

import com.sun.istack.internal.NotNull;
import model.Product;

import java.math.BigDecimal;

public class ShopProduct implements Product {
    @NotNull
    private final int id;

    @NotNull
    private final String name;

    @NotNull
    private final BigDecimal price;

    ShopProduct(@NotNull int id, @NotNull String firstName, @NotNull BigDecimal price) {
        this.id = id;
        this.name = firstName;
        this.price = price;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }
}
