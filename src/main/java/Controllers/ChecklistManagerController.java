package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChecklistManagerController {

    @FXML
    private ListView<String> listView;
    private List<String> stringList = new ArrayList<>();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    @FXML
    protected void setListView() {
        // This is where we should iterate through the current checklist and get all task strings
        // Add each task to stringList
        // observableList should keep the ListView up to date, but if not call setListView() again
        stringList.add("Task 1");
        stringList.add("Task 2");
        stringList.add("Task 3");
        stringList.add("Task 4");
        stringList.add("Task 5");
        stringList.add("Task 6");
        stringList.add("Task 7");
        stringList.add("Task 8");
        stringList.add("Task 9");
        stringList.add("Task 10");
        stringList.add("Task 11");
        stringList.add("Task 12");
        stringList.add("Task 13");
        stringList.add("Task 14");
        stringList.add("Task 15");

        observableList.setAll(stringList);

        listView.setItems(observableList);
    }

    @FXML
    protected void changeSceneToMainMenuButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }

    @FXML
    void initialize() {
        setListView();
    }
}
