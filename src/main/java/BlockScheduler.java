import Entities.Task;
import UseCases.StudyBlock;

import java.util.*;

public class BlockScheduler {
    /**
     * Creates a new UseCases.StudyBlock based on the selected blockLength,
     * studyMethod, chosenTasks, and priorityType.
     */
    private double maxLength;
    private ArrayList<Integer> studyMethod;
    private ArrayList chosenTasks;
    private String priorityType;
    // TODO figure out type of chosenTasks: list of Tasks

    /**
     * Constructor for the BlockScheduler.
     *
     * @param blockLength Time of desired UseCases.StudyBlock in minutes.
     * @param studyMethod Preferred study scheduling method.
     * @param chosenTasks List of Tasks to be completed.
     * @param priorityType How the Tasks will be prioritized.
     */
    public BlockScheduler(double blockLength, ArrayList<Integer> studyMethod,
                          ArrayList chosenTasks, String priorityType) {
        this.maxLength = blockLength;
        this.studyMethod = studyMethod;
        this.chosenTasks = chosenTasks;
        this.priorityType = priorityType;
    }

    /**
     * Schedules a new UseCases.StudyBlock.
     * TODO figure out how to represent, with breaks splitting task chunks appropriately
     * @return Prioritized UseCases.StudyBlock of specified blockLength.
     */
    public StudyBlock schedule() {
        StudyBlock newBlock = new StudyBlock();
        List<Task> taskList = new ArrayList<>(this.chosenTasks); // shallow copy should be okay?
        // TODO UseCases.TaskManager sorts taskList by priorityType

        while (newBlock.length() < this.maxLength) {
            // TODO implement all below
            for (Task task : chosenTasks) {
                newBlock.add(task);
                newBlock.addLength(task.getLength());
                // add break time to total time
                newBlock.addLength(this.studyMethod.get(1));
            }
        }
        // fill in extra space
        if (newBlock.getLength() != this.maxLength) {
            // TODO if there is extra time what do we do here? add a task or notify?
        }
        return newBlock;
    }
}
