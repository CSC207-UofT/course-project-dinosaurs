package Constants;

import Entities.Task;
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

public class Constants {
    public static final String DUE_DATE = "DUE_DATE";
    public static final String LENGTH = "LENGTH";
    public static final String IMPORTANCE = "IMPORTANCE";
    public static final String WEIGHT = "WEIGHT";

    public static final Map<String, Comparator<Entities.Task>> COMPARE;
    static {
        COMPARE = new HashMap<>();
        COMPARE.put(DUE_DATE, new DueDateComparator());
        COMPARE.put(LENGTH, new LengthComparator());
        COMPARE.put(IMPORTANCE, new ImportanceComparator());
        COMPARE.put(WEIGHT, new WeightComparator());
    }


}
