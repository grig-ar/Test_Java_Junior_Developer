package model.entity;

import java.sql.Date;

public interface Purchase {
    int getId();

    Date getDate();

    int getCustomerId();

    int getProductId();
}
