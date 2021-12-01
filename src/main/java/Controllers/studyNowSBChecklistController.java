package Controllers;

import Entities.Checklist;
import Entities.StudyBlock;
import Entities.StudyMethod;
import UseCases.DataAccessInterface;
import UseCases.StudyBlockManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for all elements and pop-up windows for creating a StudyBlock from a chosen checklist.
 */
public class studyNowSBChecklistController {
    @FXML
    public TextField SBfromCheckListNameTextField;
    public TextField studyBlockLengthTextField;

    DataAccessInterface Data = MainGUI.Data;

    /**
     * private variables for the ListView that display created StudyBlocks.
     */
    @FXML
    private ListView<Checklist> listView = new ListView<>();
    private List<Checklist> checkListArrayList = new ArrayList<>();
    private ObservableList<Checklist> observableCheckList = FXCollections.observableArrayList();

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
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() {
        setListView();
    }

    /**
     * Saves the created StudyBlock from chosen Checklist and adds it to StudyBlocklist.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating main-view.fxml
     */
    @FXML
    public void saveSBfromCheckList(ActionEvent actionEvent) throws IOException {
        String name = SBfromCheckListNameTextField.getText();
        String length = studyBlockLengthTextField.getText();
        StudyMethod method = Data.getStudyMethod();
        // bellow gets the chosen checklist
        Checklist list = listView.getSelectionModel().getSelectedItem();
        StudyBlock studyBlock= StudyBlockManager.createStudyBlock(name, method, list, length);
        Data.addToStudyBlockList(studyBlock);
        changeSceneToBlockManagerButton(actionEvent);
    }

    /**
     * Changes scene back to BlockManager.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating block-manager-view.fxml
     */
    @FXML
    protected void changeSceneToBlockManagerButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
