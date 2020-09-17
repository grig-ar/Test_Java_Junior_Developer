package model.result.error;

import org.jetbrains.annotations.NotNull;
import model.result.Result;

import java.util.Objects;

public class ErrorResult implements Result {

    private final String type = "error";

    @NotNull
    private final String message;

    public ErrorResult(@NotNull String message) {
        Objects.requireNonNull(message);

        this.message = message;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
