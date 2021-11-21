import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.*;

import static org.junit.Assert.*;

import java.sql.Array;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import UseCases.StudyBlock;


public class StudyBlockTest {
    Checklist tasks = new Checklist("Checklist");

    TaskManager tm = new TaskManager();

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    LocalDate d3 = d1.plusDays(2);
    Task t1 = new Task("t1", 15, d1, 5, 30);
    Task t2 = new Task("t2", 35, d2, 4, 20);
//    Task t3 = new Task("t3", 35, d3, 3, 20);

    StudyMethod method = new StudyMethod(StudyMethod.POMODORO);
    StudyBlock block = new StudyBlock("studyBlock", method, tasks, 60);

    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
//        tm.addTask(tasks, t3);
    }


    @Test
    public void TestbreakUpStudyBlock() {
        int[][] a1 = block.breakUpStudyBlock();
        int[][] a2 = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        assertEquals(a1, a2);

    }

    @Test
    public void TestbreakUpStudyBlock2() {
        block.setLength(110);
        int[][] a1 = block.breakUpStudyBlock();
        int[][] a2 = new int[][]{{25, 5}, {25, 5}, {25, 5}, {20, 0}};
        assertEquals(a1, a2);

    }


    @Test
    /**
     * Tests when Task 1 goes into the 2nd study block
     */
    public void TestAssignTask() {
        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        msg.add("t1 | 25 min");
        msg.add("Break | 5 min");
        msg.add("t1 | 5 min");
        msg.add("t2 | 20 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    @Test
    public void TestAssignTask2() {
        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(20);
        t2.setLength(30);
        msg.add("t1 | 20 min");
        msg.add("t2 | 5 min");
        msg.add("Break | 5 min");
        msg.add("t2 | 25 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    @Test
    /**
     * Tests when Task 1 and Task 2 go into the 2nd study block
     */
    public void TestAssignTask3() {
        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(25);
        t2.setLength(25);
        msg.add("t1 | 25 min");
        msg.add("Break | 5 min");
        msg.add("t2 | 25 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    @Test
    public void TestAssignTask4() {
        block.setLength(90);
        int[][] array = new int[][]{{25, 5}, {25, 5}, {25, 5}, {0, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(50);
        msg.add("t1 | 25 min");
        msg.add("Break | 5 min");
        msg.add("t1 | 25 min");
        msg.add("Break | 5 min");
        msg.add("t2 | 20 min");
        msg.add("Break (extended) | 5 min + 5");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    @Test
    public void TestAssignTask5() {
        block.setLength(30);
        int[][] array = new int[][]{{25, 5}, {0, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(20);
        t2.setLength(5);
        msg.add("t1 | 20 min");
        msg.add("t2 | 5 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    /**
     * Uncomment t3 above
     */
    @Test
    public void TestAssignTask6() {
        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        Task t3 = new Task("t3", 35, d3, 3, 20);
        tm.addTask(tasks, t3);
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(15);
        t2.setLength(15);
        msg.add("t1 | 15 min");
        msg.add("t2 | 10 min");
        msg.add("Break | 5 min");
        msg.add("t2 | 5 min");
        msg.add("t3 | 20 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }


    /**
     * Change StudyMethod to DESKTIME
     */
    @Test
    public void TestAssignTask1DESKTIME() {
        block.setLength(138);
        method = new StudyMethod(StudyMethod.DESKTIME);
        int[][] array = new int[][]{{52, 17}, {52, 17}, {0, 0}};
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(26);
        t2.setLength(78);
        msg.add("t1 | 26 min");
        msg.add("t2 | 26 min");
        msg.add("Break | 17 min");
        msg.add("t2 | 52 min");
        msg.add("Break | 17 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }
}

