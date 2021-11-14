package Controllers;

import Entities.Task;
import UseCases.TaskManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    /**
     * Used to get user input for adding new Tasks
     */
    public TextField importanceTextField;
    public TextField weightTextField;
    public TextField nameTextField;
    public TextField lengthTextField;
    public DatePicker datePicker;

    public Label checklistTitle;

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
        if (Data.checklistList.size() > 0) {
            for (Task task : Data.checklistList.get(Data.checklistIndex)) {
                stringList.add(task.toString());
            }
        }

        observableList.setAll(stringList);
        checklistTitle.setText(Data.checklistList.get(Data.checklistIndex).name);
        listView.setItems(observableList);
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
     * Creates a new stage for adding new Tasks that is owned by the current stage, and must
     * be dismissed before user can interact with the rest of the program again.
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
     * @param actionEvent on click
     */
    @FXML
    protected void backButton(ActionEvent actionEvent) {
        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
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

    @FXML
    protected void addNewTask(ActionEvent actionEvent) {
        LocalDate dueDate = datePicker.getValue();
        String name = nameTextField.getText();
        String weight = weightTextField.getText();
        String importance = importanceTextField.getText();
        String length = lengthTextField.getText();
        TaskManager taskManager = new TaskManager();
        Task newTask = taskManager.addTaskHelper(name, weight, dueDate, importance, length);
        taskManager.addTask(Data.checklistList.get(Data.checklistIndex), newTask);

        // Casts the action event to obtain the Stage where the button was clicked
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() {
        setListView();
    }
}
