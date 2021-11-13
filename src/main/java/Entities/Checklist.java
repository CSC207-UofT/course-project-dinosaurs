package Entities;

import Constants.Constants;
import Entities.Task;
import HelperFunctions.DueDateComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Checklist implements Serializable {
    /**
     * A Checklist which stores all the completed and incomplete
     * tasks and keeps the completed tasks sorted based on priority.
     * Default priority is by due date.
     * TODO data structure for complete, incomplete
     */

    public ArrayList<Task> incomplete;
    public ArrayList<Task> complete;
    public String priority;
    public String name;

    public Checklist(String name) {
        this.incomplete = new ArrayList<>();
        this.complete = new ArrayList<>();
        this.priority = Constants.DUE_DATE;
        this.name = name;
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
