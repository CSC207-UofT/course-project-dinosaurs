package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUIController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void changeSceneToStudyNowButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("study-now-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }

    @FXML
    protected void changeSceneToBlockManagerButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("block-manager-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }

    @FXML
    protected void changeSceneToChecklistManagerButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("checklist-manager-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }

    @FXML
    protected void changeSceneToPreferencesButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("preferences-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }
}
