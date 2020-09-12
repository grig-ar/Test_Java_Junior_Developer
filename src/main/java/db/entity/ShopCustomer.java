package db.entity;

import com.sun.istack.internal.NotNull;
import model.Customer;

public class ShopCustomer implements Customer {
    @NotNull
    private final int id;

    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;

    ShopCustomer(@NotNull int id, @NotNull String firstName, @NotNull String lastName) {
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
