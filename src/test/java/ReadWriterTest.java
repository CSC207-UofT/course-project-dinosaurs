import HelperFunctions.ChecklistReadWriter;
import HelperFunctions.StudyBlockReadWriter;
import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.ChecklistSaver;
import UseCases.StudyBlock;
import UseCases.StudyBlockSaver;
import UseCases.TaskManager;
import org.junit.*;

import java.io.IOException;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;


public class ReadWriterTest {

    Checklist tasks = new Checklist("Checklist 1");

    TaskManager tm = new TaskManager();

    ZonedDateTime d1 = ZonedDateTime.now();
    ZonedDateTime d2 = d1.plusDays(1);
    ZonedDateTime d3 = d1.plusDays(2);
    ZonedDateTime d4 = d1.plusDays(3);

    Task t1 = new Task("t1", 15, d3, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 55, d4, 2, 7);
    Task t4 = new Task("t4", 75, d2, 3, 1);

    StudyMethod methodChosen = new StudyMethod(StudyMethod.POMODORO);
    StudyBlock newBlock = new StudyBlock("Block", methodChosen, tasks);

    Checklist tasks2 = new Checklist("Checklist 2");


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
    }

    @Test(timeout = 80)
    public void TestChecklistSave() throws IOException, ClassNotFoundException {

        ChecklistReadWriter readWriter = new ChecklistReadWriter();
        new ChecklistSaver(tasks);
        Checklist c1 = readWriter.readFromFile(tasks.name);

        assertEquals(c1.complete, tasks.complete);
    }

    @Test(timeout = 180)
    public void TestStudyBlockSave() throws IOException, ClassNotFoundException {

        StudyBlockReadWriter readWriter = new StudyBlockReadWriter();
        new StudyBlockSaver(newBlock);
        StudyBlock s1 = readWriter.readFromFile(newBlock.name);

        assertEquals(s1.name, newBlock.name);
    }

    @Test(timeout = 80)
    public void TestChecklistSaveTwo() throws IOException, ClassNotFoundException {

        ChecklistReadWriter readWriter = new ChecklistReadWriter();
        new ChecklistSaver(tasks);
        new ChecklistSaver(tasks2);
        Checklist c1 = readWriter.readFromFile(tasks.name);
        Checklist c2 = readWriter.readFromFile(tasks2.name);

        assertEquals(c1.complete, tasks.complete);
        assertEquals(c2.complete, tasks.complete);
    }
}
