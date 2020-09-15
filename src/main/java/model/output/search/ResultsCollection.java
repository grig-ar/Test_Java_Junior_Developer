package model.output.search;

import java.util.List;

public class ResultsCollection {

    private final String type;

    private final List<Result> results;

    public ResultsCollection(String type, List<Result> results) {
        this.type = type;
        this.results = results;
    }
}
