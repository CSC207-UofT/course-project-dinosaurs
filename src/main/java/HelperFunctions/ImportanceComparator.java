package HelperFunctions;

import Entities.Task;

import java.util.Comparator;

public class ImportanceComparator implements Comparator<Task> {

    /**
     * A comparator which compares importance between tasks.
     *
     * @param t1 The first task's importance to be compared.
     * @param t2 The second task's importance to be compared.
     * @return A positive integer if t1's importance is less than
     * t2's importance. 0 if they are equally importance. A negative
     * integer if t1's importance is greater than t2's.
     */

    @Override
    public int compare(Task t1, Task t2) {
        return t2.importance - t1.importance;
    }
}
