package db;

import db.executor.AppExecutor;

public class ShopDatabase {
    private static volatile ShopDatabase instance;
    private AppExecutor appExecutor;

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
