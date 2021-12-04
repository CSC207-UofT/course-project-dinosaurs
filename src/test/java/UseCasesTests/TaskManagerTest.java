import Constants.Constants;
import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.*;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Tests for task manager
 */
public class TaskManagerTest {

    Checklist tasks = new Checklist("C1");


    TaskManager tm = new TaskManager();

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    LocalDate d3 = d1.plusDays(2);
    LocalDate d4 = d1.plusDays(3);

    Task t1 = new Task("t1", 15, d3, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 55, d4, 2, 7);
//    Task t4 = new Task("t4", 75, d2, 3, 1);
    Task t4 = tm.addTaskHelper("t4", "75", d2, "3", "1");

    StudyMethod method = new StudyMethod(Constants.POMODORO);


    /**
     * Adds tasks to checklist tasks
     */
    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
        tm.addTask(tasks, t3);
        tm.addTask(tasks, t4);
    }

    /**
     * Tests the order of the default priority for a checklist
     */
    @Test(timeout = 80)
    public void TestDefaultOrder() {
        ArrayList<Task> order = new ArrayList<>();
        order.add(t2);
        order.add(t4);
        order.add(t1);
        order.add(t3);

        assertEquals(tasks.incomplete, order);
    }

    /**
     * Tests the order of the length order for a checklist
     */
    @Test(timeout = 80)
    public void TestLengthOrder() {
        tm.changePriority(tasks, "LENGTH");
        ArrayList<Task> order = new ArrayList<>();
        order.add(t4);
        order.add(t2);
        order.add(t1);
        order.add(t3);

        assertEquals(tasks.incomplete, order);
    }

    /**
     * Tests the order of the importance order for a checklist
     */
    @Test(timeout = 80)
    public void TestImportanceOrder() {
        tm.changePriority(tasks, "IMPORTANCE");
        ArrayList<Task> order = new ArrayList<>();
        order.add(t1);
        order.add(t2);
        order.add(t4);
        order.add(t3);

        assertEquals(tasks.incomplete, order);
    }

    /**
     * Tests the order of the weight order for a checklist
     */
    @Test(timeout = 80)
    public void TestWeightOrder() {
        tm.changePriority(tasks, "WEIGHT");
        ArrayList<Task> order = new ArrayList<>();
        order.add(t4);
        order.add(t3);
        order.add(t2);
        order.add(t1);

        assertEquals(tasks.incomplete, order);
    }

    /**
     * Tests the complete function
     */
    @Test(timeout = 80)
    public void TestComplete() {
        ArrayList<Task> order = new ArrayList<>();
        order.add(t2);
        order.add(t4);
        order.add(t3);

        tm.completeTask(tasks, t1);

        ArrayList<Task> complete = new ArrayList<>();
        complete.add(t1);

        assertEquals(tasks.incomplete, order);
        assertEquals(tasks.complete, complete);

        tm.completeTask(tasks, t3);
        complete.add(t3);
        order.remove(t3);

        assertEquals(tasks.incomplete, order);
        assertEquals(tasks.complete, complete);
    }


    /**
     * Tests the copying function of the checklist.
     */
    @Test
    public void TestCopy(){
        Checklist copy = tm.copy(tasks);
        copy.incomplete.remove(0);
        assertNotEquals(copy, tasks);
        assertEquals(tasks.incomplete.size(), 4);
        assertEquals(copy.incomplete.size(), 3);
    }

    /**
     * Tests the copying function of the checklist.
     */
    @Test
    public void TestCopyName(){
        Checklist copy = tm.copy(tasks);
        assertEquals(tasks.toString(), copy.toString());
    }

    /**
     * Tests that an entire checklist can be completed.
     */
    @Test
    public void TestUpdateChecklist(){
        StudyBlock block = new StudyBlock("studyBlock", method, tasks, 60);
        tm.updateChecklist(tasks, block);
        assertEquals(tasks.toString(), "[C1]\n\n");
    }

    /**
     * Tests that an entire checklist can be completed.
     */
    @Test
    public void TestUpdateHalfChecklist(){
        t1.setLength(60);
        StudyBlock block = new StudyBlock("studyBlock", method, tasks, 60);
        tm.updateChecklist(tasks, block);
        assertEquals(2, tasks.incomplete.size());
    }

    /**
     * Tests that an entire checklist can be completed.
     */
    @Test
    public void TestUpdateNoneChecklist(){
        t2.setLength(65);
        StudyBlock block = new StudyBlock("studyBlock", method, tasks, 60);
        tm.updateChecklist(tasks, block);
        assertEquals(4, tasks.incomplete.size());
    }

    /**
     * Tests that an entire checklist can be completed.
     */
    @Test
    public void TestUpdateOneChecklist(){
        t2.setLength(50);
        StudyBlock block = new StudyBlock("studyBlock", method, tasks, 60);
        tm.updateChecklist(tasks, block);
        assertEquals(3, tasks.incomplete.size());
    }
}
