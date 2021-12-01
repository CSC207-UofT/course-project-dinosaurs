package Constants;

import Entities.Task;
import HelperFunctions.DueDateComparator;
import HelperFunctions.ImportanceComparator;
import HelperFunctions.LengthComparator;
import HelperFunctions.WeightComparator;

import java.util.*;

/**
 * This class holds all the constants.
 */

public class Constants {
    public static final Map<String, Comparator<Entities.Task>> COMPARE;

    public static final ArrayList<Integer> POMODORO = new ArrayList<>(Arrays.asList(25, 5));
    public static final ArrayList<Integer> DESKTIME = new ArrayList<>(Arrays.asList(52, 17));
    public static final ArrayList<Integer> ULTRADIUM = new ArrayList<>(Arrays.asList(90, 0));

    static {
        COMPARE = new HashMap<>();
        COMPARE.put(DueDateSingleton.getInstance().getDueDate(), new DueDateComparator());
        COMPARE.put(LengthSingleton.getInstance().getLength(), new LengthComparator());
        COMPARE.put(ImportanceSingleton.getInstance().getImportance(), new ImportanceComparator());
        COMPARE.put(WeightSingleton.getInstance().getWeight(), new WeightComparator());
    }


}
