// Adrián Navarro Gabino

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import sample.models.MyImage;
import sample.models.MyImageResponse;
import sample.services.PostImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea descriptionBox;

    @FXML
    private ImageView imageView;

    @FXML
    private Button chooseBtn;

    @FXML
    private TextField nameBox;

    @FXML
    private Button addBtn;

    @FXML
    private Label statusLbl;

    private File image = null;

    @FXML
    void add(ActionEvent event) {
        if(image == null) {
            statusLbl.setText("Image cannot be empty");
            return;
        }

        if(nameBox.getText().isEmpty() || descriptionBox.getText().isEmpty()) {
            statusLbl.setText("Fields cannot be empty");
            return;
        }

        MyImage myImage = new MyImage(
                image.toPath(), nameBox.getText(), descriptionBox.getText());
        PostImage post = new PostImage(myImage);
        post.start();

        post.setOnSucceeded((e) -> {
            MyImageResponse response = post.getValue();
            statusLbl.setText(response.getError());
            if(response.isOk()) {
                statusLbl.setText("Image has been uploaded");
                imageView.setImage(null);
                nameBox.setText("");
                descriptionBox.setText("");
                image = null;
            }
        });
    }

    @FXML
    void choose(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        image = fc.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
        if(image != null &&
                (image.getName().endsWith(".jpg") ||
                        image.getName().endsWith(".jpeg") ||
                        image.getName().endsWith(".png"))) {
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image(new FileInputStream(image)));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
