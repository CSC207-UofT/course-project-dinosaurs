package Controllers;

import Constants.*;
import Entities.Checklist;
import Entities.Task;
import UseCases.DataAccessInterface;
import UseCases.TaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



/**
 * Controller for all elements and pop-ups windows in the Checklist Manager scene.
 */
public class ChecklistManagerController {

    DataAccessInterface Data = MainGUI.Data;

    /**
     * Used to get user input for adding new Tasks
     */
    public Slider importanceSlider;
    public Slider weightSlider;
    public TextField nameTextField;
    public TextField lengthTextField;
    public DatePicker datePicker;

    /**
     * Used to get user input for new Checklists
     */
    public Label checklistTitle;
    public TextField checklistNameField;

    /**
     * Setup for Checklist Sorting MenuButton
     */

    @FXML
    MenuItem menuItemDue;
    @FXML
    MenuItem menuItemLength;
    @FXML
    MenuItem menuItemImportance;
    @FXML
    MenuItem menuItemWeight;

    public MenuButton sortMenuButton;

    /**
     * Instance variables for use by ListView.
     */
    @FXML
    private ListView<String> listView;
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
        // observableList should keep the ListView up to date
        if (Data.getChecklistListSize() > 0) {
            for (Task task : Data.getChecklistList().get(Data.getChecklistListIndex())) {
                stringList.add(task.toString());
            }
            checklistTitle.setText(Data.getChecklistList().get(Data.getChecklistListIndex()).name);
        }

        observableList.setAll(stringList);

