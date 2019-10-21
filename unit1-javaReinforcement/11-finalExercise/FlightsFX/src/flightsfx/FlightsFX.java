package flightsfx;

import flightsfx.utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * <h1>FlightsFX</h1>
 * Main class of the application.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class FlightsFX extends Application {

    /**
     * Overrides start method to show the main view.
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(
                getClass().getResource("FXMLMainView.fxml"));
        primaryStage.setTitle("FlightsFX");
        primaryStage.setScene(new Scene(root, 800, 650));
        primaryStage.getIcons().add(new Image("/flightsfx/icon.png"));
        primaryStage.getScene().getWindow().addEventFilter(
                WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        primaryStage.show();
    }

    /**
     * Save flights list in a txt file at closing the window.
     * @param event Window event
     */
    private void closeWindowEvent(WindowEvent event) {
        FileUtils.saveFlights(FXMLMainViewController.getFlights());
    }

    /**
     * Main method. Launches the application.
     * @param args Console args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
