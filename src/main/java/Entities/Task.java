package Entities;

import java.time.*;
import java.time.format.DateTimeFormatter;

// TODO needs toString to work with TodoList/UseCases.TaskManager toString
// TODO needs getters and setters (to work with BlockScheduler)

public class Task {
    /**
     * A task for a student. Records the weight, due date, importance, completed status
     * and estimated length of a given task.
     */

    public String name;
    public int weight;
    public ZonedDateTime dueDate;
    public int importance;
    public int length;
    public boolean completed;

    /**
     * Constructors for the task. Completed is always false on initialization.
     */
    public Task() {
        this.name = "";
        this.weight = 0;
        this.dueDate = ZonedDateTime.now();
        this.importance = 0;
        this.length = 0;
        this.completed = false;
    }
    /**
     * Constructor which allows for inputting of parameters.
     * @param weight How much percentage the task is worth, 1 <= weight <= 100.
     * @param due The date (year, month, day, hour, minute) that the task is due.
     * @param importance The subjective importance of the task, 1-5 integers inclusive.
     * @param length The estimated time (in minutes) which the task will take.
     */
    public Task(String name, int weight, ZonedDateTime due, int importance, int length){
        this.name = name;
        this.weight = weight;
        this.dueDate = due;
        this.importance = importance;
        this.length = length;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     * TODO: Do we have to have a function which marks it back to incomplete?
     * If so, could change implementation of this function.
     */
    public void complete(){
        this.completed = true;
    }

    /**
     * Changes the importance of a given task.
     */
    public void setImportance(int newImportance){
        this.importance = newImportance;
    }

    /**
     * Changes the DueDate of a given task.
     */
    public void setDueDate(ZonedDateTime newDueDate){
        this.dueDate = newDueDate;
    }

    /**
     * Changes the weight of a given task.
     */
    public void setWeight(int Weight){
        this.weight = Weight;
    }

    /**
     * Changes the length of a given task in order to update the amount of time
     * left for a given task. I felt this was an important feature because
     * this would definitely change things for a UseCases.TaskManager using length priority.
     */

    public void setLength(int newLength){
        this.length = newLength;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        String formattedString = this.dueDate.format(formatter);
        return this.name + " Due Date: " + formattedString + "\n";
    }




}
