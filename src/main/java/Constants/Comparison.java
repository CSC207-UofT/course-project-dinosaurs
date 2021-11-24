package Constants;

import HelperFunctions.DueDateComparator;
import HelperFunctions.ImportanceComparator;
import HelperFunctions.LengthComparator;
import HelperFunctions.WeightComparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all the constants.
 */

public class Comparison {
    public static final Map<String, Comparator<Entities.Task>> COMPARE;
    static {
        COMPARE = new HashMap<>();
        COMPARE.put(DueDateSingleton.getInstance().getDueDate(), new DueDateComparator());
        COMPARE.put(LengthSingleton.getInstance().getLength(), new LengthComparator());
        COMPARE.put(ImportanceSingleton.getInstance().getImportance(), new ImportanceComparator());
        COMPARE.put(WeightSingleton.getInstance().getWeight(), new WeightComparator());
    }


}
