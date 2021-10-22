package HelperFunctions;

import Entities.Task;

import java.util.Comparator;

public class WeightComparator implements Comparator<Task> {

    /**
     * A comparator which compares weights between tasks.
     *
     * @param t1 The first task's weight to be compared.
     * @param t2 The second task's weight to be compared.
     * @return A positive integer if t1's weight is less than
     * t2's weight. 0 if the weights are equal. A negative
     * integer if t1's weight is greater than t2's.
     */

    @Override
    public int compare(Task t1, Task t2) {
        return t2.weight - t1.weight;
    }

}
