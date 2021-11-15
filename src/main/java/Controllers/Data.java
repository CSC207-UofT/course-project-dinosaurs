package Controllers;

import Entities.Checklist;
import Entities.StudyMethod;
import UseCases.StudyBlock;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class Data {

    /**
     * List of Checklists (containing all Tasks) and List of StudyBlocks.
     * Read from memory when program starts, this is the central location
     * for all controllers to access and update. It is saved to disk when
     * the user chooses "Save and Exit".
     */
    @FXML static protected List<StudyBlock> studyBlockList = new ArrayList<>();
    @FXML static protected ArrayList<Checklist> checklistList = new ArrayList<>();
    @FXML static protected boolean checklistLoaded = false;
    @FXML static protected StudyMethod method = new StudyMethod(StudyMethod.POMODORO);
    @FXML static protected int checklistIndex = 0;
    @FXML static protected int studyBlockListIndex = 0;

}
