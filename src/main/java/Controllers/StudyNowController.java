package Controllers;

import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;
import UseCases.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for all elements and pop-up windows for the StudyNow view.
 */
public class StudyNowController {
    DataAccessInterface Data = MainGUI.Data;

    @FXML
    public TextField studyBlockLengthTextField;
    public TextField studyBlockNameTextField;
    /**
     * Instance variables for use by SetlistView.
     */
    @FXML
    private ListView<Checklist> listView = new ListView<>();
    private List<Checklist> checkListArrayList = new ArrayList<>();
    private ObservableList<Checklist> observableCheckList = FXCollections.observableArrayList();

    /**
     * Instance variables for use by ChoiceBox for displaying Checklist choices.
     */
    @FXML
    public ChoiceBox chooseChecklist = new ChoiceBox();
    private ObservableList<Checklist> checklistObservableList = FXCollections.observableArrayList();
    private List <Checklist> listOfChecklists = new ArrayList<>();

    /**
     * Adds all Tasks to stringList and creates an observable list to display them in
     * ListView.
     */
    @FXML
    protected void setListView() {
        if (Data.getChecklistListSize() > 0) {
            checkListArrayList.addAll(Data.getChecklistList());
        }
        observableCheckList.setAll(checkListArrayList);
        listView.setItems(observableCheckList);
    }

    /**
     * Initializes scene and populates setListView and checkboxview.
     */
    @FXML
    void initialize() {
        setListView();
        checkboxView();
        chooseChecklist.setValue(checklistObservableList);
        chooseChecklist.setValue("Checklist");
    }
    /**
     * Adds all Tasks to stringList and creates an observable list to display them in
     * ListView.
     */
    @FXML
    protected void checkboxView() {
        for (Checklist checklist : Data.getChecklistList()){
            listOfChecklists.add(checklist);
        checklistObservableList.setAll(listOfChecklists);
        chooseChecklist.setItems(checklistObservableList);
        }
    }


    /**
     * Opens a pop-up window showing the newly created Study Block with options to Save or cancel
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
     * Changes scene back to BlockManager.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating block-manager-view.fxml
     */
    @FXML
    protected void changeSceneToBlockManagerButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Saves the created StudyBlock and adds it to StudyBlocklist.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating main-view.fxml
     */
    @FXML
    public void saveStudyBlock(ActionEvent actionEvent) throws IOException {
        String name = studyBlockNameTextField.getText();
        String length = studyBlockLengthTextField.getText();
        StudyMethod method = Data.getStudyMethod();
        // bellow gets the chosen checklist from the ChoiceBox
        Checklist list = (Checklist) chooseChecklist.getValue();
        StudyBlock studyBlock= StudyBlockManager.createStudyBlock(name, method, list, length);
        Data.addToStudyBlockList(studyBlock);
        changeSceneToBlockManagerButton(actionEvent);}


    /**
     * Opens a pop-up window showing the option to create Study Block from a checklist
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-selected-Checklist-view.fxml
     */
    @FXML
    public void openStudyBlockCheckListPopUp(ActionEvent actionEvent) throws IOException {
        Parent newStudyBlockChecklistParent = FXMLLoader.load(getClass().getResource("studynow-studyblock-selected-Checklist-view.fxml"));
        Scene newStudyBlockCheckListScene = new Scene(newStudyBlockChecklistParent);
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Your New Study Block From Selected Checklist");
        popUpWindow.setScene(newStudyBlockCheckListScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

    /**
     * Opens a pop-up window showing the option to create Study Block based on priority using a temp checklist
     * containing all tasks instead of a specific CheckList
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-priority-view.fxml
     */
    @FXML
    public void openStudyBlockPriorityPopUp(ActionEvent actionEvent) throws IOException {
        Parent newStudyBlockPriorityParent = FXMLLoader.load(getClass().getResource("studynow-studyblock-priority-view.fxml"));
        Scene newStudyBlockPriorityScene = new Scene(newStudyBlockPriorityParent);
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Your New Study Block by Priority");
        popUpWindow.setScene(newStudyBlockPriorityScene);

        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }
}
