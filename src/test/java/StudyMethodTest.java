import Constants.Constants;
import Entities.StudyMethod;
import org.junit.*;
import static org.junit.Assert.*;


public class StudyMethodTest {
    StudyMethod sm = new StudyMethod(25, 5);

    @Test(timeout = 80)
    public void TestGetStudyMethod(){
        assertEquals(sm.getMethod(), Constants.POMODORO);
    }

    @Test(timeout = 80)
    public void TestSetStudyMethod(){
        sm.setMethod(90, 0);
        assertEquals(sm.getMethod(), Constants.ULTRADIUM);
    }
}
