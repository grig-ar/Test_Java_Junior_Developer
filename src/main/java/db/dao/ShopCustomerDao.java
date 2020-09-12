package db.dao;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.entity.ShopCustomer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class ShopCustomerDao {
    @NotNull
    private final ShopDatabase database;

    public ShopCustomerDao(@NotNull ShopDatabase database) {
        Objects.requireNonNull(database);

        this.database = database;
    }

    ShopCustomer getShopCustomerById(int id) {
        String query = "SELECT * FROM customer WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = null
        ) {
            stmt.setInt(1, id);
            resultSet = stmt.executeQuery();

        } catch (SQLException ex) {

        }
    }

    List<ShopCustomer> getAllShopCustomers() {

    }

    List<ShopCustomer> getShopCustomersByLastName(String lastName) {

    }

    List<ShopCustomer> getShopCustomersByProductsAmount(String productName, int amount) {

    }

    List<ShopCustomer> getShopCustomersByPriceSum(BigDecimal lowerBound, BigDecimal upperBound) {

    }

    List<ShopCustomer> getShopCustomersByLeastProduct(int limit) {

    }

    String getAllInfo(Date lowerBound, Date upperBound) {

    }
}
