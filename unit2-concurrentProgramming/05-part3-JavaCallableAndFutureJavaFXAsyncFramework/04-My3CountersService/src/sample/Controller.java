// Adrián Navarro Gabino

package sample;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
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

    private Service<Void> from1To10Service;
    private Service<Void> from1To5Service;
    private Service<Void> from10To1Service;

    public void initialize()
    {
        from1To10Service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        int current = 1;

                        do {
                            try
                            {
                                Thread.sleep(1000);
                                updateMessage("Counting... " + current);
                            }
                            catch(Exception e) {}
                            current++;
                        } while(current <= 10);

                        return null;
                    }
                };
            }
        };

        from1To5Service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        int current = 1;

                        do {
                            try
                            {
                                Thread.sleep(1000);
                                updateMessage("Counting... " + current);
                            }
                            catch(Exception e) {}
                            current++;
                        } while(current <= 5);

                        return null;
                    }
                };
            }
        };

        from10To1Service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        int current = 10;

                        do {
                            try
                            {
                                Thread.sleep(1000);
                                updateMessage("Counting... " + current);
                            }
                            catch(Exception e) {}
                            current--;
                        } while(current >= 1);

                        return null;
                    }
                };
            }
        };

        from1to10Lbl.textProperty().bind(from1To10Service.messageProperty());
        from1to5Lbl.textProperty().bind(from1To5Service.messageProperty());
        from10to1Lbl.textProperty().bind(from10To1Service.messageProperty());

        from1To10Service.setOnSucceeded(eh -> {
            from1to10Btn.setDisable(false);
            from1To10Service.reset();
        });
        from1To5Service.setOnSucceeded(eh -> {
            from1to5Btn.setDisable(false);
            from1To5Service.reset();
        });
        from10To1Service.setOnSucceeded(eh -> {
            from10to1Btn.setDisable(false);
            from10To1Service.reset();
        });
    }

    @FXML
    void from1To10(ActionEvent event) {
        from1to10Btn.setDisable(true);
        from1To10Service.start();
    }

    @FXML
    void from1To5(ActionEvent event) {
        from1to5Btn.setDisable(true);
        from1To5Service.start();
    }

    @FXML
    void from10To1(ActionEvent event) {
        from10to1Btn.setDisable(true);
        from10To1Service.start();
    }
}
