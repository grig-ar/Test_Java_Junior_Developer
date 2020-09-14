package db;

import db.executor.AppExecutor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ShopDatabase {
    private DataSource dataSource;
    private static volatile ShopDatabase instance;
    private AppExecutor appExecutor;

    private ShopDatabase() {

    }

    public static ShopDatabase getInstance(AppExecutor executor, DataSource dataSource) {
        if (instance == null) {
            synchronized (ShopDatabase.class) {
                if (instance == null) {
                    instance = new ShopDatabase();
                    instance.setExecutor(executor);
                    instance.setDataSource(dataSource);
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

    public AppExecutor getAppExecutor() {
        return appExecutor;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
