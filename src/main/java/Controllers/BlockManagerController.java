package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BlockManagerController {
    @FXML
    protected void changeSceneToMainMenuButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent studyNowParent = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene studyNowScene = new Scene(studyNowParent);

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(studyNowScene);
        stage.show();
    }
}
