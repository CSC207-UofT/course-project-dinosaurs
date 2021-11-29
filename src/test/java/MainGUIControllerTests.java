import Entities.Task;
import org.junit.*;
import Controllers.MainGUIController;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MainGUIControllerTests {

    @Test(timeout = 80)
    public void TestCreateChecklistFolder() {
        MainGUIController.createChecklistFolder();
        assert MainGUIController.checklistFolderExists();
    }

    @Test(timeout = 80)
    public void TestCreateStudyBlockFolder() {
        MainGUIController.createStudyBlockFolder();
        assert MainGUIController.studyBlockFolderExists();
    }
}

