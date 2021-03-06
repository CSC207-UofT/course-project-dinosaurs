import Constants.Constants;
import Infrastructure.ChecklistReadWriter;
import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import Entities.StudyBlock;
import UseCases.TaskManager;
import org.junit.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Tests for checklist readwriter, both saving and reading functions
 */
public class ChecklistReadWriterTest {

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

    Checklist taskSB = new Checklist("Checklist SB");

    Task t5 = new Task("t1", 15, d1, 5, 30);
    Task t6 = new Task("t2", 35, d2, 4, 20);



    StudyMethod methodChosen = new StudyMethod(Constants.POMODORO);
    StudyBlock block2 = new StudyBlock("StudyBlock 2", methodChosen, taskSB, 90);

    Checklist tasks2 = new Checklist("Checklist 2");

    /**
     * Creates checklists tasks and tasks2 for testing
     */
    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
        tm.addTask(tasks, t3);
        tm.addTask(tasks, t4);
        tm.addTask(tasks2, t1);
        tm.addTask(tasks2, t2);
        tm.addTask(tasks2, t3);
        tm.addTask(tasks2, t4);
        tm.changePriority(tasks2, "LENGTH");

        tm.addTask(taskSB, t5);
        tm.addTask(taskSB, t6);
        block2.setLength(90);
    }

    /**
     * Tests if tasks can be successfully saved and read
     * @throws IOException Error in file saving/reading
     * @throws ClassNotFoundException thrown if readwriter is not capable of finding the class
     */
    @Test(timeout = 80)
    public void TestChecklistReadAndSave() throws IOException, ClassNotFoundException {

        ChecklistReadWriter readWriter = new ChecklistReadWriter();
        try {
            readWriter.saveToFile(tasks.name, tasks);
        } catch (IOException e) {
            System.out.println(tasks.name + " did not save.");
        }
        Checklist c1 = readWriter.readFromFile(tasks.name);

        assertEquals(c1.complete, tasks.complete);
    }

    /**
     * Tests if two checklists can be successfully saved and read
     * @throws IOException Error in file saving/reading
     * @throws ClassNotFoundException thrown if readwriter is not capable of finding the class
     */
    @Test(timeout = 180)
    public void TestChecklistReadAndSaveTwo() throws IOException, ClassNotFoundException {

        ChecklistReadWriter readWriter = new ChecklistReadWriter();
        try {
            readWriter.saveToFile(tasks.name, tasks);
        } catch (IOException e) {
            System.out.println(tasks.name + " did not save.");
        }

        try {
            readWriter.saveToFile(tasks2.name, tasks2);
        } catch (IOException e) {
            System.out.println(tasks2.name + " did not save.");
        }
        Checklist c1 = readWriter.readFromFile(tasks.name);
        Checklist c2 = readWriter.readFromFile(tasks2.name);

        assertEquals(c1.complete, tasks.complete);
        assertEquals(c2.complete, tasks.complete);
    }
}
