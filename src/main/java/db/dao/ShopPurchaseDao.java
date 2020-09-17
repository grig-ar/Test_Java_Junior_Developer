package db.dao;

import db.ShopDatabase;
import db.entity.ShopPurchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopPurchaseDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "purchase_date";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_PRODUCT_ID = "product_id";

    public ShopPurchase getShopPurchaseById(int id) throws SQLException {
        String query = "SELECT id, purchase_date, customer_id, product_id FROM purchase WHERE id = ?";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query);
            ) {
                stmt.setInt(1, id);
                ShopPurchase purchase = null;

                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        purchase = new ShopPurchase(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getDate(COLUMN_DATE),
                                resultSet.getInt(COLUMN_CUSTOMER_ID),
                                resultSet.getInt(COLUMN_PRODUCT_ID));
                    }
                }
                return purchase;
            }
        }));
    }

    public List<ShopPurchase> getAllShopPurchases() throws SQLException {
        List<ShopPurchase> purchases = new ArrayList<>();
        String query = "SELECT id, purchase_date, customer_id, product_id FROM purchase";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()
            ) {
                while (resultSet.next()) {
                    purchases.add(new ShopPurchase(
                            resultSet.getInt(COLUMN_ID),
                            resultSet.getDate(COLUMN_DATE),
                            resultSet.getInt(COLUMN_CUSTOMER_ID),
                            resultSet.getInt(COLUMN_PRODUCT_ID)));

                }
                return purchases;
            }
        }));
    }

    public Integer getTotalDaysAmount(Date lowerBound, Date upperBound) throws SQLException {
        String query = "SELECT COUNT(the_day) " +
                " FROM ( " +
                " SELECT generate_series(?::timestamp, ?, '1 day') AS the_day " +
                " ) days " +
                " WHERE EXTRACT('ISODOW' FROM the_day) < 6";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDate(1, lowerBound);
                stmt.setDate(2, upperBound);
                int totalDays = -1;

                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        totalDays = resultSet.getInt(1);
                    }
                }
                return totalDays;
            }
        }));
    }

}
