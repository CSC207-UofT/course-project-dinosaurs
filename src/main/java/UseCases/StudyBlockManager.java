package UseCases;


import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;

/**
 * A StudyBlock manager class which handles the creation of a StudyBlock based on controller input.
 */
public class StudyBlockManager {


    /**
     * Helper method to construct a task from user input strings and add it to given StudyBlock
     * @param name String name of the StudyBlock
     * @param studyMethod preferred StudyMethod that the user would like the StudyBlock to be divided according to
     * @param checklist Checklist that is used for the StudyBlock creation
     * @param length String the length of the StudyBlock
     */
    public static StudyBlock createStudyBlock(String name, StudyMethod studyMethod,
                                              Checklist checklist, String length) {
        return new StudyBlock(name, studyMethod,
                checklist, Integer.parseInt(length));
    }
}
