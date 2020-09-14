package db.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutor {
    private final ExecutorService diskIO;

    private AppExecutor(ExecutorService diskIO) {
        this.diskIO = diskIO;
    }

    public AppExecutor() {
        this(Executors.newSingleThreadExecutor());
    }

    public ExecutorService diskIO() {
        return diskIO;
    }
}
