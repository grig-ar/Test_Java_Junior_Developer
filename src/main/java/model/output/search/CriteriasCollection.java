package model.output.search;

import java.util.List;

public class CriteriasCollection {

    private final List<Criteria> criterias;

    public CriteriasCollection(List<Criteria> criterias) {
        this.criterias = criterias;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }
}
