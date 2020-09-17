package db.dao;

import db.ShopDatabase;
import db.entity.ShopCustomer;
import db.entity.ShopCustomerInfo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopCustomerDao {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";

    public ShopCustomer getShopCustomerById(int id) throws SQLException {
        String query = "SELECT id, first_name, last_name FROM customer WHERE id = ?";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ShopCustomer customer = null;
                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        customer = new ShopCustomer(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_FIRST_NAME),
                                resultSet.getString(COLUMN_LAST_NAME));
                    }
                }
                return customer;
            }
        }));
    }

    public List<ShopCustomer> getAllShopCustomers() throws SQLException {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, first_name, last_name FROM customer";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()
            ) {
                while (resultSet.next()) {
                    customers.add(new ShopCustomer(
                            resultSet.getInt(COLUMN_ID),
                            resultSet.getString(COLUMN_FIRST_NAME),
                            resultSet.getString(COLUMN_LAST_NAME)));

                }
                return customers;
            }
        }));
    }

    public List<ShopCustomer> getShopCustomersByLastName(String lastName) throws SQLException {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT id, first_name, last_name FROM customer WHERE last_name = ?";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, lastName);

                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new ShopCustomer(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_FIRST_NAME),
                                resultSet.getString(COLUMN_LAST_NAME)));
                    }
                }
                return customers;
            }
        }));
    }

    public List<ShopCustomer> getShopCustomersByProductsAmount(String productName, int amount)
            throws SQLException {
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

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, productName);
                stmt.setInt(2, amount);

                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new ShopCustomer(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_FIRST_NAME),
                                resultSet.getString(COLUMN_LAST_NAME)));
                    }
                }
                return customers;
            }
        }));
    }

    public List<ShopCustomer> getShopCustomersByPriceSum(BigDecimal lowerBound, BigDecimal upperBound)
            throws SQLException {
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

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setBigDecimal(1, lowerBound);
                stmt.setBigDecimal(2, upperBound);

                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new ShopCustomer(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_FIRST_NAME),
                                resultSet.getString(COLUMN_LAST_NAME)));
                    }
                }
                return customers;
            }
        }));
    }

    public List<ShopCustomer> getShopCustomersByLeastProduct(int limit) throws SQLException {
        List<ShopCustomer> customers = new ArrayList<>();
        String query = "SELECT customer.id, customer.last_name, customer.first_name " +
                " from customer " +
                " left outer join purchase on customer.id = customer_id " +
                " group by customer.id, customer.last_name, customer.first_name " +
                " order by coalesce(count(purchase.id), 0) " +
                " limit ?";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, limit);

                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        customers.add(new ShopCustomer(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_FIRST_NAME),
                                resultSet.getString(COLUMN_LAST_NAME)));
                    }
                }
                return customers;
            }
        }));
    }

    public List<Integer> getEveryId() throws SQLException {
        List<Integer> indices = new ArrayList<>();
        String query = "SELECT id FROM customer";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    indices.add(resultSet.getInt("id"));
                }
                return indices;
            }
        }));
    }

    public ShopCustomerInfo getCustomerInfo(int customerId, Date lowerBound, Date upperBound) throws SQLException {
        List<String> productNames = new ArrayList<>();
        List<BigDecimal> expenses = new ArrayList<>();

        String query = "SELECT customer.id, product.name, SUM(price) AS expenses " +
                " FROM purchase " +
                " JOIN product ON purchase.product_id = product.id " +
                " JOIN customer ON purchase.customer_id = customer.id " +
                " WHERE customer.id = ? AND purchase_date BETWEEN ? AND ? " +
                " GROUP BY customer.id, product.name " +
                " ORDER BY customer.id, (SUM(price)) DESC";

        return ShopDatabase.execute((conn -> {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, customerId);
                stmt.setDate(2, lowerBound);
                stmt.setDate(3, upperBound);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        productNames.add(resultSet.getString("name"));
                        expenses.add(resultSet.getBigDecimal("expenses"));
                    }
                }
                return new ShopCustomerInfo(productNames, expenses);
            }
        }));
    }
}
