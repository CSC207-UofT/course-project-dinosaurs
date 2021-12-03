package Controllers;

import Entities.StudyBlock;
import UseCases.*;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller for all elements and pop-ups windows in the Study Block Manager scene.
 */
public class BlockManagerController {

    @FXML
    public Label sBlockName;

    /**
     * private variables for the ListView that display created StudyBlocks.
     */
    @FXML
    private ListView<String> sBlockView = new ListView<>();
    private final List<String> sBlockStringList = new ArrayList<>();
    private final ObservableList<String> sBlockObservableList = FXCollections.observableArrayList();


    private final DataAccessInterface Data = MainGUI.Data;

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
     * Opens a pop-up window showing the newly created Study Block with options to Save, Export,
     * and Delete.
     * @param actionEvent on click
     * @throws IOException if there is an issue locating studynow-studyblock-view.fxml
     */
    @FXML
    protected void openStudyBlockPopUp(ActionEvent actionEvent) throws IOException {
        StudyNowController SNC = new StudyNowController();
        SNC.openStudyBlockPopUp(actionEvent);
    }


    /**
     * Exports the selected StudyBlock
     * @throws IOException if there is an issue.
     */
    @FXML
    protected void exportStudyBlock() throws IOException {
        Schedulable currStudyBlock = Data.getStudyBlockList().get(Data.getStudyBlockListIndex());
        currStudyBlock.writeICS();
    }

    /**
     * Adds all created StudyBlocks to this ListView.
     */
    @FXML
    protected void setSBlockView() {
        if (Data.getStudyBlockListSize() > 0) {
            sBlockStringList.add(Data.getStudyBlockList().get(Data.getStudyBlockListIndex()).toString());

            sBlockName.setText(Data.getStudyBlockList().get(Data.getStudyBlockListIndex()).name);

            sBlockObservableList.setAll(sBlockStringList);

            sBlockView.setItems(sBlockObservableList);
        } else {
            sBlockObservableList.removeAll(sBlockStringList);
            sBlockStringList.clear();
            sBlockName.setText("Example Study Block");
        }
    }

    /**
     * Initializes scene and populates ListView.
     */
    @FXML
    void initialize() {
        setSBlockView();
    }

    /**
     * Moves forward to next StudyBlock in the list
     */
    @FXML
    protected void cycleStudyBlocksForwardButton(){
        sBlockObservableList.removeAll(sBlockStringList);
        sBlockStringList.clear();
        if (Data.getStudyBlockListIndex() < (Data.getStudyBlockListSize() - 1)) {
            Data.setStudyBlockListIndex(Data.getStudyBlockListIndex() + 1);
        } else {
            Data.setStudyBlockListIndex(0);
        }
        setSBlockView();
    }

    /**
     * Moves backward to prior StudyBlock in the list
     */
    @FXML
    protected void cycleStudyBlocksBackwardButton(){
        sBlockObservableList.removeAll(sBlockStringList);
        sBlockStringList.clear();
        if (Data.getStudyBlockListIndex() > 0) {
            Data.setStudyBlockListIndex(Data.getStudyBlockListIndex() - 1);
        } else {
            Data.setStudyBlockListIndex((Data.getStudyBlockListSize() - 1));
        }
        setSBlockView();
    }

    /**
     * Deletes the selected StudyBlock in the list
     */
    @FXML
    protected void deleteSelectedSB(){
        StudyBlock currsb = Data.getStudyBlockList().get(Data.getStudyBlockListIndex());
        Data.getStudyBlockList().remove(currsb);
        sBlockObservableList.removeAll(sBlockStringList);
        sBlockStringList.clear();
        setSBlockView();
    }

}
