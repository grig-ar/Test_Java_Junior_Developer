package model.result.statistics;

import org.jetbrains.annotations.NotNull;
import model.result.Result;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

public class StatisticsResult implements Result {

    private final String type = "stat";

    private final int totalDays;

    @NotNull
    private final List<CustomerStatistics> customers;

    @NotNull
    private final BigDecimal totalExpenses;

    @NotNull
    private final BigDecimal avgExpenses;

    public StatisticsResult(int totalDays, @NotNull List<CustomerStatistics> customers) {
        Objects.requireNonNull(customers);

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
        if (customers.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return totalExpenses.divide(BigDecimal.valueOf(customers.size()), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String getType() {
        return type;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public List<CustomerStatistics> getCustomers() {
        return customers;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public BigDecimal getAvgExpenses() {
        return avgExpenses;
    }
}
