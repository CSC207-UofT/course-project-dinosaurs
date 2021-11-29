import Constants.Constants;
import Infrastructure.StudyBlockReadWriter;
import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.StudyBlock;
import UseCases.TaskManager;
import org.junit.*;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;


public class StudyBlockReadWriterTest {

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

    StudyBlock block = new StudyBlock("StudyBlock 1", methodChosen, taskSB, 60);

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

        tm.addTask(taskSB, t5);
        tm.addTask(taskSB, t6);
        block2.setLength(90);
    }

    @Test(timeout = 180)
    public void TestStudyBlockSave() throws IOException, ClassNotFoundException {

        StudyBlockReadWriter readWriter = new StudyBlockReadWriter();

        try {
            readWriter.saveToFile(block.name, block);
        } catch (IOException e) {
            System.out.println(block.name + " did not save.");
        }

        t5.setLength(50);
        try {
            readWriter.saveToFile(block2.name, block2);
        } catch (IOException e) {
            System.out.println(block2.name + " did not save.");
        }
        StudyBlock s1 = readWriter.readFromFile(block.name);
        StudyBlock s2 = readWriter.readFromFile(block2.name);

        assertEquals(s1.name, block.name);
        assertEquals(s2.name, block2.name);
    }

}