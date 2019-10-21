package flightsfx.utils;

import javafx.scene.control.Alert;

/**
 * <h1>Message Utils</h1>
 * Class to show messages.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class MessageUtils {
    /**
     * Shows an error message.
     * @param message Error message
     */
    public static void showError(String message)
    {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText("Error");
        dialog.setTitle("FlightsFX");
        dialog.setContentText(message);
        dialog.showAndWait();
    }

    /**
     * Shows an information message.
     * @param message Information message
     */
    public static void showMessage(String message)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Information");
        dialog.setTitle("FlightsFX");
        dialog.setContentText(message);
        dialog.showAndWait();
    }
}
