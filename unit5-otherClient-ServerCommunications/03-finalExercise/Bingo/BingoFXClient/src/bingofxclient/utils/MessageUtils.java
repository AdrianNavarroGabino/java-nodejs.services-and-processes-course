package bingofxclient.utils;

import javafx.scene.control.Alert;

/**
 * <h1>Message Utils</h1>
 * Class to show messages.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class MessageUtils {

    /**
     * Shows an IO error message.
     */
    public static void showIOError()
    {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText("Error");
        dialog.setTitle("IO Error");
        dialog.setContentText("The user couldn't connect to the server");
        dialog.showAndWait();
    }
}
