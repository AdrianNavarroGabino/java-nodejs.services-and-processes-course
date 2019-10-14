// Adrián Navarro Gabino

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerView2 {

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        ScreenLoader.loadScreen("main.fxml",(Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
