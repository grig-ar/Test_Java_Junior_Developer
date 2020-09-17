package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;
import db.entity.ShopCustomer;
import db.entity.ShopCustomerInfo;
import model.criteria.Criteria;
import model.criteria.CriteriasCollection;
import model.result.Result;
import model.result.search.SearchElements;
import model.result.search.SearchResult;
import model.result.statistics.CustomerStatistics;
import model.result.statistics.PurchasesStatistics;
import model.result.statistics.Statistics;
import model.result.statistics.StatisticsResult;
import repository.DataRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Controller {
    @NotNull
    private final String[] args;

    @NotNull
    private final ExceptionHandler exceptionHandler;

    @NotNull
    private final DataRepository dataRepository;

    @NotNull
    private final Gson gson;

    @NotNull
    private String type;

    @NotNull
    private String inputFileName;

    @NotNull
    private String outputFileName;

    public Controller(@NotNull String[] args, @NotNull ExceptionHandler exceptionHandler,
                      @NotNull DataRepository dataRepository, @NotNull Gson gson) {
        Objects.requireNonNull(args);
        Objects.requireNonNull(exceptionHandler);
        Objects.requireNonNull(dataRepository);
        Objects.requireNonNull(gson);

        this.args = args;
        this.exceptionHandler = exceptionHandler;
        this.dataRepository = dataRepository;
        this.gson = gson;
    }

    private void parseArguments() {
        if (args.length == 3) {
            String tempType = args[0];
            if (!tempType.equalsIgnoreCase("search")
                    && !tempType.equalsIgnoreCase("stat")) {
                throw new IllegalArgumentException("Incorrect operation type");
            }
            type = tempType;
            inputFileName = args[1];
            outputFileName = args[2];
        } else {
            throw new IllegalArgumentException("Incorrect arguments");
        }
    }

    public void run() {
        try {
            parseArguments();
            Objects.requireNonNull(type);
            Objects.requireNonNull(inputFileName);
            Objects.requireNonNull(outputFileName);

            if (type.equals("search")) {
                search();
            } else if (type.equals("stat")) {
                collectStatistics();
            }

        } catch (Exception e) {
            exceptionHandler.handle(e);
        }

    }

    private void search() throws IOException, SQLException {
        JsonReader jsonReader = new JsonReader(new FileReader(inputFileName));
        JsonObject object = JsonParser.parseReader(jsonReader).getAsJsonObject();
        List<SearchElements> results = new ArrayList<>();
        CriteriasCollection criteriasCollection = CriteriaBuilder.buildCriterias(object);

        for (Criteria criteria : criteriasCollection.getCriterias()) {
            List<ShopCustomer> customers = criteria.search(dataRepository);
            results.add(new SearchElements(criteria, customers));
        }

        SearchResult resultsCollection = new SearchResult(results);

        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            gson.toJson(resultsCollection, SearchResult.class, fileWriter);
        }

        jsonReader.close();
    }

    private void collectStatistics() throws IOException, SQLException {
        JsonReader jsonReader = new JsonReader(new FileReader(inputFileName));
        Statistics statistics = gson.fromJson(jsonReader, Statistics.class);
        List<ShopCustomer> customers = dataRepository.getAllShopCustomers();
        int totalDays = statistics.getTotalDays(dataRepository);
        List<CustomerStatistics> customerStatisticsList = new ArrayList<>();

        for (ShopCustomer customer : customers) {
            ShopCustomerInfo customerInfo = statistics.collectCustomerInfo(dataRepository, customer.getId());
            List<String> productNames = customerInfo.getProductNames();
            int size = productNames.size();
            List<BigDecimal> expenses = customerInfo.getExpenses();
            BigDecimal sum = BigDecimal.ZERO;
            List<PurchasesStatistics> purchases = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                sum = sum.add(expenses.get(i));
                purchases.add(new PurchasesStatistics(productNames.get(i), expenses.get(i)));
            }

            customerStatisticsList.add(new CustomerStatistics(
                    customer.getLastName() + " " + customer.getFirstName(),
                    purchases, sum));
        }
        Result statisticsResult = new StatisticsResult(totalDays, customerStatisticsList);

        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            gson.toJson(statisticsResult, StatisticsResult.class, fileWriter);
        }

        jsonReader.close();
    }


}
