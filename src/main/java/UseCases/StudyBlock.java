package UseCases;

import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudyBlock {
    /**
     * TODO implement UseCases.StudyBlock
     * Creates a new UseCases.StudyBlock based on the selected blockLength,
     * studyMethod, chosenTasks, and priorityType.
     *
     * TODO implement private double length;
     */

    private StudyMethod studyMethod;
    private Checklist checklist;
    private ArrayList<String> listTODO;

    /**
     * Constructor for the BlockScheduler.
     *
     * @param studyMethod Preferred study scheduling method.
     * @param checklist List of Tasks to be completed.
     */
    public StudyBlock(StudyMethod studyMethod,
                      Checklist checklist) {
        this.studyMethod = studyMethod;
        this.checklist = checklist;
        this.listTODO = new ArrayList<>();
    }

    // TODO getters and setters

    /**
     * Builds an ArrayList, <listTodo>, that incorporates the studyMethod and checklist.
     * TODO implement way of checking multiple tasks, using length input as well.
     */
    public void buildListTODO() {
        ArrayList<Task> task = this.checklist.incomplete;
        Task t1 = task.get(0);
        double x = t1.length;
        ArrayList<String> msg = new ArrayList<>();

        while ((x - this.studyMethod.getMethod().get(0)) >= 0){
            msg.add(t1.name + " | " + this.studyMethod.getMethod().get(0) + " min");
            msg.add("Break" + " | " + this.studyMethod.getMethod().get(1) + " min");
            x -= this.studyMethod.getMethod().get(0);
        }
        this.listTODO = msg;
    }

    /**
     * Breaks up the study block into a two dimensional Array which stores the
     * different increments of active and break time.
     * @param length The length of the study block in minutes.
     */
    public int[][] breakUpStudyBlock(int length){
        // We first need the number of full blocks we'll have and the amount of extra time leftover.
        // Note that extra + blocks == length
        int blocks = length / (this.studyMethod.getMethod().get(0) + this.studyMethod.getMethod().get(1));
        int extra = length % (this.studyMethod.getMethod().get(0) + this.studyMethod.getMethod().get(1));
        // We return a 2D array where the length is the number of block-breaks and the indexes of each
        // array are the amount of time for the block and break.
        int[][] array = new int[blocks + 1][2];
        for (int i = 0; i < blocks; i++) {
            array[i] = new int[]{this.studyMethod.getMethod().get(0), this.studyMethod.getMethod().get(1)};
        }
        // If the extra time is less than the active study block, tag it on as an extra active study block
        // If the extra time is more than the active study block, tag it on as a full
        // active study block and an extra break.
        // Note that if there is no extra time, this last index will be [0][0].
        if (extra < this.studyMethod.getMethod().get(0)){
            array[blocks] = new int[]{extra, 0};
        } else {
            array[blocks] = new int[]{this.studyMethod.getMethod().get(0), extra - this.studyMethod.getMethod().get(0)};
        }
        return array;
    }

    public ArrayList<String> assignTasks(int[][] array){
        ArrayList<String> msg = new ArrayList<>();
        // We want to build listTODO as a list of strings like before
        // e.g. ["T1 - 25 min", "Break - 5 min", ... ]
        int checklistIterator = 0;
        // I was using this to iterate through the checklist (i.e. get the next task)
        Task task = this.checklist.incomplete.get(checklistIterator);
        int taskLengthLeft = task.length;
        // Assign task as a variable for convenience and make a taskLengthLeft which updates as you
        // move through the study blocks
        for (int i = 0; i < (array.length - 1); i ++)
            if (taskLengthLeft > array[i][0]){
                msg.add(task.name + " | " + array[i][0] + " min");
                msg.add("Break" + " | " + array[i][1] + " min");
                taskLengthLeft -= array[i][0]; // Updates taskLengthLeft for the next array index
            } else {
                int taskLength = taskLengthLeft; // Set as variable because we need to update taskLengthLeft
                msg.add(task.name + " | " + taskLength + " min"); // Add remaining time from current task to
                // the study block
                checklistIterator += 1;
                task = this.checklist.incomplete.get(checklistIterator);
                taskLengthLeft = task.length;
                // Everything after this line is hard code, you'll likely have to edit this whole else statement
                // tbh
                msg.add(task.name + " | " + (array[i][0] - taskLength) + " min");
                msg.add("Break" + " | " + array[i][1] + " min");
                taskLengthLeft -= (array[i][0] - taskLength);
            }
        return msg;

    }

    public String toString() {
        buildListTODO();
        StringBuilder todo = new StringBuilder(" --- TODO List --- \n");

        for (String task: this.listTODO){
            todo.append(task).append("\n");
        }
        return todo.toString();
    }
}