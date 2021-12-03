import Constants.Constants;
import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import Entities.StudyBlock;

public class StudyBlockTest {
    Checklist tasks = new Checklist("Checklist");

    TaskManager tm = new TaskManager();

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    LocalDate d3 = d1.plusDays(2);
    Task t1 = new Task("t1", 15, d1, 5, 30);
    Task t2 = new Task("t2", 35, d2, 4, 20);

    StudyMethod method = new StudyMethod(Constants.POMODORO);
    StudyBlock block = new StudyBlock("studyBlock", method, tasks, 60);

    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
    }

    /**
     * Splits studyblock into even subblock
     */
    @Test
    public void TestbreakUpStudyBlock() {
        int[][] a1 = block.breakUpStudyBlock();
        int[][] a2 = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        assertEquals(a1, a2);
    }

    /**
     * Splits studyblock into non-even subblock
     */
    @Test
    public void TestbreakUpStudyBlock2() {
        block.setLength(89);
        int[][] a1 = block.breakUpStudyBlock();
        int[][] a2 = new int[][]{{25, 5}, {25, 5}, {25, 4}};
        assertEquals(a1, a2);
    }

    /**
     * Splits studyblock into one non-even subblock
     */
    @Test
    public void TestbreakUpStudyBlock3() {
        block.setLength(29);
        int[][] a1 = block.breakUpStudyBlock();
        int[][] a2 = new int[][]{{25, 4}};
        assertEquals(a1, a2);
    }
    /**
     * Splits studyblock into 2 even subblock
     */
    @Test
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

    /**
     * Splits studyblock into non-even subblock
     */
    @Test
    public void TestAssignTask1() {
        block.setLength(89);
        int[][] array = new int[][]{{25, 5}, {25, 5}, {25, 4}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(75);
        msg.add("t1 | 25 min");
        msg.add("Break | 5 min");
        msg.add("t1 | 25 min");
        msg.add("Break | 5 min");
        msg.add("t1 | 25 min");
        msg.add("Break | 4 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    /**
     * Splits studyblock into one non-even subblock
     */
    @Test
    public void TestAssignTask1a() {
        block.setLength(23);
        int[][] array = new int[][]{{23, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(23);
        msg.add("t1 | 23 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    /**
     * Splits studyblock into even subblocks
     */
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

    /**
     * Splits studyblock into 3 even subblocks
     */
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
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    /**
     * Splits studyblock into one even subblock
     */
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
     * Split Studyblock with 3 tasks
     */
    @Test
    public void TestAssignTask6() {
        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
        Task t3 = new Task("t3", 35, d3, 3, 25);
        tm.addTask(tasks, t3);
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(15);
        t2.setLength(10);
        msg.add("t1 | 15 min");
        msg.add("t2 | 10 min");
        msg.add("Break | 5 min");
        msg.add("t3 | 25 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    /**
     * For when there are too many tasks for the studyblock length.
     */
    @Test
    public void TestAssignTask7() {
        block.setLength(60);
        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
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
     * Split studybloack when using StudyMethod to DESKTIME
     */
    @Test
    public void TestAssignTask1DESKTIME() {
        block.setLength(138);
        method = new StudyMethod(Constants.DESKTIME);
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

    /**
     * Splits studyblock into even subblock with 2 tasks
     */
    @Test
    public void Testfillblock() {
        block.setLength(30);
        int[][] array = new int[][]{{25, 5}, {0, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(22);
        msg.add("t1 | 22 min");
        msg.add("t2 | 3 min");
        msg.add("Break | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }

    /**
     * Splits studyblock into one non-even subblock with no break time
     */
    @Test
    public void Testfillblock2() {
        block.setLength(10);
        int[][] array = new int[][]{{10, 0}};
        assertEquals(array, block.breakUpStudyBlock());
        ArrayList<String> msg = new ArrayList<>();
        t1.setLength(5);
        msg.add("t1 | 5 min");
        msg.add("t2 | 5 min");
        ArrayList<String> msg2 = block.assignTasks(array);
        assertEquals(msg, msg2);
    }
}

