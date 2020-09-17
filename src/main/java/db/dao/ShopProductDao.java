package db.dao;

import db.ShopDatabase;
import db.entity.ShopProduct;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopProductDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";

    public ShopProduct getShopProductById(int id) throws SQLException {
        String query = "SELECT id, name, price FROM product WHERE id = ?";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ShopProduct product = null;
                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        product = new ShopProduct(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_NAME),
                                resultSet.getBigDecimal(COLUMN_PRICE));
                    }
                }
                return product;
            }
        }));
    }

    public List<ShopProduct> getAllShopProducts() throws SQLException {
        List<ShopProduct> products = new ArrayList<>();
        String query = "SELECT id, name, price FROM product";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()
            ) {
                while (resultSet.next()) {
                    products.add(new ShopProduct(
                            resultSet.getInt(COLUMN_ID),
                            resultSet.getString(COLUMN_NAME),
                            resultSet.getBigDecimal(COLUMN_PRICE)));

                }
                return products;
            }
        }));
    }

}
