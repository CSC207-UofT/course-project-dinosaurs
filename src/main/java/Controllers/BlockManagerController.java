package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for all elements and pop-ups windows in the Study Block Manager scene.
 */
public class BlockManagerController {

    /**
     * Changes scene to Main Menu.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating main-view.fxml
     */
    @FXML
    protected void changeSceneToMainMenuButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(mainMenuScene);
        stage.show();
    }
    // TODO before opening popup we need a window that gets all parameters

    /**
     * Opens a pop-up window showing the newly created Study Block with options to Save, Export,
     * and Delete.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-view.fxml
     */
    @FXML
    protected void openStudyBlockPopUp(ActionEvent actionEvent) throws IOException {
        StudyNowController SNC = new StudyNowController();
        SNC.openStudyBlockPopUp(actionEvent);
    }

    /**
     * Exports StudyBlock
     * @param actionEvent on click
     * @throws IOException if there is an issue.
     */
    @FXML
    protected void exportStudyBlock(ActionEvent actionEvent) throws IOException {
        ExportStudyBlockController export =  new ExportStudyBlockController();
        export.exportStudyBlock(actionEvent);
    }
}

