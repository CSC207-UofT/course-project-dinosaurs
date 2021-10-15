import java.util.ArrayList;

public class StudyBlock {
    /**
     * TODO implement StudyBlock
     * Creates a new StudyBlock based on the selected blockLength,
     * studyMethod, chosenTasks, and priorityType.
     *
     * private double length;
     */

    private StudyMethod studyMethod;
    private Checklist checklist;
    private ArrayList<String> listTODO;

    /**
     * Constructor for the BlockScheduler.
     *
     * @param blockLength Length of desired StudyBlock in minutes. ADD after Phase 0
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
     *
     * @return a sorted listTODO
     */
    public void buildListTODO() {
        ArrayList<Task> task = this.checklist.getIncomplete();
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