package messagesfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import messagesfx.models.responses.LoginResponse;
import messagesfx.models.User;
import messagesfx.services.PostLogin;
import messagesfx.services.ServiceUtils;
import messagesfx.utils.FileUtils;
import java.io.IOException;

/**
 * <h1>Login Controller</h1>
 * Manages the login view.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class LoginController {

    @FXML
    private TextField userBox;

    @FXML
    private PasswordField passwordBox;

    @FXML
    private Label statusLbl;

    private static LoginResponse response;

    @FXML
    private void login(ActionEvent event) {
        User user = new User(
                userBox.getText(), passwordBox.getText());
        PostLogin post = new PostLogin(user);
        post.start();

        post.setOnSucceeded((e) -> {
            response = post.getValue();

            if(!response.isOk())
            {
                statusLbl.setText(response.getError());
            }
            else {
                ServiceUtils.setToken(response.getToken());

                try {
                    FileUtils.loadScreen("/messagesfx/Dashboard.fxml",
                            (Stage) ((Node) event.getSource())
                                    .getScene().getWindow(),
                            700, 700, "Messages");
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void register(ActionEvent event) throws IOException {

        FileUtils.loadScreen("/messagesfx/Register.fxml",
                (Stage) ((Node) event.getSource())
                        .getScene().getWindow(),
                500, 250, "Register");
    }

    /**
     * Gets the login response.
     * @return Login response
     */
    public static LoginResponse getResponse() { return response; }
}
