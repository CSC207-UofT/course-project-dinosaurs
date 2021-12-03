package Controllers;

import Entities.Checklist;
import Entities.Task;
import UseCases.TaskManager;
//import UseCases.TempCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.time.LocalDate;
//import static Constants.Constants.DUE_DATE;
//import static Controllers.Data.checklistList;
//import static Controllers.Data.method;

/**
 * Controller for exporting the studyBlock.
 */
public class ExportStudyBlockController {
    /**
     * Exports the studyBlock into an ics file.
     * @throws IOException if there is an issue exporting.
     */
    @FXML
    public void exportStudyBlock(ActionEvent actionEvent) throws IOException {
        Checklist tasks = new Checklist("Checklist");
        TaskManager tm = new TaskManager();
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = d1.plusDays(1);
        LocalDate d3 = d1.plusDays(2);
        LocalDate d4 = d1.plusDays(3);

        Task t1 = new Task("t1", 15, d3, 5, 25);
        Task t2 = new Task("t2", 35, d1, 4, 15);
        Task t3 = new Task("t3", 55, d4, 2, 7);
        Task t4 = new Task("t3", 75, d2, 3, 10);

        tm.addTask(tasks, t1);
        tm.addTask(tasks, t2);
        tm.addTask(tasks, t3);
        tm.addTask(tasks, t4);
        // Todo fix Bellow upon proper implementation
//        Checklist list = TempCreator.createTemp("examplename", checklistList, DUE_DATE);
        //Schedulable obj = new StudyBlock("StudyBlock", method, tasks, 75);
        //obj.writeICS();

// todo under works kind of
//        Parent exportSceneParent = FXMLLoader.load(getClass().getResource("block-manager-view.fxml"));
//        Scene exportScene = new Scene(exportSceneParent);
//        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        stage.setScene(exportScene);
//        FileChooser fileChooser = new FileChooser();
//        File selectedFile = fileChooser.showOpenDialog(stage);

//        int returnVal = sefil.showDialog(FileChooserDemo2.this, "Attach");

        // get the file selected
//        File file = fileChooser.showSaveDialog(stage);


}}
