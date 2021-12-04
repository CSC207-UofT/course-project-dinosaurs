import Constants.Constants;
import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.StudyBlockManager;
import UseCases.TaskManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

/**
 * Tests for StudyBlockManager
 */
public class StudyBlockManagerTest {

    Checklist tasks = new Checklist("Checklist 1");

    TaskManager tm = new TaskManager();

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    LocalDate d3 = d1.plusDays(2);
    LocalDate d4 = d1.plusDays(3);

    Task t1 = new Task("t1", 15, d3, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 55, d4, 2, 7);
    Task t4 = new Task("t4", 75, d2, 3, 1);

    StudyMethod methodChosen = new StudyMethod(Constants.POMODORO);

    /**
     * Adds tasks to checklists
     */
    @Before
    public void setup() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
        tm.addTask(tasks, t3);
        tm.addTask(tasks, t4);
    }

    /**
     * Tests the successful creation of a study block
     */
    @Test
    public void TestCreateStudyBlock() {
        StudyBlock s1 = StudyBlockManager.createStudyBlock("StudyBlock1", methodChosen, tasks, "90");
        assertEquals(s1.name, "StudyBlock1");
    }
}
