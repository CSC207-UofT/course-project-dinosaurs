package Controllers;

import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;
import UseCases.DataAccessInterface;
import UseCases.StudyBlockManager;
import UseCases.TempCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for all elements and pop-up windows for creating a StudyBlock with a chosen priority.
 */
public class StudyNowStudyBlockPriorityView {

    public TextField studyBlockLengthTextField;
    public ChoiceBox<String> choosePriority = new ChoiceBox<>();
    public TextField SBPriorityNameTextField;

    DataAccessInterface Data = MainGUI.Data;

    /**
     * Saves the created StudyBlock with a chosen priority using a temp Checklist containing all tasks
     * and adds it to StudyBlocklist.
     * @param actionEvent on click
     */
    @FXML
    public void savePriorityStudyBlock(ActionEvent actionEvent) {
        String name = SBPriorityNameTextField.getText();
        String length = studyBlockLengthTextField.getText();
        StudyMethod method = Data.getStudyMethod();
        // bellow chooses the priority
        String priority = choosePriority.getValue();
        // creates the temporary checklist
        Checklist checkList = TempCreator.createTemp( "All Tasks", Data.getChecklistList(), priority);

        StudyBlock studyBlock= StudyBlockManager.createStudyBlock(name, method, checkList, length);
        Data.addToStudyBlockList(studyBlock);
        changeSceneToBlockManagerButton(actionEvent);
    }

    /**
     * Initializes Checkboxview.
     */
    @FXML
    void initialize() {
        checkboxView();
        choosePriority.setValue("Priority");
    }

    /**
     * Adds all priorities to checkboxView.
     */
    @FXML
    protected void checkboxView() {

        choosePriority.getItems().add("DUE_DATE");
        choosePriority.getItems().add("LENGTH");
        choosePriority.getItems().add("IMPORTANCE");
        choosePriority.getItems().add("WEIGHT");
    }

    /**
     * Changes scene back to BlockManager.
     * @param actionEvent on click
     */
    @FXML
    public void changeSceneToBlockManagerButton(ActionEvent actionEvent){
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
