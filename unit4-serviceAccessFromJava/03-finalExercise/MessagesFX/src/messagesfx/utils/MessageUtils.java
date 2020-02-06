package messagesfx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * <h1>Message Utils</h1>
 * Class to show messages.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class MessageUtils {

    /**
     * Shows an confirmation message.
     * @param title Alert title
     * @param header Alert header
     * @param message Alert message
     * @return true when the user presses ok, false when the user presses cancel.
     */
    public static boolean showConfirmMessage(
            String title, String header, String message )
    {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setTitle(title);
        alert.setHeaderText( header );
        alert.setContentText( message );
        alert.getButtonTypes().setAll( ButtonType.OK, ButtonType.CANCEL );
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            return true;
        } else {
            alert.close();
            return false;
        }
    }
}
