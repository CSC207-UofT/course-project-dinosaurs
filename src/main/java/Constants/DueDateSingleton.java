package Constants;

/**
 * Class holding the constant representing the due date of tasks
 */

public class DueDateSingleton {
    private final String dueDate;
    private static final DueDateSingleton instance = new DueDateSingleton();

    private DueDateSingleton() {
        this.dueDate = "DUE_DATE";
    }

    public static DueDateSingleton getInstance() {
        return instance;
    }

    public String getDueDate() {
        return dueDate;
    }

}
