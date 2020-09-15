package model.output.statistics;

import db.entity.ShopCustomerInfo;
import repository.DataRepository;

import java.sql.Date;

public class Statistics {

    private final Date startDate;

    private final Date endDate;

    public Statistics(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public ShopCustomerInfo collectCustomerInfo(DataRepository repository, int id) {
        return repository.getCustomerInfo(id, startDate, endDate);
    }

    public int getTotalDays(DataRepository repository) {
        return repository.getTotalDaysAmount(startDate, endDate);
    }
}
