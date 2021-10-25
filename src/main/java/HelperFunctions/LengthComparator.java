package HelperFunctions;

import Entities.Task;

import java.util.Comparator;

public class LengthComparator implements Comparator<Task> {

    /**
     * A comparator which compares lengths between tasks.
     *
     * @param t1 The first task's length to be compared.
     * @param t2 The second task's length to be compared.
     * @return A negative integer if t1's length is less than
     * t2's length. 0 if the lengths are equal. A positive
     * integer if t1's length is greater than t2's.
     */

    @Override
    public int compare(Task t1, Task t2) {
        return t1.length - t2.length;
    }

}