        listView.setItems(observableList);
    }

    @FXML
    protected void resetListView() {
        observableList.clear();
        stringList.clear();
    }

    /**
     * Changes scene to Main Menu.
     *
     * @param actionEvent on click
     * @throws IOException if there is an issue locating main-view.fxml
     */
    @FXML
    protected void changeSceneToMainMenuButton(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Creates a new stage for adding new Tasks that is owned by the current stage, and must
     * be dismissed before user can interact with the rest of the program again.
     *
     * @param actionEvent on click
     * @throws IOException if there is an issue locating add-task-view.fxml
     */
    @FXML
    protected void openAddTaskPopUp(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent newTaskParent = FXMLLoader.load(getClass().getResource("add-task-view.fxml"));
        Scene newTaskScene = new Scene(newTaskParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Add a Task");
        popUpWindow.setScene(newTaskScene);

        // Modality restricts user action to this stage
        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        // Offset by (50,50) to help differentiate the new window
        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

    /**
     * Creates a new stage for creating and naming Checklists that is owned by the
     * current stage, and must be dismissed before user can interact with the rest
     * of the program again.
     *
     * @param actionEvent on click
     * @throws IOException if there is an issue locating create-checklist-view.fxml
     */
    @FXML
    protected void openCreateChecklistPopUp(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent newChecklistParent = FXMLLoader.load(getClass().getResource("create-checklist-view.fxml"));
        Scene newChecklistScene = new Scene(newChecklistParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Create New Checklist");
        popUpWindow.setScene(newChecklistScene);

        // Modality restricts user action to this stage
        popUpWindow.initModality(Modality.WINDOW_MODAL);
        popUpWindow.initOwner(stage);

        // Offset by (50,50) to help differentiate the new window
        popUpWindow.setX(stage.getX() + 50);
        popUpWindow.setY(stage.getY() + 50);

        popUpWindow.show();
    }

    /**
     * Closes the pop-up window.
     *
     * @param actionEvent on click
     */
    @FXML
    protected void backButton(ActionEvent actionEvent) {
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Move forward to next Checklist in the list
     */
    @FXML
    protected void cycleChecklistsForwardButton() {
        observableList.removeAll(stringList);
        stringList.clear();
        if (Data.getChecklistListIndex() < (Data.getChecklistListSize() - 1)) {
            Data.setChecklistListIndex(Data.getChecklistListIndex() + 1);
        } else {
            Data.setChecklistListIndex(0);
        }
        setListView();
    }

    /**
     * Move backward to prior Checklist in the list
     */
    @FXML
    protected void cycleChecklistsBackwardButton() {
        observableList.removeAll(stringList);
        stringList.clear();
        if (Data.getChecklistListIndex() > 0) {
            Data.setChecklistListIndex(Data.getChecklistListIndex() - 1);
        } else {
            Data.setChecklistListIndex(Data.getChecklistListSize() - 1);
        }
        setListView();

    }

    /**
     * Creates a new Task from user input and adds it to the currently selected Checklist
     *
     * @param actionEvent on click
     */
    @FXML
    protected void addNewTask(ActionEvent actionEvent) {
        LocalDate dueDate = datePicker.getValue();
        String name = nameTextField.getText();
        double weight = weightSlider.getValue();
        double importance = importanceSlider.getValue();
        String length = lengthTextField.getText();
        TaskManager taskManager = new TaskManager();
        Task newTask = TaskManager.addTaskHelper(name, weight, dueDate, importance, length);
        taskManager.addTask(Data.getChecklistList().get(Data.getChecklistListIndex()), newTask);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Removes the selected Task from the currently selected Checklist
     * @param actionEvent on click
     */
    @FXML
    protected void deleteSelectedTask(ActionEvent actionEvent) {
        TaskManager taskManager = new TaskManager();
        Task currTask = Data.getChecklistList().get(Data.getChecklistListIndex()).incomplete.get(listView.getFocusModel().getFocusedIndex());
        taskManager.removeTask(Data.getChecklistList().get(Data.getChecklistListIndex()), currTask);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }

    /**
     * Creates a new Checklist named by user
     *
     * @param actionEvent on click
     */
    @FXML
    protected void createNewChecklist(ActionEvent actionEvent) {
        Checklist newChecklist = new Checklist(checklistNameField.getText());
        Data.addToChecklistList(newChecklist);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Removes the selected checklist
     */
    @FXML
    protected void deleteSelectedChecklist(){
        Checklist currChecklist = Data.getChecklistList().get(Data.getChecklistListIndex());
        Data.getChecklistList().remove(currChecklist);
    }

    /**
     * Changes how the Checklist sorting to by Due Date (Sooner to Later).
     */
    @FXML
    protected void sortChecklistButtonDue() {
        TaskManager taskManager = new TaskManager();
        taskManager.changePriority(Data.getChecklistFromList(Data.getChecklistListIndex()), DueDateSingleton.getInstance().getDueDate());
        resetListView();
        setListView();
    }

    /**
     * Changes how the Checklist sorting to by Length (Short to Long).
     */
    @FXML
    protected void sortChecklistButtonLength() {
        TaskManager taskManager = new TaskManager();
        taskManager.changePriority(Data.getChecklistFromList(Data.getChecklistListIndex()), LengthSingleton.getInstance().getLength());
        resetListView();
        setListView();
    }

    /**
     * Changes how the Checklist sorting to by Importance (High to Low).
     */
    @FXML
    protected void sortChecklistButtonImportance() {
        TaskManager taskManager = new TaskManager();
        taskManager.changePriority(Data.getChecklistFromList(Data.getChecklistListIndex()), ImportanceSingleton.getInstance().getImportance());
        resetListView();
        setListView();
    }

    /**
     * Changes how the Checklist sorting to by Weight (High to Low).
     */
    @FXML
    protected void sortChecklistButtonWeight() {
        TaskManager taskManager = new TaskManager();
        taskManager.changePriority(Data.getChecklistFromList(Data.getChecklistListIndex()), WeightSingleton.getInstance().getWeight());
        resetListView();
        setListView();
    }

//    @FXML
//    protected void deleteSelectedChecklist() {
//        Checklist currChecklist = Data.getChecklistList().get(Data.getChecklistListIndex());
//        Data.getChecklistList().remove(currChecklist);
//    }


    /**
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() {
        setListView();
    }

}