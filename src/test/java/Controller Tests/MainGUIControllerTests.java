import org.junit.*;
import Controllers.MainGUIController;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for MainGUIController functions.
 */
public class MainGUIControllerTests {

    /**
     * Tests to see if the checklist folder exists function works.
     */
    @Test
    public void TestChecklistFolderExists() {
        if (MainGUIController.checklistFolderExists()) {
            File currDir = new File(System.getProperty("user.dir") + "\\Checklists");
            assertEquals(currDir.getName(), "Checklists");
            assert (currDir.isDirectory());
        }
    }

    /**
     * Tests to see if the studyblock folder exists function works.
     */
    @Test
    public void TestStudyBlockFolderExists() {
        if (MainGUIController.studyBlockFolderExists()) {
            File currDir = new File(System.getProperty("user.dir") + "\\StudyBlocks");
            assertEquals(currDir.getName(), "StudyBlocks");
            assert (currDir.isDirectory());
        }
    }

    /**
     * Tests the creation of the checklist folder.
     */
    @Test(timeout = 80)
    public void TestCreateChecklistFolder() {
        MainGUIController.createChecklistFolder();
        assert MainGUIController.checklistFolderExists();
    }

    /**
     * Tests the creation of Study Block folder.
     */
    @Test(timeout = 80)
    public void TestCreateStudyBlockFolder() {
        MainGUIController.createStudyBlockFolder();
        assert MainGUIController.studyBlockFolderExists();
    }

    /**
     * Tests deletion of all study blocks.
     */
    @Test
    public void TestDeleteAllStudyBlocks() {
        if (MainGUIController.studyBlockFolderExists()) {
            MainGUIController.deleteAllStudyBlocks();
            File currDir = new File(System.getProperty("user.dir") + "\\StudyBlocks");
            File[] array = currDir.listFiles();
            assert array != null;
            assertEquals(array.length, 0);
        }
    }

    /**
     * Tests deletion of all checklists.
     */
    @Test
    public void TestDeleteAllChecklists() {
        if (MainGUIController.checklistFolderExists()) {
            MainGUIController.deleteAllChecklists();
            File currDir = new File(System.getProperty("user.dir") + "\\Checklists");
            File[] array = currDir.listFiles();
            assert array != null;
            assertEquals(array.length, 0);
        }
    }
}

