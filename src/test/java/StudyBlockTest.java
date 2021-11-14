import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.TaskManager;
import org.junit.*;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import UseCases.StudyBlock;


public class StudyBlockTest {
    Checklist tasks = new Checklist();

    TaskManager tm = new TaskManager();

    ZonedDateTime d1 = ZonedDateTime.now();
    ZonedDateTime d2 = d1.plusDays(1);
    Task t1 = new Task("t1", 15, d1, 5, 30);
    Task t2 = new Task("t2", 35, d2, 4, 20);

    StudyMethod method = new StudyMethod(StudyMethod.POMODORO);
    StudyBlock block = new StudyBlock(method, tasks);

    @Before
    public void setUp() {
        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);

    }
//
//    @Test(timeout = 80)
//    public void TestBreakUp() {
//        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
//        assertEquals(array, block.breakUpStudyBlock(60));
//        ArrayList<String> msg = new ArrayList<>();
//        msg.add("t1 | 25 min");
//        msg.add("Break | 5 min");
//        msg.add("t1 | 5 min");
//        msg.add("t2 | 20 min");
//        msg.add("Break | 5 min");
//        ArrayList<String> msg2 = block.assignTasks(array);
//        assertEquals(msg, msg2);
//    }
//
//    @Test(timeout = 80)
//    public void TestBreakUp2() {
//        int[][] array = new int[][]{{25, 5}, {25, 5}, {0, 0}};
//        assertEquals(array, block.breakUpStudyBlock(60));
//        ArrayList<String> msg = new ArrayList<>();
//        t1.setLength(20);
//        t2.setLength(30);
//        msg.add("t1 | 20 min");
//        msg.add("t2 | 5 min");
//        msg.add("Break | 5 min");
//        msg.add("t2 | 25 min");
//        msg.add("Break | 5 min");
//        ArrayList<String> msg2 = block.assignTasks(array);
//        assertEquals(msg, msg2);
//    }

}
