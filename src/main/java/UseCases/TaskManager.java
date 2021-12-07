package UseCases;

import Entities.Checklist;
import Entities.Task;
import Constants.*;

import java.time.LocalDate;

/**
 * A task manager class which handles the management of a Checklist.
 * Sorts the Checklist, adds/completes tasks and retrieves completed and
 * incomplete lists from the Checklist based on controller input.
 * Constructor for TaskManager.
 */
public class TaskManager {





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
     * Deletes a task from Entities.Checklist.
     * @param task The task to be removed from Entities.Checklist.
     */
    public void removeTask(Checklist checklist, Task task) {
        if (task.completed){
            checklist.complete.remove(task);
        }
        else {
            checklist.incomplete.remove(task);
        }
    }

    /**
     * Completes a given task from the Entities.Checklist's incomplete list.
     * Removes it from the incomplete list and adds it to the completed
     * list.
     * @param task The task which was completed.
     */
    public void completeTask(Checklist checklist, Task task){

        if (checklist.incomplete.contains(task)){
            checklist.incomplete.remove(task);
            task.complete();
            checklist.complete.add(task);
        }
    }

    /**
     * Reverts a given task from the Checklist's completed list.
     * Removes it from the completed list and adds it to the incomplete list.
     * @param checklist the Checklist on which the Task appears.
     * @param task The task to revert.
     */
    public void revertTask(Checklist checklist, Task task) {
        if (checklist.complete.contains(task)) {
            checklist.complete.remove(task);
            task.revert();
            checklist.incomplete.add(task);
        }
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
     * Helper method to construct a task from user input strings.
     * @param name String name of the Task
     * @param weight String percentage weight of the Task
     * @param dueDate LocalDate Task is due
     * @param importance String priority of the Task
     * @param length String expected length to complete the Task
     */
    public static Task addTaskHelper(String name, double weight, LocalDate dueDate, double importance,
                              String length) {
        return new Task(name, (int)weight, dueDate, (int)importance,
                Integer.parseInt(length));
    }

    /**
     * Helper method to construct a new empty checklist from user input strings.
     * @param name String name of the Checklist to be created.
     */
    public static Checklist addChecklistHelper(String name) {
        return new Checklist(name);
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
