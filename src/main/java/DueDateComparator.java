import java.util.Comparator;

class DueDateComparator implements Comparator<Task> {

    /**
     * A comparator which compares two due dates between tasks.
     * @param t1
     * @param t2
     * @return
     */


    @Override
    public int compare(Task t1, Task t2){
        return t1.dueDate.compareTo(t2.dueDate);
    }
}
