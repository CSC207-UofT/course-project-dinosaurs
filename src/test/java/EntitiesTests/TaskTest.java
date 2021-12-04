import static org.junit.Assert.*;
import org.junit.*;
import Entities.Task;

import java.time.LocalDate;

/**
 * Tests for tasks, mainly setters and getters.
 */
public class TaskTest {
    LocalDate d1 = LocalDate.now();
    Task t1 = new Task("t1", 15, d1, 5, 3);

    /**
     * Tests setters and getters for due date
     */
    @Test(timeout = 80)
    public void TestDueDate(){
        assertEquals(t1.dueDate, d1);
        t1.setDueDate(d1.plusDays(1));
        assertEquals(t1.dueDate, d1.plusDays(1));
    }

    /**
     * Tests setters and getters for weight
     */
    @Test(timeout = 80)
    public void TestWeight(){
        assertEquals(t1.weight, 15);
        t1.setWeight(10);
        assertEquals(t1.weight, 10);
    }

    /**
     * Tests setters and getters for length
     */
    @Test(timeout = 80)
    public void TestLength(){
        assertEquals(t1.length, 3);
        t1.setLength(50);
        assertEquals(t1.length, 50);
    }

    /**
     * Tests setters and getters for importance
     */
    @Test(timeout = 80)
    public void TestImportance(){
        assertEquals(t1.importance, 5);
        t1.setImportance(3);
        assertEquals(t1.importance, 3);
    }

    /**
     * Tests toString for task
     */
    @Test(timeout = 80)
    public void TestToString(){
        String formattedString = d1.toString();
        assertEquals(t1.toString(), "=== t1 ===\nDue Date: " + formattedString + "\n" +
                "Weight: 15\nImportance: 5\nLength: 3");
    }

}
