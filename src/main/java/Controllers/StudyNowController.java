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
import java.util.Objects;


/**
 * Controller for all elements and pop-up windows for the StudyNow view.
 */
public class StudyNowController {
    public Button selectedChecklist;
    public Button byPriority;
    /**
     * Instance variable to choose sorting priority.
     */
    @FXML
    public ChoiceBox<String> choosePriority = new ChoiceBox<>();
    /**
     * Instance variable to choose checklist.
     */
    @FXML
    private ListView<Checklist> studyNowListView = new ListView<>();
    @FXML
    private ListView<String> manageStudyBlockListView = new ListView<>();
    private List<String> checkListNamesArrayList = new ArrayList<>();
    private ObservableList<String> observableCheckListNames = FXCollections.observableArrayList();

    DataAccessInterface Data = MainGUI.Data;

    @FXML
    public TextField studyBlockLengthTextField;
    public TextField studyBlockNameTextField;

    /**
     * Instance variables for use by SetlistView, the listview in the study now view.
     */
    @FXML

    private ListView<Checklist> listView = new ListView<>();
    private final List<Checklist> checkListArrayList = new ArrayList<>();
    private final ObservableList<Checklist> observableCheckList = FXCollections.observableArrayList();

    /**
     * Adds all Tasks to stringList and creates an observable list to display them in
     * ListView.
     */
    @FXML
    protected void setStudyNowListView() {
        if (Data.getChecklistListSize() > 0) {
            checkListArrayList.addAll(Data.getChecklistList());
        }
        observableCheckList.setAll(checkListArrayList);
        studyNowListView.setItems(observableCheckList);
    }

    /**
     * Adds all priorities to checkboxView.
     */
    @FXML
    protected void checkboxView() {

        choosePriority.getItems().add("DUE_DATE");
        choosePriority.getItems().add("LENGTH");
        choosePriority.getItems().add("IMPORTANCE");
        choosePriority.getItems().add("WEIGHT");
    }

    /**
     * Initializes scene and populates setListView and checkboxview.
     */
    @FXML
    void initialize() {
        setManageStudyBlockListView();
        setStudyNowListView();
        checkboxView();
        choosePriority.setValue("Priority");
    }



    /**
     * Opens a pop-up window showing the newly created Study Block with options to Save or cancel
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-view.fxml
     */
    @FXML
    protected void openStudyBlockPopUp(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent newStudyBlockParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studynow-studyblock-view.fxml")));
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
        Parent mainMenuParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Scene mainMenuScene = new Scene(mainMenuParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Changes scene back to BlockManager.
     * @param actionEvent on click
     */
    @FXML
    protected void changeSceneToBlockManagerButton(ActionEvent actionEvent) {
        // Loads FXML file and creates a new Scene
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    /**
     * Adds all Tasks to stringList and creates an observable list to display them in
     * ListView.
     */
    @FXML
    protected void setManageStudyBlockListView() {
        manageStudyBlockListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if (Data.getChecklistListSize() > 0) {
            for (Checklist checklist : Data.getChecklistList()) {
                checkListNamesArrayList.add(checklist.name);
            }
        }
        observableCheckListNames.setAll(checkListNamesArrayList);
        manageStudyBlockListView.setItems(observableCheckListNames);
    }

    /**
     * Saves the created StudyBlock from one or more checklists and adds it to StudyBlocklist.
     * @param actionEvent on click
     */
    @FXML
    public void saveStudyBlock(ActionEvent actionEvent) {
        String name = studyBlockNameTextField.getText();
        String length = studyBlockLengthTextField.getText();
        StudyMethod method = Data.getStudyMethod();
        String priority = choosePriority.getValue();

        ObservableList<String> selectedItems =  manageStudyBlockListView.getSelectionModel().getSelectedItems();
        if (selectedItems.size() >1){
            ArrayList<Checklist> list = new ArrayList<>();
            for (String item: selectedItems){
                list.add(Data.getChecklistWithName(item));
            }
            Checklist checkList = TempCreator.createTemp( "Selected Checklists Tasks", list, priority);
            StudyBlock studyBlock= StudyBlockManager.createStudyBlock(name, method, checkList, length);
            Data.addToStudyBlockList(studyBlock);
        }else{
            String list = manageStudyBlockListView.getSelectionModel().getSelectedItem();
            Checklist checklist = Data.getChecklistWithName(list);
            StudyBlock studyblock= StudyBlockManager.createStudyBlock(name, method, checklist, length);
            Data.addToStudyBlockList(studyblock);
        }
        changeSceneToBlockManagerButton(actionEvent);}


    /**
     * Opens a pop-up window showing the option to create Study Block from a checklist
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-selected-Checklist-view.fxml
     */
    @FXML
    public void openStudyBlockCheckListPopUp(ActionEvent actionEvent) throws IOException {
        Parent newStudyBlockChecklistParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studynow-studyblock-selected-Checklist-view.fxml")));
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
        Parent newStudyBlockPriorityParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("studynow-studyblock-priority-view.fxml")));
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