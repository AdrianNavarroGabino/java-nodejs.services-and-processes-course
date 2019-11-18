package linktracker.utils;

import javafx.scene.control.Alert;

public class MessageUtils {
    public static void showError(String title, String message)
    {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText("Error");
        dialog.setTitle(title);
        dialog.setContentText(message);
        dialog.showAndWait();
    }

    public static void showMessage(String title, String message)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Information");
        dialog.setTitle(title);
        dialog.setContentText(message);
        dialog.showAndWait();
    }
}
