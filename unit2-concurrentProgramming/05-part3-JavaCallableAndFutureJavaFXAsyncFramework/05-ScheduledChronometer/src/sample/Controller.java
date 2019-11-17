// Adrián Navarro Gabino

package sample;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Controller {

    @FXML
    private TextField secondsBox;

    @FXML
    private Button startBtn;

    @FXML
    private Button pauseBtn;

    @FXML
    private Label resultLbl;

    private ScheduledService<Integer> scheduledService;
    private int seconds;

    public void initialize()
    {
        seconds = 0;
        pauseBtn.setDisable(true);

        scheduledService = new ScheduledService<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        seconds--;
                        return seconds;
                    }
                };
            }
        };

        scheduledService.setDelay(Duration.millis(1000));
        scheduledService.setPeriod(Duration.millis(1000));
        scheduledService.setOnSucceeded(eh -> {
            int scheduledValue = scheduledService.getValue();
            if(scheduledValue <= 0)
            {
                scheduledService.cancel();
                pauseBtn.setDisable(true);
                startBtn.setDisable(false);
            }
            resultLbl.setText(String.valueOf(scheduledValue));
        });
    }

    @FXML
    void pause(ActionEvent event) {
        if(scheduledService.isRunning())
        {
            pauseBtn.setText("Resume");
            scheduledService.cancel();
            scheduledService.reset();
        }
        else
        {
            pauseBtn.setText("Pause");
            startBtn.setDisable(true);
            pauseBtn.setDisable(false);
            scheduledService.restart();
        }
    }

    @FXML
    void start(ActionEvent event) {
        startBtn.setDisable(true);
        pauseBtn.setDisable(false);
        seconds = Integer.parseInt(secondsBox.getText());
        resultLbl.setText((String.valueOf(seconds)));
        scheduledService.restart();
    }
}