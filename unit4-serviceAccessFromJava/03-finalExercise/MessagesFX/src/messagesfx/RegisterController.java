package messagesfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import messagesfx.models.User;
import messagesfx.models.responses.UserResponse;
import messagesfx.services.PostUser;
import messagesfx.utils.FileUtils;
import messagesfx.utils.MessageUtils;
import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

/**
 * <h1>Register Controller</h1>
 * Manages the register view.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class RegisterController {

    @FXML
    private ImageView photoImg;

    @FXML
    private TextField userBox;

    @FXML
    private PasswordField passBox;

    @FXML
    private PasswordField repeatBox;

    @FXML
    private Label statusLbl;

    private File image = null;

    @FXML
    private void cancel(ActionEvent event) throws IOException {

        goToLogin(event);
    }

    @FXML
    private void register(ActionEvent event) {
        if(image == null) {
            statusLbl.setText("Image cannot be empty");
            return;
        }

        if(userBox.getText().trim().isEmpty() || passBox.getText().trim().isEmpty() ||
                repeatBox.getText().trim().isEmpty()) {
            statusLbl.setText("Fields cannot be empty");
            return;
        }

        if(!passBox.getText().equals(repeatBox.getText()))
        {
            statusLbl.setText("Passwords don't match");
            return;
        }

        byte[] fileData;
        String data = "";
        try {
            fileData = Files.readAllBytes(image.toPath());
            data = Base64.getEncoder().encodeToString(fileData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        User user = new User(
                userBox.getText(), passBox.getText(), data);
        PostUser post = new PostUser(user);
        post.start();

        post.setOnSucceeded((e) -> {
            UserResponse response = post.getValue();

            if(!response.isOk())
            {
                statusLbl.setText(response.getError());
            }
            else {
                statusLbl.setText("");

                boolean ok = MessageUtils.showConfirmMessage(
                        "Register complete",
                        "You have succesfully registered",
                        "You will be redirected to the login page");
                if (ok){
                    try {
                        goToLogin(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    private void selectImage(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        image = fc.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        if(image != null &&
                (image.getName().endsWith(".jpg") ||
                        image.getName().endsWith(".jpeg") ||
                        image.getName().endsWith(".png"))) {
            photoImg.setPreserveRatio(true);
            photoImg.setImage(new Image(new FileInputStream(image)));
        }
    }

    private void goToLogin(ActionEvent event) throws IOException {

        FileUtils.loadScreen("/messagesfx/Login.fxml",
                (Stage) ((Node) event.getSource())
                        .getScene().getWindow(),
                400, 250, "Login");
    }
}