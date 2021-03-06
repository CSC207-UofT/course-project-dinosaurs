import Entities.Checklist;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.*;
import UseCases.TempCreator;
import Constants.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Tests the functionality of the temp creator use case
 */
public class TempCreatorTest {
    Checklist tasks = new Checklist("Checklist 1");

    TaskManager tm = new TaskManager();

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    LocalDate d3 = d1.plusDays(2);
    LocalDate d4 = d1.plusDays(3);
    LocalDate d5 = d1.plusDays(4);
    LocalDate d6 = d1.plusDays(5);

    Task t1 = new Task("t1", 15, d3, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 55, d4, 2, 7);
    Task t4 = new Task("t4", 75, d2, 3, 1);


    Task t5 = new Task("t5", 15, d5, 5, 30);
    Task t6 = new Task("t6", 35, d6, 4, 20);


    Checklist tasks2 = new Checklist("Checklist 2");

    /**
     * Adds all tasks to the given checklists
     */
    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
        tm.addTask(tasks, t3);
        tm.addTask(tasks, t4);
        tm.addTask(tasks2, t5);
        tm.addTask(tasks2, t6);
    }

    /**
     * Tests the length of a temp created with two checklists
     */
    @Test(timeout = 80)
    public void TestTempLength(){
        ArrayList<Checklist> checklists = new ArrayList<>();
        checklists.add(tasks);
        checklists.add(tasks2);
        Checklist c1 = TempCreator.createTemp("Temp", checklists, DueDateSingleton.getInstance().getDueDate());
        assertEquals(c1.incomplete.size(), 6);
    }

    /**
     * Tests the priority of the temp checklist
     */
    @Test(timeout = 80)
    public void TestTempPriority(){
        ArrayList<Checklist> checklists = new ArrayList<>();
        checklists.add(tasks);
        Checklist c1 = TempCreator.createTemp("Temp", checklists, LengthSingleton.getInstance().getLength());
        assertEquals(c1.incomplete.get(0), t4);
    }

    /**
     * Tests the to string of a temp created
     */
    @Test(timeout = 80)
    public void TestTempToString(){
        ArrayList<Checklist> checklists = new ArrayList<>();
        checklists.add(tasks);
        checklists.add(tasks2);
        Checklist c1 = TempCreator.createTemp("Temp", checklists, DueDateSingleton.getInstance().getDueDate());
        assertEquals(c1.toString(), "[Temp]\n\n" + t2.toString() + "\n\n" + t4.toString() +
                "\n\n" + t1.toString() + "\n\n" + t3.toString() + "\n\n" + t5.toString() + "\n\n" + t6.toString()
                + "\n\n");
    }
}
