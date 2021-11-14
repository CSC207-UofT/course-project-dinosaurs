package Controllers;

import Entities.Checklist;
import UseCases.StudyBlock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for all buttons and pop-up windows for the Main Menu.
 */
public class MainGUIController {

    /**
     * List of Checklists (containing all Tasks) and List of StudyBlocks.
     * Read from memory when program starts, this is the central location
     * for all controllers to access and update. It is saved to disk when
     * the user chooses "Save and Exit".
     * @param studyBlockList list of all saved StudyBlocks
     * @param checklistList list of all saved Checklists
     */
    @FXML protected List<StudyBlock> studyBlockList = new ArrayList<StudyBlock>();
    @FXML protected List<Checklist> checklistList = new ArrayList<Checklist>();

    /**
     * Changes scene to Study now.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating study-now-view.fxml
     */
    @FXML
    protected void changeSceneToStudyNowButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("study-now-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }

    /**
     * Changes scene to Study Block Manager.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating block-manager-view.fxml
     */
    @FXML
    protected void changeSceneToBlockManagerButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent blockManagerParent = FXMLLoader.load(getClass().getResource("block-manager-view.fxml"));
        Scene blockManagerScene = new Scene(blockManagerParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(blockManagerScene);
        stage.show();
    }

    /**
     * Changes scene to Checklist Manager.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating checklist-manager-view.fxml
     */
    @FXML
    protected void changeSceneToChecklistManagerButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent checklistManagerParent = FXMLLoader.load(getClass().getResource("checklist-manager-view.fxml"));
        Scene checklistManagerScene = new Scene(checklistManagerParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(checklistManagerScene);
        stage.show();
    }

    /**
     * Changes scene to Preferences menu.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating preferences-view.fxml
     */
    @FXML
    protected void changeSceneToPreferencesButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent preferencesParent = FXMLLoader.load(getClass().getResource("preferences-view.fxml"));
        Scene preferencesScene = new Scene(preferencesParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(preferencesScene);
        stage.show();
    }

    /**
     * Creates a new stage to confirm exiting without saving data. It is owned
     * by the current stage, and must be dismissed before user can interact with
     * the rest of the program again.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating save-confirm-view.fxml
     */
    @FXML
    protected void openExitConfirmWindowButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent saveConfirmParent = FXMLLoader.load(getClass().getResource("save-confirm-view.fxml"));
        Scene saveConfirmScene = new Scene(saveConfirmParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Confirm Exit Without Saving");
        popUpWindow.setScene(saveConfirmScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

    /**
     * Closes the exiting without saving confirmation dialog.
     * @param actionEvent on click
     */
    @FXML
    protected void popUpCancelButton(ActionEvent actionEvent) {
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the program without saving data.
     */
    @FXML
    protected void closeProgramButton() {
        Platform.exit();
    }
}
