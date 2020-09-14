package repository;

import com.sun.istack.internal.NotNull;
import db.ShopDatabase;
import db.dao.ShopCustomerDao;
import db.dao.ShopProductDao;
import db.dao.ShopPurchaseDao;
import db.entity.ShopCustomer;
import db.entity.ShopProduct;
import db.entity.ShopPurchase;
import db.entity.ShopStatistics;
import db.executor.AppExecutor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class DataRepository {

    @NotNull
    private static volatile DataRepository instance;

    @NotNull
    private final AppExecutor appExecutor;

    @NotNull
    private final ShopCustomerDao shopCustomerDao;

    @NotNull
    private final ShopProductDao shopProductDao;

    @NotNull
    private final ShopPurchaseDao shopPurchaseDao;

    private DataRepository(@NotNull final ShopDatabase database) {
        Objects.requireNonNull(database);

        shopCustomerDao = database.getCustomerDao();
        shopProductDao = database.getProductDao();
        shopPurchaseDao = database.getPurchaseDao();
        appExecutor = database.getAppExecutor();
    }

    public static DataRepository getInstance(@NotNull final ShopDatabase database) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(database);
                }
            }
        }
        return instance;
    }

    public ShopCustomer getShopCustomerById(int id) {

    }

    public List<ShopCustomer> getAllShopCustomers() {

    }

    public List<ShopCustomer> getShopCustomersByLastName(String lastName) {

    }

    public List<ShopCustomer> getShopCustomersByProductsAmount(String productName, int amount) {

    }

    public List<ShopCustomer> getShopCustomersByPriceSum(BigDecimal lowerBound, BigDecimal upperBound) {

    }

    public List<ShopCustomer> getShopCustomersByLeastProduct(int limit) {

    }

    public List<ShopStatistics> getAllInfo(Date lowerBound, Date upperBound) {

    }

    public ShopProduct getShopProductById(int id) {

    }

    public List<ShopProduct> getAllShopProducts() {

    }

    public ShopPurchase getShopPurchaseById(int id) {

    }

    public List<ShopPurchase> getAllShopPurchases() {

    }

    public int getTotalDaysAmount(Date lowerBound, Date upperBound) {

    }

}
