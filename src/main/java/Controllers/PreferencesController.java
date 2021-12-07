package Controllers;

import UseCases.DataAccessInterface;
import UseCases.PreferencesManager;
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
import java.util.Objects;

/**
 * Controller for all buttons and pop-up windows for the Preferences menu.
 */
public class PreferencesController {

    private final DataAccessInterface Data = MainGUI.Data;

    /**
     * Variables for displaying and receiving user input.
     */
    public Label currStudyMethod;
    public TextField newActiveTimeField;
    public TextField newBreakTimeField;

    /**
     * Changes scene to Main Menu.
     * @param actionEvent on click
     */
    @FXML
    protected void changeSceneToMainMenuButton(ActionEvent actionEvent) {
        try {
            // Loads FXML file and creates a new Scene
            Parent mainMenuParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
            Scene mainMenuScene = new Scene(mainMenuParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(mainMenuScene);
            stage.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("main-view.fxml not found");
        }
    }

    /**
     * Opens the Change Study Method pop up window.
     * @param actionEvent on click
     */
    @FXML
    protected void openChangeStudyMethodButton(ActionEvent actionEvent) {
        try {
            // Loads FXML file and creates a new Scene
            Parent changeStudyMethodParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("change-study-method-view.fxml")));
            Scene changeStudyMethodScene = new Scene(changeStudyMethodParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage popUpWindow = new Stage();
            popUpWindow.setTitle("Change Study Method");
            popUpWindow.setScene(changeStudyMethodScene);

            popUpWindow.initModality(Modality.WINDOW_MODAL);
            popUpWindow.initOwner(stage);

            popUpWindow.setX(stage.getX() + 50);
            popUpWindow.setY(stage.getY() + 50);

            popUpWindow.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("change-study-method-view.fxml not found");
        }
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
