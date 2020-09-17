package model.result.statistics;

import org.jetbrains.annotations.NotNull;
import db.entity.ShopCustomerInfo;
import repository.DataRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;

public class Statistics {
    @NotNull
    private final Date startDate;

    @NotNull
    private final Date endDate;

    public Statistics(@NotNull Date startDate, @NotNull Date endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }


    public ShopCustomerInfo collectCustomerInfo(DataRepository repository, int id) throws SQLException {
        return repository.getCustomerInfo(id, startDate, endDate);
    }

    public int getTotalDays(DataRepository repository) throws SQLException {
        return repository.getTotalDaysAmount(startDate, endDate);
    }
}
