// Adrián Navarro Gabino

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    void goToView1(ActionEvent event) throws IOException {
        ScreenLoader.loadScreen("view1.fxml",(Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    void goToView2(ActionEvent event) throws IOException {
        ScreenLoader.loadScreen("view2.fxml",(Stage) ((Node) event.getSource()).getScene().getWindow());
    }
}
