package Controllers;

import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import Infrastructure.ChecklistReadWriter;
import Infrastructure.PreferencesReadWriter;
import Infrastructure.StudyBlockReadWriter;
import UseCases.DataAccessInterface;
import Entities.StudyBlock;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller for all buttons and pop-up windows for the Main Menu.
 */
public class MainGUIController {

    public Label checklistName;
    public Label studyBlockName;
    /**
     * private variables for the ListView that displays Tasks for each loaded Checklist
     */
    @FXML
    private ListView<String> checklistView = new ListView<>();
    private final List<String> checklistStringList = new ArrayList<>();
    private final ObservableList<String> checklistObservableList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> studyBlockView = new ListView<>();
    private final List<String> studyBlockStringList = new ArrayList<>();
    private final ObservableList<String> studyBlockObservableList = FXCollections.observableArrayList();

    private final DataAccessInterface Data = MainGUI.Data;

    /**
     * Adds all checklists to the ListView.
     */
    @FXML
    protected void setChecklistView() {
        // This is where we should iterate through the current checklist and get all task strings
        // Add each task to stringList
        // observableList should keep the ListView up to date, but if not call setListView() again
        if (Data.getChecklistListSize() > 0) {
            for (Task task : Data.getChecklistList().get(Data.getChecklistListIndex())) {
                checklistStringList.add(task.toString());
            }
            checklistName.setText(Data.getChecklistList().get(Data.getChecklistListIndex()).name);
        }

        checklistObservableList.setAll(checklistStringList);

        checklistView.setItems(checklistObservableList);
    }

    /**
     * Adds all StudyBlock to the ListView.
     */
    @FXML
    protected void setStudyBlockView() {
        // This is where we should iterate through the current checklist and get all task strings
        // Add each task to stringList
        // observableList should keep the ListView up to date, but if not call setListView() again
        if (Data.getStudyBlockListSize() > 0) {
            studyBlockStringList.add(Data.getStudyBlockList().get(Data.getStudyBlockListIndex()).toString());
            studyBlockName.setText(Data.getStudyBlockList().get(Data.getStudyBlockListIndex()).name);
        }


        studyBlockObservableList.setAll(studyBlockStringList);

        studyBlockView.setItems(studyBlockObservableList);
    }

