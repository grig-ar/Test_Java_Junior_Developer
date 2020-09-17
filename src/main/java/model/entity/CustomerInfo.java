package model.entity;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerInfo {
    List<String> getProductNames();

    List<BigDecimal> getExpenses();
}
