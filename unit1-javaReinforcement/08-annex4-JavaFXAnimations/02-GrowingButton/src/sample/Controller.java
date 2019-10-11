// Adrián Navarro Gabino

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KeyFrame kf = new KeyFrame(Duration.millis(10), e -> {
            button.setPrefWidth(button.getPrefWidth() + 1);
            button.setPrefHeight(button.getPrefHeight() + 1);
        });

        Timeline t = new Timeline(kf);
        t.setCycleCount(200);

        button.setOnMouseEntered(ev -> {
            t.play();
        });

        button.setOnMouseExited(ev -> {
            t.stop();
            button.setPrefSize(100, 50);
        });
    }
}
