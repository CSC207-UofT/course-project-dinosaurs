package Constants;

/**
 * Class holding the constant representing the length of tasks
 */

public class LengthSingleton {
    private String length;
    private static final LengthSingleton instance = new LengthSingleton();

    private LengthSingleton() {
        this.length = "LENGTH";
    }

    public static LengthSingleton getInstance() {
        return instance;
    }

    public String getLength() {
        return length;
    }

}
