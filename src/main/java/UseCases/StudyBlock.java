package UseCases;

import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class StudyBlock implements Serializable {
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
    public String name;

    /**
     * Constructor for the BlockScheduler.
     *
     * @param studyMethod Preferred study scheduling method.
     * @param checklist List of Tasks to be completed.
     */
    public StudyBlock(String name, StudyMethod studyMethod,
                      Checklist checklist) {
        this.studyMethod = studyMethod;
        this.checklist = checklist;
        this.listTODO = new ArrayList<>();
        this.name = name;
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

    public String toString() {
        buildListTODO();
        StringBuilder todo = new StringBuilder(" --- TODO List --- \n");

        for (String task: this.listTODO){
            todo.append(task).append("\n");
        }
        return todo.toString();
    }
}