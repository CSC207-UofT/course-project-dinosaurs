package Controllers;

import UseCases.DataAccessInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * The primary stage/window for the program's GUI.
 */
public class MainGUI extends Application {

    static DataAccessInterface Data = new DataAccess();
    static boolean loaded = false;

    /**
     * Launches the GUI.
     * @param args command line arguments (not supported)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the stage.
     * @param stage passed in automatically
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Study Planner");
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            System.out.println("Problem loading program: main-view.fxml not found");
        }
    }
}
