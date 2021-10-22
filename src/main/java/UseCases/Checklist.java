package UseCases;

import Constants.Constants;
import Entities.Task;
import HelperFunctions.DueDateComparator;

import java.util.ArrayList;
import java.util.Objects;

public class Checklist {
    /**
     * A UseCases.Checklist which stores all the completed and incomplete
     * tasks and keeps the completed tasks sorted based on priority.
     * Default priority is by due date.
     */

    public ArrayList<Task> incomplete;
    public ArrayList<Task> complete;
    public String priority;

    public Checklist() {
        this.incomplete = new ArrayList<>();
        this.complete = new ArrayList<>();
        this.priority = Constants.DUE_DATE;
    }

    /**
     * Overrides the Tostring method.
     */
    @Override
    public String toString(){
        StringBuilder message = new StringBuilder();
        for (Task task : this.incomplete){
            message.append(task).append("\n");
        }
        return message.toString();
    }
}
