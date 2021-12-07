package Constants;

/**
 * Class holding the constant representing the weightage of tasks
 */
public class WeightSingleton {
    private final String weight;
    private static final WeightSingleton instance = new WeightSingleton();

    private WeightSingleton() {
        this.weight = "WEIGHT";
    }

    public static WeightSingleton getInstance() {
        return instance;
    }

    public String getWeight() {
        return weight;
    }

}
