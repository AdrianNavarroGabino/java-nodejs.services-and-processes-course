// Adrián Navarro Gabino

package flightsfx;

import flightsfx.utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FlightsFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(
                getClass().getResource("FXMLMainView.fxml"));
        primaryStage.setTitle("FlightsFX");
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.getScene().getWindow().addEventFilter(
                WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        primaryStage.show();
    }

    private void closeWindowEvent(WindowEvent event) {
        FileUtils.saveFlights(FXMLMainViewController.getFlights());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
