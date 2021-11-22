package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller for all elements and pop-up windows for the Preferences menu.
 */
public class StudyNowController {

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

    public void exportStudyBlock(ActionEvent actionEvent) throws IOException {
        ExportStudyBlockController export =  new ExportStudyBlockController();
        export.exportStudyBlock(actionEvent);
    }

//    public void addStudyBlock(ActionEvent actionEvent, String name, int length) {
//        addStudyBlockController add_sb =  new addStudyBlockController();
//        add_sb.addStudyBlock(actionEvent, name, length);
//    }

    public void addStudyBlock(ActionEvent actionEvent) {
        //tested
    }


    public void getSBLength(ActionEvent actionEvent) {

    }


    public void getSBName(ActionEvent actionEvent) {
    }
}
