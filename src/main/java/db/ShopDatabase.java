package db;

import db.dao.ShopCustomerDao;
import db.dao.ShopProductDao;
import db.dao.ShopPurchaseDao;
import db.executor.AppExecutor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ShopDatabase {
    private DataSource dataSource;
    private static volatile ShopDatabase instance;
    private AppExecutor appExecutor;

    private ShopPurchaseDao purchaseDao;
    private ShopProductDao productDao;
    private ShopCustomerDao customerDao;

    private ShopDatabase() {

    }

    public static ShopDatabase getInstance(AppExecutor executor, DataSource dataSource) {
        if (instance == null) {
            synchronized (ShopDatabase.class) {
                if (instance == null) {
                    instance = new ShopDatabase();
                    instance.setExecutor(executor);
                    instance.setDataSource(dataSource);
                    instance.setDao();
                }
            }
        }
        return instance;
    }

    private void setExecutor(AppExecutor executor) {
        appExecutor = executor;
    }

    private void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void setDao() {
        this.customerDao = new ShopCustomerDao(this);
        this.productDao= new ShopProductDao(this);
        this.purchaseDao = new ShopPurchaseDao(this);
    }

    public ShopPurchaseDao getPurchaseDao() {
        return purchaseDao;
    }

    public ShopProductDao getProductDao() {
        return productDao;
    }

    public ShopCustomerDao getCustomerDao() {
        return customerDao;
    }

    public AppExecutor getAppExecutor() {
        return appExecutor;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
