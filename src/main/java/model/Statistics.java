package model;

import java.math.BigDecimal;
import java.sql.Date;

public interface Statistics {
    Date getBeginDate();

    Date getEndDate();

    Customer getCustomer();

    Product getProduct();

    BigDecimal getExpenses();
}
