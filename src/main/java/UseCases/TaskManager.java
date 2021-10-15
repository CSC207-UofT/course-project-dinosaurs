package UseCases;

import Entities.Checklist;
import Entities.Task;

import java.util.ArrayList;

public class TaskManager {

    /**
     * A task manager class which handles the management of a TodoList.
     * Sorts the Entities.Checklist, adds/completes tasks and retrieves completed and
     * incomplete lists from the TodoList based on controller input.
     */

    private Checklist todo;

    /**
     * Constructor for Taskmanager.
     * @param checklist The Entities.Checklist which the Taskmanager will work on.
     */

    public TaskManager(Checklist checklist){
        this.todo = checklist;
    }

    /**
     * Retrieves the incomplete list from the Entities.Checklist.
     */
    public ArrayList<Task> getIncompleteList(){
        return this.todo.getIncomplete();
    }

    /**
     * Retrieves the completed list from the Entities.Checklist.
     */
    public ArrayList<Task> getCompletedList(){
        return this.todo.getCompleted();
    }

    /**
     * Adds a task into the Entities.Checklist. Then sorts the task based on
     * current priority status.
     * @param task The task to be added into the Entities.Checklist.
     * @return Returns true iff the task was successfully added and sorted
     * into the Entities.Checklist.
     */
    public boolean addTask(Task task){
        return this.todo.addTask(task);
    }

    /**
     * Completes a given task from the Entities.Checklist's incomplete list.
     * Removes it from the incomplete list and adds it to the completed
     * list.
     * @param task The task which was completed.
     * @return true if the task was completed successfully.
     * Returns false if the task was not on the incomplete list.
     */
    public boolean completeTask(Task task){
        return this.todo.completeTask(task);
    }

    /**
     * Changes the current priority to a different priority
     * in constants.
     * @param priority A priority organizing scheme as outlined in
     * constants.Constants.
     */
    public void changePriority(String priority){
        this.todo.changePriority(priority);
        this.todo.sort();

    }





}
