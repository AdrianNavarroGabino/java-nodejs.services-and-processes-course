package bingofxclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * <h1>Bingo FX Client</h1>
 * Main class of the application.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class BingoFXClient extends Application {

    /**
     * Overrides start method to show the main view.
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BingoClient.fxml"));
        primaryStage.setTitle("Bingo");
        primaryStage.getIcons().add(new Image("/bingofxclient/icon.png"));
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
