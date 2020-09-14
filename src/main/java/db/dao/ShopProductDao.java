package db.dao;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.entity.ShopProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopProductDao {
    @NotNull
    private final ShopDatabase database;

    public ShopProductDao(@NotNull ShopDatabase database) {
        Objects.requireNonNull(database);

        this.database = database;
    }

    public ShopProduct getShopProductById(int id) {
        String query = "SELECT id, name, price FROM product WHERE id = ?";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query);
        ) {
            stmt.setInt(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new ShopProduct(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getBigDecimal("price"));
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<ShopProduct> getAllShopProducts() {
        List<ShopProduct> products = new ArrayList<>();
        String query = "SELECT id, name, price FROM product";

        try (PreparedStatement stmt = database.getConnection().prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()
        ) {
            while (resultSet.next()) {
                products.add(new ShopProduct(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBigDecimal("price")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

}
