// Adrián Navarro Gabino

package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenLoader {
    public static void loadScreen(String viewPath, Stage stage) throws IOException
    {
        // Load the view from the FXML file
        Parent view1 = FXMLLoader.load(ScreenLoader.class.getResource(viewPath));
        Scene view1Scene = new Scene(view1);
        stage.setScene(view1Scene);
        stage.show();
    }
}
