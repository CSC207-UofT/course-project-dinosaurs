import Entities.Checklist;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the checklist entity.
 */
public class ChecklistTest {
    Checklist tasks = new Checklist("Checklist");

    TaskManager tm = new TaskManager();

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    LocalDate d3 = d1.plusDays(2);
    LocalDate d4 = d1.plusDays(3);

    Task t1 = new Task("t1", 15, d3, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 55, d4, 2, 7);
    Task t4 = new Task("t3", 75, d2, 3, 1);

    /**
     * Adds tasks to the checklist using taskmanager.
     */
    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
        tm.addTask(tasks, t3);
        tm.addTask(tasks, t4);
    }

    /**
     * Tests iterator implementation of checklist.
     */
    @Test(timeout = 80)
    public void TestIteratorOrder() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks){
            list.add(task);
        }
        assertEquals(tasks.incomplete, list);

    }

    /**
     * Tests toString of checklist.
     */
    @Test(timeout = 80)
    public void TestToString() {
        assertEquals(tasks.toString(), "[Checklist]\n\n" + t2.toString() +"\n\n" + t3.toString() + "\n\n" +
                t1.toString() + "\n\n" + t4.toString() + "\n\n");
    }
}
