package messagesfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>MessagesFX</h1>
 * Main class of the application.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Overrides start method to show the main view.
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.getIcons().add(new Image("/messagesfx/icon.png"));
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
