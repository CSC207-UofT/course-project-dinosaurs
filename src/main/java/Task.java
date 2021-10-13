import java.time.ZonedDateTime;

public class Task {
    /**
     * A task for a student. Records the weight, due date, importance, completed status
     * and estimated length of a given task.
     */

    public int weight;
    public ZonedDateTime dueDate;
    public int importance;
    public double length;
    public boolean completed;

    /**
     * Constructors for the task. Completed is always false on initialization.
     */
    public Task() {
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
     * @param length The estimated time (in hours) which the task will take.
     */
    public Task(int weight, ZonedDateTime due, int importance, int length){
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
     * this would definitely change things for a TaskManager using length priority.
     */

    public void setLength(int newLength){
        this.length = newLength;
    }




}
