import java.time.ZonedDateTime;
import java.util.*;

public class ExampleData {
    /**
     * This class could be merged with Constants, but it holds entities
     * that will belong somewhere else in future to conform to Clean Architecture.
     *
     * TODO move program files into named packages so this can be moved to Constants
     */

    //default Student
    public static final Student DEFAULT_STUDENT = new Student();
    public static Map<String, Student> studentMap;
    static {
        studentMap = new HashMap<>();
        studentMap.put("DEFAULT_STUDENT", DEFAULT_STUDENT);
    }

    //default TodoList
    // TODO populate this list with tasks
    public static final Checklist DEFAULT_CHECKLIST = new Checklist();
    public static TaskManager newManager;
    static {newManager = new TaskManager(DEFAULT_CHECKLIST);}
    public static ZonedDateTime d1;
    public static ZonedDateTime d2;
    public static ZonedDateTime d3;
    public static ZonedDateTime d4;
    public static Task t1;
    public static Task t2;
    public static Task t3;
    public static Task t4;
    static {
        d1 = ZonedDateTime.now();
        d2 = d1.plusDays(1);
        d3 = d1.plusDays(2);
        d4 = d1.plusDays(3);
        t1 = new Task("t1", 35, d3, 5, 3);
        t2 = new Task("t2", 35, d1, 4, 2);
        t3 = new Task("t3",35, d4, 2, 7);
        t4 = new Task("t4", 35, d2, 3, 1);
        newManager.addTask(t1);
        newManager.addTask(t2);
        newManager.addTask(t3);
        newManager.addTask(t4);
    }

    // default StudyMethod
    private static final ArrayList<Integer> POMODORO = new ArrayList<>(Arrays.asList(25, 5));
    public static Map<String, ArrayList<Integer>> methodMap;
    static {
        methodMap = new HashMap<>();
        methodMap.put("POMODORO", POMODORO);
    }
}
