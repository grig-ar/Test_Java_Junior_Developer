package db.dao;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.entity.ShopCustomer;
import db.entity.ShopStatistics;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopCustomerDao {
    @NotNull
    private final ShopDatabase database;

    public ShopCustomerDao(@NotNull ShopDatabase database) {
        Objects.requireNonNull(database);

        this.database = database;
    }

    public ShopCustomer getShopCustomerById(int id) {
        String query = "SELECT id, first_name, last_name FROM customer WHERE id = ?";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new ShopCustomer(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"));
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<ShopCustomer> getAllShopCustomers() {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, first_name, last_name FROM customer";

        try (PreparedStatement stmt = database.getConnection().prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()
        ) {
            while (resultSet.next()) {
                customers.add(new ShopCustomer(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public List<ShopCustomer> getShopCustomersByLastName(String lastName) {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, first_name, last_name FROM customer WHERE last_name = ?";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setString(1, lastName);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(new ShopCustomer(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public List<ShopCustomer> getShopCustomersByProductsAmount(String productName, int amount) {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, last_name, first_name " +
                " FROM customer " +
                " WHERE id IN ( " +
                " SELECT customer_id " +
                " FROM purchase " +
                " JOIN product ON product_id = product.id " +
                " WHERE name = ? " +
                " GROUP BY customer_id " +
                " HAVING COUNT(*) >= ? " +
                ")";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setString(1, productName);
            stmt.setInt(2, amount);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(new ShopCustomer(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public List<ShopCustomer> getShopCustomersByPriceSum(BigDecimal lowerBound, BigDecimal upperBound) {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, last_name, first_name " +
                " FROM customer " +
                " WHERE id IN ( " +
                " SELECT customer_id " +
                " FROM purchase " +
                " JOIN product ON product_id = product.id " +
                " GROUP BY customer_id " +
                " HAVING SUM(price) BETWEEN ? AND ? " +
                ")";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setBigDecimal(1, lowerBound);
            stmt.setBigDecimal(2, upperBound);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(new ShopCustomer(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public List<ShopCustomer> getShopCustomersByLeastProduct(int limit) {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, last_name, first_name " +
                " FROM customer " +
                " WHERE id in ( " +
                " SELECT customer_id " +
                " FROM purchase " +
                " GROUP BY customer_id " +
                " ORDER BY count(*) " +
                " LIMIT ? " +
                ")";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setInt(1, limit);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(new ShopCustomer(
                            resultSet.getInt("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public List<ShopStatistics> getAllInfo(Date lowerBound, Date upperBound) {
        List<ShopStatistics> statistics = new ArrayList<>();
        String query = "SELECT customer.id, product.id, SUM(price) AS expenses " +
                " FROM purchase " +
                " JOIN product ON purchase.product_id = product.id " +
                " JOIN customer ON purchase.customer_id = customer.id " +
                " WHERE purchase_date BETWEEN ? AND ? " +
                " GROUP BY customer.id, customer.first_name, customer.last_name, product.name " +
                " ORDER BY customer.first_name, customer.last_name, (SUM(price)) DESC";

        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setDate(1, lowerBound);
            stmt.setDate(2, upperBound);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    statistics.add(new ShopStatistics(
                            lowerBound,
                            upperBound,
                            database.getCustomerDao().getShopCustomerById(resultSet.getInt("customer.id")),
                            database.getProductDao().getShopProductById(resultSet.getInt("product.id")),
                            resultSet.getBigDecimal("expenses")));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return statistics;
    }
}
