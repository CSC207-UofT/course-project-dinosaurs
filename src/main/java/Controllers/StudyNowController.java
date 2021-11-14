package Controllers;

import Entities.Checklist;
import HelperFunctions.ChecklistReadWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Controller for all elements and pop-up windows for the Preferences menu.
 */
public class StudyNowController {

    /**
     * Instance variables for use by ListView.
     */
    @FXML
    private ListView<String> listView;
    private List<String> stringList = new ArrayList<>();
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private Desktop desktop = Desktop.getDesktop();

    /**
     * Opens FileChooser to select checklists on initialization. Preliminary function.
     * TODO When you hit study now, the file opener opens again. Possibly needs to add another button.
     */
    @FXML
    protected void getChecklists() throws ClassNotFoundException, IOException{
        // I don't understand how these stages work, but the function requires one so here it is
        Stage mainStage = new Stage();

        final FileChooser fileChooser = new FileChooser();
        ArrayList<Checklist> checklists = new ArrayList<>();


        ChecklistReadWriter readWriter = new ChecklistReadWriter();

        // Helper function to specify current directory where things are being saved.
        configureFileChooser(fileChooser);
        List<File> list = fileChooser.showOpenMultipleDialog(mainStage);
        if (list != null) {
            for (File file : list) {

                // Deserializes files.
                Checklist checklist = readWriter.readFromFile(file.getPath());
                checklists.add(checklist);
            }
        }
        for (Checklist checklist : checklists) {
            stringList.add(checklist.name);
        }
        observableList.setAll(stringList);

        listView.setItems(observableList);
    }


    /**
     * Adds all Tasks to stringList and creates an observable list to display them in
     * ListView.
     * TODO I left this in here for now but I assume it will eventually be replaced/integrated
     * into the getChecklists function.
     */
    @FXML
    protected void setListView() {
        // This is where we should iterate through the current checklist and get all task strings
        // Add each task to stringList
        // observableList should keep the ListView up to date, but if not call setListView() again
        stringList.add("Checklist 1");
        stringList.add("Checklist 2");
        stringList.add("Checklist 3");
        stringList.add("Checklist 4");
        stringList.add("Checklist 5");

        observableList.setAll(stringList);

        listView.setItems(observableList);
    }

    /**
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() throws ClassNotFoundException, IOException{
        getChecklists();
    }

    /**
     * Opens a pop-up window showing the newly created Study Block with options to Save, Export,
     * and Delete.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-view.fxml
     */
    @FXML
    protected void openStudyBlockPopUp(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent newStudyBlockParent = FXMLLoader.load(getClass().getResource("studynow-studyblock-view.fxml"));
        Scene newStudyBlockScene = new Scene(newStudyBlockParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Your New Study Block");
        popUpWindow.setScene(newStudyBlockScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

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
     * Helper function to configure the file chooser to the current directory. user.dir specifies
     * the current directory. Subject to change.
     * @param fileChooser the fileChooser which is being initialized.
     */
    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Get Checklists");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.dir"))
        );
    }

}
