// Adrián Navarro Gabino

package linktracker.utils;

import javafx.scene.control.Alert;

/**
 * <h1>Message Utils</h1>
 * Class to show messages.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class MessageUtils {
    /**
     * Shows an error message.
     * @param title Message title
     * @param message Error message
     */
    public static void showError(String title, String message)
    {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText("Error");
        dialog.setTitle(title);
        dialog.setContentText(message);
        dialog.showAndWait();
    }

    /**
     * Shows an information message.
     * @param title Message title
     * @param message Information message
     */
    public static void showMessage(String title, String message)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Information");
        dialog.setTitle(title);
        dialog.setContentText(message);
        dialog.showAndWait();
    }
}
