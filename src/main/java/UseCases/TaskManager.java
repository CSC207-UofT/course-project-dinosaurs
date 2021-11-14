package UseCases;

import Entities.Checklist;
import Entities.Task;
import Constants.Constants;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskManager {

    /**
     * A task manager class which handles the management of a Checklist.
     * Sorts the Checklist, adds/completes tasks and retrieves completed and
     * incomplete lists from the Checklist based on controller input.
     * Constructor for TaskManager.
     * TODO look into clean architecture for creating a task from user interface.
     */

    public TaskManager(){

    }

    /**
     * Adds a task into the Entities.Checklist. Then sorts the task based on
     * current priority status.
     * @param task The task to be added into the Entities.Checklist.
     * @return Returns true iff the task was successfully added and sorted
     * into the Entities.Checklist.
     */
    public boolean addTask(Checklist checklist, Task task){

        boolean added = checklist.incomplete.add(task);
        sort(checklist);
        return added;
    }

    /**
     * Completes a given task from the Entities.Checklist's incomplete list.
     * Removes it from the incomplete list and adds it to the completed
     * list.
     * @param task The task which was completed.
     * @return true if the task was completed successfully.
     * Returns false if the task was not on the incomplete list.
     */
    public boolean completeTask(Checklist checklist, Task task){

        if (checklist.incomplete.contains(task)){
            checklist.incomplete.remove(task);
            task.complete();
            checklist.complete.add(task);
            return true;
        }
        else {return false;}
    }

    /**
     * Changes the current priority to a different priority
     * in constants.
     * @param priority A priority organizing scheme as outlined in
     * constants.Constants.
     */
    public void changePriority(Checklist checklist, String priority){
        checklist.priority = priority;
        sort(checklist);

    }

    /**
     * Helper method to construct a task from user input strings and add it to given checklist
     * @param name String name of the Task
     * @param weight String percentage weight of the Task
     * @param dueDate LocalDate Task is due
     * @param importance String priority of the Task
     * @param length String expected length to complete the Task
     */
    public Task addTaskHelper(String name, String weight, LocalDate dueDate, String importance,
                              String length) {
        return new Task(name, Integer.parseInt(weight), dueDate, Integer.parseInt(importance),
                Integer.parseInt(length));
    }

    /**
     * Sorts the current incomplete list based on the current priority
     * of the Entities.Checklist. Due Date is sorted from earliest to latest.
     * Importance and weight are sorted from highest to lowest. Length is sorted
     * from shortest to longest.
     * TODO: Should we implement a reverse order sorting function?
     */

    private void sort(Checklist checklist) {
        checklist.incomplete.sort(Constants.COMPARE.get(checklist.priority));
    }





}
