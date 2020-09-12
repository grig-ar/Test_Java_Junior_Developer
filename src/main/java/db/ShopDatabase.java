package db;

import db.dao.ShopCustomerDao;
import db.dao.ShopProductDao;
import db.dao.ShopPurchaseDao;
import db.executor.AppExecutor;

import java.io.InputStream;
import java.sql.Connection;

public class ShopDatabase {
    private static volatile ShopDatabase instance;
    private AppExecutor appExecutor;

    public ShopCustomerDao shopCustomerDao;
    public ShopProductDao shopProductDao;
    public ShopPurchaseDao shopPurchaseDao;

    private ShopDatabase() {

    }

    public static ShopDatabase getInstance(AppExecutor executor) {
        if (instance == null) {
            synchronized (ShopDatabase.class) {
                if (instance == null) {
                    instance = new ShopDatabase();
                    instance.setExecutor(executor);
                }
            }
        }
        return instance;
    }

    private void setExecutor(AppExecutor executors) {
        appExecutor = executors;
    }

    public AppExecutor getAppExecutor() {
        return appExecutor;
    }
}
