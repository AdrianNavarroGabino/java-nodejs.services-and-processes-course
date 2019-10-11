// Adrián Navarro Gabino

package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Transition t = new TranslateTransition(Duration.millis(4000), name);
        ((TranslateTransition) t).setFromX(-200);
        ((TranslateTransition) t).setToX(400);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }
}
