package messagesfx.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * <h1>File Utils</h1>
 * Tools to use in the application.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class FileUtils {

    /**
     * Constant to indicate the host.
     */
    public static final String HOST = "http://localhost:8080/";

    /**
     * Loads a new view.
     * @param viewPath View path
     * @param stage Stage
     * @param width Scene width
     * @param height Scene height
     * @param title Scene title
     * @throws IOException Input/Output Exception
     */
    public static void loadScreen(String viewPath, Stage stage,
                                  int width, int height, String title)
            throws IOException {
        Parent view1 = FXMLLoader.load(FileUtils.class.getResource(viewPath));
        Scene view1Scene = new Scene(view1, width, height);
        stage.hide();
        stage.setTitle(title);
        stage.setScene(view1Scene);
        stage.show();
    }
}
