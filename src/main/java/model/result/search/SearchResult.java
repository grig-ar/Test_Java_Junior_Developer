package model.result.search;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class SearchResult implements model.result.Result {

    private final String type = "search";

    @NotNull
    private final List<SearchElements> results;

    public SearchResult(@NotNull List<SearchElements> results) {
        Objects.requireNonNull(results);

        this.results = results;
    }

    @Override
    public String getType() {
        return type;
    }

    public List<SearchElements> getResults() {
        return results;
    }
}
