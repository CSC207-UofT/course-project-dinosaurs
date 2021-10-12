import java.util.ArrayList;
import java.util.Objects;

import constants.Constants;

public class TaskManager {
    /**
     * A task manager which stores all the completed and incomplete
     * tasks and keeps the completed tasks sorted based on priority.
     * Default priority is by due date.
     */

    public ArrayList<Task> incomplete;
    public ArrayList<Task> complete;
    public String priority;

    public TaskManager(){
        this.incomplete = new ArrayList<>();
        this.complete = new ArrayList<>();
        this.priority = Constants.DUEDATE;
    }


    /**
     * Adds a task into the TaskManager. Then sorts the task based on
     * current priority status.
     * @param task The task to be added into the task manager.
     * @return Returns true iff the task was successfully added and sorted
     * into the task manager.
     */
    public boolean addTask(Task task){
        boolean added = this.incomplete.add(task);
        sort();
        return added;

    }


    /**
     * Changes the current priority to a different priority
     * in constants.
     * @param priority A priority organizing scheme as outlined in
     * constants.Constants.
     */
    public void changePriority(String priority){
        this.priority = priority;
        sort();

    }


    /**
     * Completes a given task from the incomplete list. Removes
     * it from the incomplete list and adds it to the completed
     * list.
     * @param task The task which was completed.
     * @return true if the task was completed successfully.
     * Returns false if the task was not on the incomplete list.
     */
    public boolean completeTask(Task task){
        if (this.incomplete.contains(task)) {
            this.incomplete.remove(task);
            task.complete();
            this.complete.add(task);
            return true;
        }
        else {
            return false;
        }


    }

    /**
     * Sorts the current incomplete list based on the current priority
     * of the TaskManager.
     * TODO: Add cases for different priorities.
     * TODO: A hashmap would be a much more efficient way of accessing this.
     */
    private void sort(){

        if (Objects.equals(this.priority, Constants.DUEDATE)) {
            this.incomplete.sort(new DueDateComparator());
        }

    }

    /**
     * Accesses the completed list of tasks in the task manager.
     * @return The completed list of tasks.
     */
    public ArrayList<Task> getCompleted() {
        return this.complete;
    }


}
