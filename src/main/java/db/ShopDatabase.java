package db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ShopDatabase {
    private static DataSource dataSource;
    private static volatile ShopDatabase instance;

    private ShopDatabase() {

    }

    public static ShopDatabase getInstance(DataSource dataSource) {
        if (instance == null) {
            synchronized (ShopDatabase.class) {
                if (instance == null) {
                    instance = new ShopDatabase();
                    ShopDatabase.dataSource = dataSource;
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static <T> T execute(Callback<Connection, T> body) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return body.apply(conn);
        }
    }

    public interface Callback<T, R> {
        R apply(T t) throws SQLException;
    }

}
