//import java.util.*;
//import java.time.ZonedDateTime;
//
//public class BlockScheduler {
//    /**
//     * Schedules a StudyBlock based on the selected blockLength,
//     * studyMethod, chosenTasks, and priorityType.
//
//     * Creates a new StudyBlock with Tasks by Priority
//     * TODO figure out how to represent, with breaks splitting task chunks appropriately
//     * @return Prioritized StudyBlock of specified blockLength.
//     */
//    public void schedule(StudyBlock targetBlock) {
//        List<Task> taskList = targetBlock.getTasks();
//        StudyBlock newBlock = new StudyBlock();
//        int totalLength = 0;
//        // TODO TaskManager sorts taskList by priorityType
//
//        while (totalLength < targetBlock.getLength()) {
//            // TODO implement all below
//            for (Task task : chosenTasks) {
//                targetBlock.add(task);
//                targetBlock.addLength(task.getLength());
//                // add break time to total time TODO this isn't correct
//                targetBlock.addLength(this.studyMethod.get(1));
//            }
//        }
//        // fill in extra space
//        if (newBlock.getLength() != this.maxLength) {
//            // TODO if there is extra time what do we do here? add a task or notify?
//        }
//        return newBlock;
//    }
//}
