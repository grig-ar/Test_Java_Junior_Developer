package model.output.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StatisticsCollection {

    private final String type;

    private final int totalDays;

    private final List<CustomerStatistics> customers;

    private final BigDecimal totalExpenses;

    private final BigDecimal avgExpenses;

    public StatisticsCollection(String type, int totalDays, List<CustomerStatistics> customers) {
        this.type = type;
        this.totalDays = totalDays;
        this.customers = customers;
        this.totalExpenses = calculateTotalExpenses(customers);
        this.avgExpenses = calculateAvgExpenses(customers);
    }

    private BigDecimal calculateTotalExpenses(List<CustomerStatistics> customers) {
        BigDecimal total = BigDecimal.ZERO;

        for (CustomerStatistics customer : customers) {
            total = total.add(customer.getTotalExpenses());
        }

        return total;
    }

    private BigDecimal calculateAvgExpenses(List<CustomerStatistics> customers) {
        return totalExpenses.divide(BigDecimal.valueOf(customers.size()), 2, RoundingMode.HALF_UP);
    }
}
