package model.criteria;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CriteriasCollection {

    @NotNull
    private final List<Criteria> criterias;

    public CriteriasCollection(@NotNull List<Criteria> criterias) {
        Objects.requireNonNull(criterias);

        this.criterias = criterias;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }
}
