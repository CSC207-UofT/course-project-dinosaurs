import org.junit.*;

import static org.junit.Assert.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class TaskManagerTest {

    TaskManager tasks = new TaskManager();

    ZonedDateTime d1 = ZonedDateTime.now();
    ZonedDateTime d2 = d1.plusDays(1);
    ZonedDateTime d3 = d1.plusDays(2);
    ZonedDateTime d4 = d1.plusDays(3);

    Task t1 = new Task(35, d3, 5, 3);
    Task t2 = new Task(35, d1, 4, 2);
    Task t3 = new Task(35, d4, 2, 7);
    Task t4 = new Task(35, d2, 3, 1);

    @Before
    public void setUp() {
        tasks.addTask(t1);
        tasks.addTask(t2);
        tasks.addTask(t3);
        tasks.addTask(t4);
    }

    @Test(timeout = 80)
    public void TestOrder() {
        ArrayList<Task> order = new ArrayList<>();
        order.add(t2);
        order.add(t4);
        order.add(t1);
        order.add(t3);

        assertEquals(tasks.incomplete, order);
    }

    @Test(timeout = 80)
    public void TestComplete() {
        ArrayList<Task> order = new ArrayList<>();
        order.add(t2);
        order.add(t4);
        order.add(t3);

        tasks.completeTask(t1);

        ArrayList<Task> complete = new ArrayList<>();
        complete.add(t1);

        assertEquals(tasks.incomplete, order);
        assertEquals(tasks.complete, complete);
    }


}