    /**
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() {
        if (!MainGUI.loaded) {
            loadAll();
            MainGUI.loaded = true;
        }
        setChecklistView();
        setStudyBlockView();
    }


    /**
     * Loads all checklists from folder "Checklists" and all study blocks from folder
     * "StudyBlocks". Loads saved StudyMethod from folder "StudyMethod".
     */
    private void loadAll() {
        try {
            if (checklistFolderExists()) {
                ChecklistReadWriter checklistReadWriter = new ChecklistReadWriter();
                File checklistDir = new File(System.getProperty("user.dir") + "//Checklists");
                File[] checklists = checklistDir.listFiles();
                if (checklists != null) {
                    for (File file : checklists) {

                        // Deserializes files.
                        Checklist checklist = checklistReadWriter.readFromFile(file.getPath());
                        Data.addToChecklistList(checklist);
                    }
                }
            }
            if (studyBlockFolderExists()) {
                StudyBlockReadWriter studyBlockReadWriter = new StudyBlockReadWriter();
                File studyBlockDir = new File(System.getProperty("user.dir") + "//StudyBlocks");
                File[] studyBlocks = studyBlockDir.listFiles();
                if (studyBlocks != null) {
                    for (File file : studyBlocks) {

                        // Deserializes files.
                        StudyBlock studyBlock = studyBlockReadWriter.readFromFile(file.getPath());
                        Data.addToStudyBlockList(studyBlock);
                    }
                }
            }
            if (preferencesFolderExists()) {
                PreferencesReadWriter preferencesReadWriter = new PreferencesReadWriter();
                File preferencesDir = new File(System.getProperty("user.dir") + "//Preferences");
                File[] preferences = preferencesDir.listFiles();
                if (preferences != null) {
                    for (File file : preferences) {

                        // Deserializes files.
                        StudyMethod studyMethod = preferencesReadWriter.readFromFile(file.getPath());
                        Data.setStudyMethod(studyMethod);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Problem loading Checklist or Studyblock file");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem locating Checklist or Studyblock folder in current directory");
        }
    }

    /**
     * Changes scene to Study now.
     * @param actionEvent on click
     */
    @FXML
    protected void changeSceneToStudyNowButton(ActionEvent actionEvent) {
        try { // Loads FXML file and creates a new Scene
            Parent studyNowParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("study-now-view.fxml")));
            Scene studyNowScene = new Scene(studyNowParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(studyNowScene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("study-now-view.fxml not found");
        }
    }

    /**
     * Changes scene to Study Block Manager.
     * @param actionEvent on click
     */
    @FXML
    protected void changeSceneToBlockManagerButton(ActionEvent actionEvent) {
        try {
            // Loads FXML file and creates a new Scene
            Parent blockManagerParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("block-manager-view.fxml")));
            Scene blockManagerScene = new Scene(blockManagerParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(blockManagerScene);
            stage.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("block-manager-view.fxml not found");
        }
    }

    /**
     * Changes scene to Checklist Manager.
     * @param actionEvent on click
     */
    @FXML
    protected void changeSceneToChecklistManagerButton(ActionEvent actionEvent) {
        try {
            // Loads FXML file and creates a new Scene
            Parent checklistManagerParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("checklist-manager-view.fxml")));
            Scene checklistManagerScene = new Scene(checklistManagerParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(checklistManagerScene);
            stage.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("checklist-manager-view.fxml not found");
        }
    }

