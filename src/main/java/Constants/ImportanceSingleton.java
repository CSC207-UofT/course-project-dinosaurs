package Constants;

/**
 * Class holding the constant representing the importance of tasks
 */
public class ImportanceSingleton {
    private final String importance;
    private static final ImportanceSingleton instance = new ImportanceSingleton();

    private ImportanceSingleton() {
        this.importance = "IMPORTANCE";
    }

    public static ImportanceSingleton getInstance() {
        return instance;
    }

    public String getImportance() {
        return importance;
    }


}
