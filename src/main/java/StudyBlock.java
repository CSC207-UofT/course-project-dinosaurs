import java.util.ArrayList;

public class StudyBlock {
    /**
     * TODO implement StudyBlock
     * Creates a new StudyBlock based on the selected blockLength,
     * studyMethod, chosenTasks, and priorityType.
     */
    private double length;
    private ArrayList<Integer> studyMethod;
    private ArrayList chosenTasks;
    private String priorityType;
    // TODO figure out type of chosenTasks: list of Tasks

    /**
     * Constructor for the BlockScheduler.
     *
     * @param blockLength Length of desired StudyBlock in minutes.
     * @param studyMethod Preferred study scheduling method.
     * @param chosenTasks List of Tasks to be completed.
     * @param priorityType How the Tasks will be prioritized.
     */
    public StudyBlock(double blockLength, ArrayList<Integer> studyMethod,
                          ArrayList chosenTasks, String priorityType) {
        this.length = blockLength;
        this.studyMethod = studyMethod;
        this.chosenTasks = chosenTasks;
        this.priorityType = priorityType;
    }

    // TODO getters and setters
}
