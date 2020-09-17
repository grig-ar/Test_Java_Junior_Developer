package controller;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import model.result.error.ErrorResult;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.Objects;

public class ExceptionHandler {
    @NotNull
    private final Gson gson;

    public ExceptionHandler(@NotNull Gson gson) {
        Objects.requireNonNull(gson);

        this.gson = gson;
    }

    public void handle(Exception e) {

        try {
            int counter = 1;
            File myObj = new File("errorLog.json");
            while (!myObj.createNewFile()) {
                myObj = new File("errorLog" + counter + ".json");
                counter++;
            }
            String message = "";
            if (e instanceof SQLException) {
                message = handleSql((SQLException) e);
            } else {
                String cause = "";
                if (e.getCause() != null) {
                    cause = e.getCause().getMessage();
                }
                message = "Cause: " + cause + '\n' +
                        "Message: " + e.getMessage();
            }
            ErrorResult errorResult = new ErrorResult(message);
            try (FileWriter fileWriter = new FileWriter(myObj.getName())) {
                gson.toJson(errorResult, ErrorResult.class, fileWriter);
            }
        } catch (Exception exception) {
            System.err.println("Exception while handling exception");
            System.err.println(exception.getMessage());
            if (exception.getCause() != null) {
                System.err.println(exception.getCause().getMessage());
            }
        }
    }

    private String handleSql(SQLException ex) {
        StringBuilder builder = new StringBuilder();
        for (Throwable e : ex) {
            if (e instanceof SQLException && !ignoreSQLException(((SQLException) e).getSQLState())) {
                builder.append("SQLState: ").append(((SQLException) e).getSQLState()).append(' ');

                builder.append("Error Code: ").append(((SQLException) e).getErrorCode()).append(' ');

                builder.append("Message: ").append(e.getMessage()).append(' ');

                Throwable t = ex.getCause();
                while (t != null) {
                    builder.append("Cause: ").append(t).append(' ');
                    t = t.getCause();
                }

            }
        }
        return builder.toString();
    }

    private boolean ignoreSQLException(String sqlState) {
        if (sqlState == null) {
            return false;
        }

        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32")) {
            return true;
        }

        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55")) {
            return true;
        }

        return false;
    }

}
