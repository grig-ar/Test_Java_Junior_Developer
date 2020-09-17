package model.result.search;

import org.jetbrains.annotations.NotNull;
import db.entity.ShopCustomer;
import model.criteria.Criteria;

import java.util.List;
import java.util.Objects;

public class SearchElements {
    @NotNull
    private final Criteria criteria;

    @NotNull
    private final List<ShopCustomer> results;

    public SearchElements(@NotNull Criteria criteria, @NotNull List<ShopCustomer> results) {
        Objects.requireNonNull(criteria);
        Objects.requireNonNull(results);

        this.criteria = criteria;
        this.results = results;
    }
}
