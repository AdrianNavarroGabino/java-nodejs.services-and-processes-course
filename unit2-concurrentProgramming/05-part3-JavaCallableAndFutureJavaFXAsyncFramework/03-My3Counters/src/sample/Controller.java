package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button from1to10Btn;

    @FXML
    private Label from1to10Lbl;

    @FXML
    private Button from1to5Btn;

    @FXML
    private Label from1to5Lbl;

    @FXML
    private Button from10to1Btn;

    @FXML
    private Label from10to1Lbl;

    @FXML
    void from1To10(ActionEvent event) {
        from1to10Btn.setDisable(true);
        Thread thread = new Thread(() -> {
            int current = 1;
            int end = 10;
            do {
                try
                {
                    Thread.sleep(1000);
                    final int aux = current;
                    Platform.runLater(() -> from1to10Lbl.setText("Counting... " + aux));
                } catch (InterruptedException e) { }
                current++;
            } while(current <= end);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            Platform.runLater(() -> from1to10Lbl.setText(""));
            from1to10Btn.setDisable(false);
        });
        thread.start();
    }

    @FXML
    void from1To5(ActionEvent event) {
        from1to5Btn.setDisable(true);
        Thread thread = new Thread(() -> {
            int current = 1;
            int end = 5;
            do {
                try
                {
                    Thread.sleep(1000);
                    final int aux = current;
                    Platform.runLater(() -> from1to5Lbl.setText("Counting... " + aux));
                } catch (InterruptedException e) { }
                current++;
            } while(current <= end);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            Platform.runLater(() -> from1to5Lbl.setText(""));
            from1to5Btn.setDisable(false);
        });
        thread.start();
    }

    @FXML
    void from10To1(ActionEvent event) {
        from10to1Btn.setDisable(true);
        Thread thread = new Thread(() -> {
            int current = 10;
            int end = 1;
            do {
                try
                {
                    Thread.sleep(1000);
                    final int aux = current;
                    Platform.runLater(() -> from10to1Lbl.setText("Counting... " + aux));
                } catch (InterruptedException e) { }
                current--;
            } while(current >= end);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            Platform.runLater(() -> from10to1Lbl.setText(""));
            from10to1Btn.setDisable(false);
        });
        thread.start();
    }
}
