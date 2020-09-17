package db.entity;

import org.jetbrains.annotations.NotNull;
import model.entity.Customer;

import java.util.Objects;

public class ShopCustomer implements Customer {

    private final transient int id;

    @NotNull
    private final String lastName;

    @NotNull
    private final String firstName;

    public ShopCustomer(int id, @NotNull String firstName, @NotNull String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }
}
