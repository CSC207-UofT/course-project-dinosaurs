package Controllers;

import Constants.DueDateSingleton;
import Constants.ImportanceSingleton;
import Constants.LengthSingleton;
import Constants.WeightSingleton;
import Entities.Checklist;
import Entities.Task;
import UseCases.DataAccessInterface;
import UseCases.TaskManager;
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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Controller for all elements and pop-ups windows in the Checklist Manager scene.
 */
public class ChecklistManagerController {

    DataAccessInterface Data = MainGUI.Data;

    /**
     * Used to get user input for adding new Tasks
     */
    public TextField importanceTextField;
    public TextField weightTextField;
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
     * Instance variables for use by ListView for the Incomplete Tasks.
     */
    @FXML
    private ListView<String> listView;
    private final List<String> stringList = new ArrayList<>();
    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    /**
     * Instance variables for use by ListView for the Complete Tasks.
     */
    @FXML
    private ListView<String> completedListView;
    private List<String> completedStringList = new ArrayList<>();
    private ObservableList<String> completedObservableList = FXCollections.observableArrayList();

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
            for (Task task : Data.getChecklistList().get(Data.getChecklistListIndex()).incomplete) {
                stringList.add(task.toString());
            }
            checklistTitle.setText(Data.getChecklistList().get(Data.getChecklistListIndex()).name);

            for (Task compTask : Data.getChecklistList().get(Data.getChecklistListIndex()).complete) {
                completedStringList.add(compTask.toString());
            }
           observableList.setAll(stringList);
           listView.setItems(observableList);

           completedObservableList.setAll(completedStringList);
           completedListView.setItems(completedObservableList);
        } else {
            observableList.clear();
            stringList.clear();
            checklistTitle.setText("Example Checklist");
        }
    }

    /**
     * Resets the list view to empty.
     */
    @FXML
    protected void resetListView() {
        observableList.clear();
        stringList.clear();

        completedObservableList.clear();
        completedStringList.clear();
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
        Parent mainMenuParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
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
        Parent newTaskParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-task-view.fxml")));
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
        Parent newChecklistParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("create-checklist-view.fxml")));
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
     * Creates a new stage for viewing previously completed Tasks that is owned by the
     * current stage, and must be dismissed before user can interact with the rest
     * of the program again.
     *
     * @param actionEvent on click
     * @throws IOException if there is an issue locating create-checklist-view.fxml
     */
    @FXML
    protected void openCompletedTasksPopUp(ActionEvent actionEvent) throws IOException {
        // Loads FXML file and creates a new Scene
        Parent newChecklistParent = FXMLLoader.load(getClass().getResource("completed-task-view.fxml"));
        Scene newChecklistScene = new Scene(newChecklistParent);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage popUpWindow = new Stage();
        popUpWindow.setTitle("Completed Tasks");
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
     * Move forward to next Checklist in the list
     */
    @FXML
    protected void cycleCompletedChecklistsForwardButton() {
        completedObservableList.removeAll(completedStringList);
        completedStringList.clear();
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
    protected void cycleCompletedChecklistsBackwardButton() {
        completedObservableList.removeAll(completedStringList);
        completedStringList.clear();
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
        String weight = weightTextField.getText();
        String importance = importanceTextField.getText();
        String length = lengthTextField.getText();
        TaskManager taskManager = new TaskManager();
        Task newTask = taskManager.addTaskHelper(name, weight, dueDate, importance, length);
        taskManager.addTask(Data.getChecklistList().get(Data.getChecklistListIndex()), newTask);
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Removes the selected Task from the currently selected Checklist
     */
    @FXML
    protected void deleteSelectedTask() {
        TaskManager taskManager = new TaskManager();
        Task currTask = Data.getChecklistList().get(Data.getChecklistListIndex()).incomplete.get(listView.getFocusModel().getFocusedIndex());
        taskManager.removeTask(Data.getChecklistList().get(Data.getChecklistListIndex()), currTask);
        resetListView();
        setListView();
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
    protected void deleteSelectedChecklist() {
        if (Data.getChecklistListSize() != 0) {
            Checklist currChecklist = Data.getChecklistList().get(Data.getChecklistListIndex());
            Data.getChecklistList().remove(currChecklist);
            Data.setChecklistListIndex(0);
            setListView();
        }

    }

    /**
     * Marks the selected Task complete.
     */
    @FXML
    protected void markTaskComplete() {
        Checklist currChecklist = Data.getChecklistList().get(Data.getChecklistListIndex());
        Task currTask = Data.getChecklistList().get(Data.getChecklistListIndex()).incomplete.get(listView.getFocusModel().getFocusedIndex());
        TaskManager taskManager = new TaskManager();
        taskManager.completeTask(currChecklist, currTask);
    }

    /**
     * Marks the selected Task incomplete.
     */
    @FXML
    protected void markTaskIncomplete() {
        Checklist currChecklist = Data.getChecklistList().get(Data.getChecklistListIndex());
        Task currTask = Data.getChecklistList().get(Data.getChecklistListIndex()).complete.get(completedListView.getFocusModel().getFocusedIndex());
        TaskManager taskManager = new TaskManager();
        taskManager.revertTask(currChecklist, currTask);
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