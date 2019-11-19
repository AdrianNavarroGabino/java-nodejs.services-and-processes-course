// Adrián Navarro Gabino

package linktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>LinkTracker</h1>
 * Main class of the application.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class LinkTracker extends Application {

    /**
     * Overrides start method to show the main view.
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainView.fxml"));
        primaryStage.setTitle("Link Tracker");
        primaryStage.getIcons().add(new Image("/linktracker/icon.png"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Main method. Launches the application.
     * @param args Console args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
