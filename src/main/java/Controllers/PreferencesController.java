package Controllers;

import Entities.StudyBlock;
import UseCases.DataAccessInterface;
import UseCases.PreferencesManager;
import UseCases.StudyBlockManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for all buttons and pop-up windows for the Preferences menu.
 */
public class PreferencesController {

    private DataAccessInterface Data = MainGUI.Data;

    /**
     * Variables for displaying and receiving user input.
     */
    public Label currStudyMethod;
    public TextField newActiveTimeField;
    public TextField newBreakTimeField;

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

    /**
     * Opens the Change Study Method pop up window.
     * @param actionEvent on click
     * @throws IOException
     */
    @FXML
    protected void openChangeStudyMethodButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent changeStudyMethodParent = FXMLLoader.load(getClass().getResource("change-study-method-view.fxml"));
        Scene changeStudyMethodScene = new Scene(changeStudyMethodParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Change Study Method");
        popUpWindow.setScene(changeStudyMethodScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

    /**
     * Saves new Study Method preferences.
     */
    @FXML
    protected void setNewStudyMethodButton(ActionEvent actionEvent) {
        Data.setStudyMethod(PreferencesManager.changeStudyMethodHelper(newActiveTimeField.getText(), newBreakTimeField.getText()));

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Closes Change Study Method pop up window.
     */
    @FXML
    protected void closeChangeStudyMethodWindowButton(ActionEvent actionEvent) {
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        currStudyMethod.setText(Data.getStudyMethod().toString());
    }
}
