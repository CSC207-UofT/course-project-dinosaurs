import Entities.Checklist;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.*;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class TaskManagerTest {

    Checklist tasks = new Checklist();

    TaskManager tm = new TaskManager(tasks);

    ZonedDateTime d1 = ZonedDateTime.now();
    ZonedDateTime d2 = d1.plusDays(1);
    ZonedDateTime d3 = d1.plusDays(2);
    ZonedDateTime d4 = d1.plusDays(3);

    Task t1 = new Task("t1", 15, d3, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 55, d4, 2, 7);
    Task t4 = new Task("t3", 75, d2, 3, 1);

    @Before
    public void setUp() {
        tm.addTask(t1);
        tm.addTask(t2);
        tm.addTask(t3);
        tm.addTask(t4);
    }

    @Test(timeout = 80)
    public void TestDefaultOrder() {
        ArrayList<Task> order = new ArrayList<>();
        order.add(t2);
        order.add(t4);
        order.add(t1);
        order.add(t3);

        assertEquals(tm.getIncompleteList(), order);
    }

    @Test(timeout = 80)
    public void TestLengthOrder() {
        tm.changePriority("LENGTH");
        ArrayList<Task> order = new ArrayList<>();
        order.add(t4);
        order.add(t2);
        order.add(t1);
        order.add(t3);

        assertEquals(tm.getIncompleteList(), order);
    }

    @Test(timeout = 80)
    public void TestImportanceOrder() {
        tm.changePriority("IMPORTANCE");
        ArrayList<Task> order = new ArrayList<>();
        order.add(t1);
        order.add(t2);
        order.add(t4);
        order.add(t3);

        assertEquals(tm.getIncompleteList(), order);
    }

    @Test(timeout = 80)
    public void TestWeightOrder() {
        tm.changePriority("WEIGHT");
        ArrayList<Task> order = new ArrayList<>();
        order.add(t4);
        order.add(t3);
        order.add(t2);
        order.add(t1);

        assertEquals(tm.getIncompleteList(), order);
    }

    @Test(timeout = 80)
    public void TestComplete() {
        ArrayList<Task> order = new ArrayList<>();
        order.add(t2);
        order.add(t4);
        order.add(t3);

        tm.completeTask(t1);

        ArrayList<Task> complete = new ArrayList<>();
        complete.add(t1);

        assertEquals(tm.getIncompleteList(), order);
        assertEquals(tm.getCompletedList(), complete);

        tm.completeTask(t3);
        complete.add(t3);
        order.remove(t3);

        assertEquals(tm.getIncompleteList(), order);
        assertEquals(tm.getCompletedList(), complete);
    }


}
