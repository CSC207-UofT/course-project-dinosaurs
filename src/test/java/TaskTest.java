import static org.junit.Assert.*;
import org.junit.*;
import Entities.Task;

import java.time.LocalDate;

public class TaskTest {
    LocalDate d1 = LocalDate.now();
    Task t1 = new Task("t1", 15, d1, 5, 3);


    @Test(timeout = 80)
    public void TestDueDate(){
        assertEquals(t1.dueDate, d1);
    }

    @Test(timeout = 80)
    public void TestWeight(){
        assertEquals(t1.weight, 15);
    }

    @Test(timeout = 80)
    public void TestLength(){
        assertEquals(t1.length, 3);
    }

    @Test(timeout = 80)
    public void TestImportance(){
        assertEquals(t1.importance, 5);
    }

    @Test(timeout = 80)
    public void TestToString(){
        String formattedString = d1.toString();
        assertEquals(t1.toString(), "=== t1 ===\nDue Date: " + formattedString + "\n" +
                "Weight: 15\nImportance: 5\nLength: 3");
    }

}
