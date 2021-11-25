package Controllers;

import Entities.Checklist;
import Entities.StudyMethod;
import Entities.Task;
import UseCases.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Constants.Constants.DUE_DATE;


/**
 * Controller for all elements and pop-up windows for the Preferences menu.
 */
public class StudyNowController {

    DataAccessInterface Data = MainGUI.Data;

    public TextField studyBlockLengthTextField;
    public TextField studyBlockNameTextField;
    /**
     * Instance variables for use by ListView.
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
    void initialize() {
        setListView();
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
        int length = Integer.parseInt(studyBlockLengthTextField.getText());
        StudyMethod method = Data.getStudyMethod();
        // todo get the checklist chosen, im not sure if the thing under takes the right one but i dont think so
//        Checklist checklist = Data.getChecklistList().get(Data.getChecklistListIndex());
        Checklist checklist = new Checklist("list");
        StudyBlock studyBlock = new StudyBlock(name, method, checklist, length);
        Data.addToStudyBlockList(studyBlock);
        changeSceneToBlockManagerButton(actionEvent);
    }


}
