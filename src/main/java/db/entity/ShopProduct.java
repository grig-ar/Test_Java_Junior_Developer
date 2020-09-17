package db.entity;

import org.jetbrains.annotations.NotNull;
import model.entity.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ShopProduct implements Product {

    private final transient int id;

    @NotNull
    private final String name;

    @NotNull
    private final BigDecimal price;

    public ShopProduct(int id, @NotNull String name, @NotNull BigDecimal price) {
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
