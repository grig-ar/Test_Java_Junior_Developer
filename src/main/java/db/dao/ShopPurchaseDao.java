package db.dao;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.entity.ShopPurchase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopPurchaseDao {
    @NotNull
    private final ShopDatabase database;

    public ShopPurchaseDao(@NotNull ShopDatabase database) {
        Objects.requireNonNull(database);

        this.database = database;
    }

    public ShopPurchase getShopPurchaseById(int id) {
        String query = "SELECT id, purchase_date, customer_id, product_id FROM purchase WHERE id = ?";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query);
        ) {
            stmt.setInt(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new ShopPurchase(
                            resultSet.getInt("id"),
                            resultSet.getDate("purchase_date"),
                            resultSet.getInt("customer_id"),
                            resultSet.getInt("product_id"));
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<ShopPurchase> getAllShopPurchases() {
        List<ShopPurchase> purchases = new ArrayList<>();
        String query = "SELECT id, purchase_date, customer_id, product_id FROM purchase";

        try (PreparedStatement stmt = database.getConnection().prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()
        ) {
            while (resultSet.next()) {
                purchases.add(new ShopPurchase(
                        resultSet.getInt("id"),
                        resultSet.getDate("purchase_date"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("product_id")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return purchases;
    }

    public int getTotalDaysAmount(Date lowerBound, Date upperBound) {
        String query = "SELECT COUNT(the_day) " +
                " FROM ( " +
                " SELECT generate_series(?, ?, '1 day') AS the_day " +
                " ) days " +
                " WHERE EXTRACT('ISODOW' FROM the_day) < 6";

        try (PreparedStatement stmt = database.getConnection().prepareStatement(query);
        ) {
            stmt.setDate(1, lowerBound);
            stmt.setDate(2, upperBound);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

}
