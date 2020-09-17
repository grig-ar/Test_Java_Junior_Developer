import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import controller.Controller;
import controller.ExceptionHandler;
import db.ShopDatabase;
import org.apache.log4j.BasicConfigurator;
import repository.DataRepository;

public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        HikariConfig config = null;
        try {
            config = new HikariConfig("hikari.properties");
        } catch (IllegalArgumentException ex) {
            System.err.println("There is no 'hikari.properties' file in the current directory.");
            return;
        }
        config.setConnectionTimeout(60 * 1000L);
        config.setMaxLifetime(60 * 60 * 1000L);

        HikariDataSource dataSource = null;
        try {
            dataSource = new HikariDataSource(config);
        }
        catch (HikariPool.PoolInitializationException ex) {
            System.err.println("Incorrect config file");
            return;
        }
        ShopDatabase shopDatabase = ShopDatabase.getInstance(dataSource);
        DataRepository dataRepository = DataRepository.getInstance();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .create();

        ExceptionHandler exceptionHandler = new ExceptionHandler(gson);
        Controller controller = new Controller(args, exceptionHandler, dataRepository, gson);
        controller.run();
    }
}
