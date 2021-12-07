package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Creates a pop-up when an exception occurs in the program.
 * Basic implementation that could be expanded in future.
 */
public class ExceptionController {

    /**
     * Displays the exception with a pop-up window on whichever stage was displayed at the time.
     * @param stage the stage where the exception occurred.
     */
    @FXML
    public static void exceptionPopUp(Stage stage) {
        // Loads FXML file and creates a new Scene
        try {
            Parent exceptionParent = FXMLLoader.load(Objects.requireNonNull
                    (ExceptionController.class.getResource("exception-popup-view.fxml")));
            Scene exceptionScene = new Scene(exceptionParent);

            Stage popUpWindow = new Stage();
            popUpWindow.setTitle("An Exception has Occurred");
            popUpWindow.setScene(exceptionScene);

            popUpWindow.initModality(Modality.WINDOW_MODAL);
            popUpWindow.initOwner(stage);

            popUpWindow.setX(stage.getX() + 50);
            popUpWindow.setY(stage.getY() + 50);

            popUpWindow.show();
        } catch(IOException e) {
            System.out.println("exception-popup-view.fxml not found");
        }
    }

    /**
     * Closes the pop-up.
     * @param actionEvent on click.
     */
    public void closeWindowButton(ActionEvent actionEvent) {
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
