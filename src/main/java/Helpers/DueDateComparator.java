package Helpers;

import Entities.Task;

import java.util.Comparator;

class DueDateComparator implements Comparator<Task> {

    /**
     * A comparator which compares two due dates between tasks.
     * @param t1 The first task's due date to be compared.
     * @param t2 The second task's due date to be compared.
     * @return A negative integer if t1's due date is before
     * t2's due date. 0 if the due dates are equal. A positive
     * integer if t1's due date is after t2's.
     */

    @Override
    public int compare(Task t1, Task t2){
        return t1.dueDate.compareTo(t2.dueDate);
    }
}
