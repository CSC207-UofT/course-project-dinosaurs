package Controllers;

import Entities.Checklist;
import Entities.Task;
import HelperFunctions.ChecklistReadWriter;
import UseCases.ChecklistSaver;
import UseCases.StudyBlock;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for all buttons and pop-up windows for the Main Menu.
 */
public class MainGUIController {

    public Label checklistName;
    /**
     * private variables for the ListView that displays Tasks for each loaded Checklist
     */
    @FXML
    private ListView<String> listView = new ListView<>();
    private List<String> stringList = new ArrayList<>();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    /**
     * Adds all Tasks to stringList and creates an observable list to display them in
     * ListView.
     */
    @FXML
    protected void setListView() {
        // This is where we should iterate through the current checklist and get all task strings
        // Add each task to stringList
        // observableList should keep the ListView up to date, but if not call setListView() again
        if (Data.checklistList.size() > 0) {
            for (Task task : Data.checklistList.get(Data.checklistIndex)) {
                stringList.add(task.toString());
            }
        }

        observableList.setAll(stringList);
        checklistName.setText(Data.checklistList.get(Data.checklistIndex).name);
        listView.setItems(observableList);

    }


    /**
     * Opens FileChooser to select checklists on initialization. Preliminary function.
     *
     */
    @FXML
    protected void getChecklists() throws ClassNotFoundException, IOException{
        // I don't understand how these stages work, but the function requires one so here it is
        Stage mainStage = new Stage();

        final FileChooser fileChooser = new FileChooser();

        ChecklistReadWriter readWriter = new ChecklistReadWriter();

        // Helper function to specify current directory where things are being saved.
        configureFileChooser(fileChooser);
        List<File> list = fileChooser.showOpenMultipleDialog(mainStage);
        if (list != null) {
            for (File file : list) {

                // Deserializes files.
                Checklist checklist = readWriter.readFromFile(file.getPath());
                Data.checklistList.add(checklist);
            }
        }

    }

    /**
     * Helper function to configure the file chooser to the current directory. user.dir specifies
     * the current directory. Subject to change.
     * @param fileChooser the fileChooser which is being initialized.
     */
    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Select Checklists to Import");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.dir"))
        );
    }

    /**
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() throws ClassNotFoundException, IOException {
        if (!Data.checklistLoaded) {
            getChecklists();
        }
        Data.checklistLoaded = true;
        setListView();
    }

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

    /**
     * Creates a new stage to confirm saving all data. It is owned
     * by the current stage, and must be dismissed before user can interact with
     * the rest of the program again.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating save-confirm-view.fxml
     */
    @FXML
    protected void openSaveChecklistsButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent saveChecklistsParent = FXMLLoader.load(getClass().getResource("save-checklists-view.fxml"));
        Scene saveChecklistsScene = new Scene(saveChecklistsParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Confirm Save");
        popUpWindow.setScene(saveChecklistsScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

    /**
     * Allows user to save current checklists.
     * @param actionEvent on click
     * @throws IOException if there is an issue saving files.
     * TODO implement same for studyblocks.
     */
    @FXML
    protected void saveChecklistButton(ActionEvent actionEvent) throws IOException {
        for (Checklist checklist : Data.checklistList){
            new ChecklistSaver(checklist);
        }

        // Loads FXML file and creates a new Scene
        Parent saveChecklistsParent = FXMLLoader.load(getClass().getResource("confirm-save-checklists-view.fxml"));
        Scene saveChecklistsScene = new Scene(saveChecklistsParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Save Confirmation");
        popUpWindow.setScene(saveChecklistsScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();

    }

    @FXML
    protected void cycleChecklistsForwardButton(){
        observableList.removeAll(stringList);
        stringList.clear();
        if (Data.checklistIndex < (Data.checklistList.size() - 1)) {
            Data.checklistIndex += 1;
        } else {
            Data.checklistIndex = 0;
        }
        setListView();
    }

    @FXML
    protected void cycleChecklistsBackwardButton(){
        observableList.removeAll(stringList);
        stringList.clear();
        if (Data.checklistIndex > 0) {
            Data.checklistIndex -= 1;
        } else {
            Data.checklistIndex = (Data.checklistList.size() - 1);
        }
        setListView();

    }

}
