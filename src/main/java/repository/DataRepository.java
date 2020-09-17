package repository;

import org.jetbrains.annotations.NotNull;
import db.dao.ShopCustomerDao;
import db.dao.ShopProductDao;
import db.dao.ShopPurchaseDao;
import db.entity.ShopCustomer;
import db.entity.ShopCustomerInfo;
import db.entity.ShopProduct;
import db.entity.ShopPurchase;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DataRepository {

    private static volatile DataRepository instance;

    @NotNull
    private final ShopCustomerDao shopCustomerDao;

    @NotNull
    private final ShopProductDao shopProductDao;

    @NotNull
    private final ShopPurchaseDao shopPurchaseDao;

    private DataRepository() {
        shopCustomerDao = new ShopCustomerDao();
        shopProductDao = new ShopProductDao();
        shopPurchaseDao = new ShopPurchaseDao();
    }

    public static DataRepository getInstance() {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository();
                }
            }
        }
        return instance;
    }

    public ShopCustomer getShopCustomerById(int id) throws SQLException {
        return shopCustomerDao.getShopCustomerById(id);
    }

    public List<ShopCustomer> getAllShopCustomers() throws SQLException {
        return shopCustomerDao.getAllShopCustomers();
    }

    public List<ShopCustomer> getShopCustomersByLastName(String lastName) throws SQLException {
        return shopCustomerDao.getShopCustomersByLastName(lastName);
    }

    public List<ShopCustomer> getShopCustomersByProductsAmount(String productName, int amount)
            throws SQLException {
        return shopCustomerDao.getShopCustomersByProductsAmount(productName, amount);
    }

    public List<ShopCustomer> getShopCustomersByPriceSum(BigDecimal lowerBound, BigDecimal upperBound)
            throws SQLException {
        return shopCustomerDao.getShopCustomersByPriceSum(lowerBound, upperBound);
    }

    public List<ShopCustomer> getShopCustomersByLeastProduct(int limit) throws SQLException {
        return shopCustomerDao.getShopCustomersByLeastProduct(limit);
    }

    public List<Integer> getEveryCustomerId() throws SQLException {
        return shopCustomerDao.getEveryId();
    }

    public ShopCustomerInfo getCustomerInfo(int customerId, Date lowerBound, Date upperBound) throws SQLException {
        return shopCustomerDao.getCustomerInfo(customerId, lowerBound, upperBound);
    }

    public ShopProduct getShopProductById(int id) throws SQLException {
        return shopProductDao.getShopProductById(id);
    }

    public List<ShopProduct> getAllShopProducts() throws SQLException {
        return shopProductDao.getAllShopProducts();
    }

    public ShopPurchase getShopPurchaseById(int id) throws SQLException {
        return shopPurchaseDao.getShopPurchaseById(id);
    }

    public List<ShopPurchase> getAllShopPurchases() throws SQLException {
        return shopPurchaseDao.getAllShopPurchases();
    }

    public int getTotalDaysAmount(Date lowerBound, Date upperBound) throws SQLException {
        return shopPurchaseDao.getTotalDaysAmount(lowerBound, upperBound);
    }

}
