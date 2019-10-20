// Adrián Navarro Gabino

package flightsfx.utils;

import javafx.scene.control.Alert;

public class MessageUtils {
    public static void showError(String message)
    {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText("Error");
        dialog.setTitle("FlightsFX");
        dialog.setContentText(message);
        dialog.showAndWait();
    }

    public static void showMessage(String message)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Information");
        dialog.setTitle("FlightsFX");
        dialog.setContentText(message);
        dialog.showAndWait();
    }
}
