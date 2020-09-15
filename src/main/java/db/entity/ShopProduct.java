package db.entity;

import com.sun.istack.internal.NotNull;
import model.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ShopProduct implements Product {
    @NotNull
    private final transient int id;

    @NotNull
    private final String name;

    @NotNull
    private final BigDecimal price;

    public ShopProduct(@NotNull int id, @NotNull String name, @NotNull BigDecimal price) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);

        this.id = id;
        this.name = name;
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
