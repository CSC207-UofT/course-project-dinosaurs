import Entities.Task;
import org.junit.*;
import HelperFunctions.ImportanceComparator;

import java.time.LocalDate;

public class ImportanceComparatorTest {
    LocalDate d1 = LocalDate.now();
    LocalDate d2 = d1.plusDays(1);
    Task t1 = new Task("t1", 15, d2, 5, 3);
    Task t2 = new Task("t2", 35, d1, 4, 2);
    Task t3 = new Task("t3", 35, d2, 4, 2);
    ImportanceComparator ddc = new ImportanceComparator();

    @Test(timeout = 80)
    public void TestComparePositive(){
        int i = ddc.compare(t2, t1);
        assert(i > 0);
    }

    @Test(timeout = 80)
    public void TestCompareNegative(){
        int i = ddc.compare(t1, t2);
        assert(i < 0);
    }

    @Test(timeout = 80)
    public void TestEqual(){
        int i = ddc.compare(t2, t3);
        assert(i == 0);
    }
}
