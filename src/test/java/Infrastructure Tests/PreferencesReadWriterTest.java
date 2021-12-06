import Constants.Constants;
import Entities.StudyMethod;
import Infrastructure.PreferencesReadWriter;
import org.junit.Before;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Tests for saving and loading using PreferencesReadWriter.
 */
public class PreferencesReadWriterTest {

    StudyMethod methodChosen = new StudyMethod(Constants.POMODORO);

    /**
     * Tests both saving and reading for PreferencesReadWriter.
     * @throws IOException Error in file saving/reading
     * @throws ClassNotFoundException thrown if readwriter is not capable of finding the class
     */
    @Test(timeout = 80)
    public void TestPreferencesReadAndSave() throws IOException, ClassNotFoundException {
        PreferencesReadWriter readWriter = new PreferencesReadWriter();
        try {
            readWriter.saveToFile("StudyMethod", methodChosen);
        } catch (IOException e) {
            System.out.println("StudyMethod did not save.");
        }
        StudyMethod studyMethod = readWriter.readFromFile("StudyMethod");

        assertEquals(studyMethod.toString(), methodChosen.toString());
    }
}
