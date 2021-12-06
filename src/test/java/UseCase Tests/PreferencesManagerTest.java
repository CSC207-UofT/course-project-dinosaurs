import Constants.Constants;
import Entities.StudyMethod;
import UseCases.PreferencesManager;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests PreferencesManager class.
 */
public class PreferencesManagerTest {

    StudyMethod testMethod = new StudyMethod(Constants.POMODORO);

    /**
     * Tests if changeStudyMethodHelper method returns a valid StudyMethod that matches input parameters.
     */
    @Test(timeout = 80)
    public void testChangeStudyMethodHelper() {
        StudyMethod helperStudyMethod = PreferencesManager.changeStudyMethodHelper("25", "5");
        assertEquals(testMethod.toString(), helperStudyMethod.toString());
    }
}