    /**
     * Changes scene to Preferences menu.
     * @param actionEvent on click
     */
    @FXML
    protected void changeSceneToPreferencesButton(ActionEvent actionEvent) {
        try {
            Parent preferencesParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("preferences-view.fxml")));
            Scene preferencesScene = new Scene(preferencesParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(preferencesScene);
            stage.show(); // Loads FXML file and creates a new Scene
        } catch(NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("preferences-view.fxml not found");
        }

    }

    /**
     * Creates a new stage to confirm exiting without saving data. It is owned
     * by the current stage, and must be dismissed before user can interact with
     * the rest of the program again.
     * @param actionEvent on click
     */
    @FXML
    protected void openExitConfirmWindowButton(ActionEvent actionEvent) {
        try {
            // Loads FXML file and creates a new Scene
            Parent saveConfirmParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("no-save-confirm-view.fxml")));
            Scene saveConfirmScene = new Scene(saveConfirmParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage popUpWindow = new Stage();
            popUpWindow.setTitle("Confirm Exit Without Saving");
            popUpWindow.setScene(saveConfirmScene);

            popUpWindow.initModality(Modality.WINDOW_MODAL);
            popUpWindow.initOwner(stage);

            popUpWindow.setX(stage.getX() + 50);
            popUpWindow.setY(stage.getY() + 50);

            popUpWindow.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("no-save-confirm-view.fxml not found");
        }
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
     */
    @FXML
    protected void openSaveAllButton(ActionEvent actionEvent) {
        try {// Loads FXML file and creates a new Scene
            Parent saveChecklistsParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("save-everything-view.fxml")));
            Scene saveChecklistsScene = new Scene(saveChecklistsParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage popUpWindow = new Stage();
            popUpWindow.setTitle("Confirm Save");
            popUpWindow.setScene(saveChecklistsScene);

            popUpWindow.initModality(Modality.WINDOW_MODAL);
            popUpWindow.initOwner(stage);

            popUpWindow.setX(stage.getX() + 50);
            popUpWindow.setY(stage.getY() + 50);

            popUpWindow.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("save-everything-view.fxml not found");
        }
    }

    /**
     * Checks if a "Checklists" folder has been saved in the current directory.
     * @return true if folder exists
     */
    public static boolean checklistFolderExists(){
        File currDir = new File(System.getProperty("user.dir"));
        File[] fileList = currDir.listFiles();
        if (fileList == null){
            return false;
        }
        for (File file : fileList){
            if (file.getName().equals("Checklists") & file.isDirectory()){
                return true;
            }
        }
        return false;
    }


    /**
     * Makes a new Checklist folder.
     */
    public static boolean createChecklistFolder(){
        File checklistFolder = new File("Checklists");
        return (checklistFolder.mkdir());
    }

    /**
     * Deletes all checklists in folder Checklists.
     */
    public static void deleteAllChecklists() {
        File checklistDir = new File(System.getProperty("user.dir") + "\\Checklists\\");
        File[] checklistFileList = checklistDir.listFiles();
        if (checklistFileList != null) {
            for (File file : checklistFileList) {
                if (file.delete()) {
                    System.out.println(file.getName() + " was overwritten successfully.");
                } else {
                    System.out.println("Failed to overwrite files.");
                }
            }
        }
    }

    /**
     * Saves all checklists to checklists folder in current directory.
     */
    public void saveAllChecklists() {
        ChecklistReadWriter checklistReadWriter = new ChecklistReadWriter();
        for (Checklist checklist : Data.getChecklistList()) {
            try {
                checklistReadWriter.saveToFile(System.getProperty("user.dir") + "\\Checklists\\" + checklist.name, checklist);
            } catch (IOException e) {
                System.out.println(checklist.name + " did not save.");
            }
        }
    }

    /**
     * Checks if a "StudyBlocks" folder has been saved in the current directory.
     * @return true if folder exists
     */
    public static boolean studyBlockFolderExists() {
        File currDir = new File(System.getProperty("user.dir"));
        File[] fileList = currDir.listFiles();
        if (fileList == null) {
            return false;
        }
        for (File file : fileList) {
            if (file.getName().equals("StudyBlocks") & file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a new StudyBlock folder.
     */
    public static boolean createStudyBlockFolder(){
        File studyBlockFolder = new File("StudyBlocks");
        return studyBlockFolder.mkdir();
    }

    /**
     * Deletes all studyblocks in studyblocks folder in current directory.
     */
    public static void deleteAllStudyBlocks() {
        File studyBlockDir = new File(System.getProperty("user.dir") + "\\StudyBlocks\\");
        File[] studyBlockFileList = studyBlockDir.listFiles();
        if (studyBlockFileList != null) {
            for (File file : studyBlockFileList) {
                if (file.delete()) {
                    System.out.println(file.getName() + " was overwritten successfully.");
                } else {
                    System.out.println("Failed to overwrite files.");
                }
            }
        }
    }

    /**
     * Saves all studyblocks to studyblocks folder in current directory.
     */
    public void saveAllStudyBlocks() {
        StudyBlockReadWriter studyBlockReadWriter = new StudyBlockReadWriter();
        for (StudyBlock studyBlock : Data.getStudyBlockList()){
            try {
                studyBlockReadWriter.saveToFile(System.getProperty("user.dir") + "\\StudyBlocks\\" + studyBlock.name, studyBlock);
            } catch (IOException e) {
                System.out.println(studyBlock.name + " did not save.");
            }
        }
    }

    /**
     * Checks if a "Preferences" folder has been saved in the current directory.
     * @return true if folder exists
     */
    public static boolean preferencesFolderExists() {
        File currDir = new File(System.getProperty("user.dir"));
        File[] fileList = currDir.listFiles();
        if (fileList == null) {
            return false;
        }
        for (File file : fileList) {
            if (file.getName().equals("Preferences") & file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes a new Preferences folder.
     */
    public static boolean createPreferencesFolder(){
        File preferencesFolder = new File("Preferences");
        return preferencesFolder.mkdir();
    }

    /**
     * Saves the studymethod to preferences folder in current directory.
     */
    public void savePreferences() {
        PreferencesReadWriter preferencesReadWriter = new PreferencesReadWriter();
        try {
            preferencesReadWriter.saveToFile(System.getProperty("user.dir") + "\\Preferences\\" + "StudyMethod", Data.getStudyMethod());
        } catch (IOException e) {
            System.out.println("StudyMethod did not save.");
        }
    }
    /**
     * Allows user to save current checklists.
     * @param actionEvent on click
     */
    @FXML
    protected void saveAllButton(ActionEvent actionEvent) {
        if (createChecklistFolder()){
            System.out.println("Checklist folder created!");
        }
        if (createStudyBlockFolder()){
            System.out.println("Study Block folder created!");
        }

        if (createPreferencesFolder()){
            System.out.println("Preferences folder created!");
        }

        deleteAllChecklists();
        deleteAllStudyBlocks();
        saveAllChecklists();
        saveAllStudyBlocks();
        savePreferences();

        try {
            // Loads FXML file and creates a new Scene
            Parent saveChecklistsParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("confirm-save-view.fxml")));
            Scene saveChecklistsScene = new Scene(saveChecklistsParent);

            // Casts the action event to obtain the Stage where the button was clicked
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage popUpWindow = new Stage();
            popUpWindow.setTitle("Save Confirmation");
            popUpWindow.setScene(saveChecklistsScene);

            popUpWindow.initModality(Modality.WINDOW_MODAL);
            popUpWindow.initOwner(stage);

            popUpWindow.setX(stage.getX() + 50);
            popUpWindow.setY(stage.getY() + 50);

            popUpWindow.show();
        } catch (NullPointerException | IOException e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            ExceptionController.exceptionPopUp(stage);
            System.out.println("confirm-save-view.fxml not found");
        }

    }

    /**
     * Move forward to next Checklist in the list
     */
    @FXML
    protected void cycleChecklistsForwardButton(){
        checklistObservableList.removeAll(checklistStringList);
        checklistStringList.clear();
        if (Data.getChecklistListIndex() < (Data.getChecklistListSize() - 1)) {
            Data.setChecklistListIndex(Data.getChecklistListIndex() + 1);
        } else {
            Data.setChecklistListIndex(0);
        }
        setChecklistView();
    }

    /**
     * Move backward to prior Checklist in the list
     */
    @FXML
    protected void cycleChecklistsBackwardButton(){
        checklistObservableList.removeAll(checklistStringList);
        checklistStringList.clear();
        if (Data.getChecklistListIndex() > 0) {
            Data.setChecklistListIndex(Data.getChecklistListIndex() - 1);
        } else {
            Data.setChecklistListIndex(Data.getChecklistListSize() - 1);
        }
        setChecklistView();

    }

    /**
     * Move forward to next StudyBlock in the list
     */
    @FXML
    protected void cycleStudyBlocksForwardButton(){
        studyBlockObservableList.removeAll(studyBlockStringList);
        studyBlockStringList.clear();
        if (Data.getStudyBlockListIndex() < (Data.getStudyBlockListSize() - 1)) {
            Data.setStudyBlockListIndex(Data.getStudyBlockListIndex() + 1);
        } else {
            Data.setStudyBlockListIndex(0);
        }
        setStudyBlockView();
    }

    /**
     * Move backward to prior StudyBlock in the list
     */
    @FXML
    protected void cycleStudyBlocksBackwardButton(){
        studyBlockObservableList.removeAll(studyBlockStringList);
        studyBlockStringList.clear();
        if (Data.getStudyBlockListIndex() > 0) {
            Data.setStudyBlockListIndex(Data.getStudyBlockListIndex() - 1);
        } else {
            Data.setStudyBlockListIndex((Data.getStudyBlockListSize() - 1));
        }
        setStudyBlockView();

    }

}
