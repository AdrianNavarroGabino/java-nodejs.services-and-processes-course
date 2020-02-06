package messagesfx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import messagesfx.models.Message;
import messagesfx.models.User;
import messagesfx.models.responses.MessageResponse;
import messagesfx.models.responses.UserResponse;
import messagesfx.services.*;
import messagesfx.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

/**
 * <h1>Dashboard Controller</h1>
 * Manages the main view.
 * @author Adrián Navarro Gabino
 * @version 1.0
 */
public class DashboardController implements Initializable {

    private Message selectedMessage = null;
    private User selectedUser = null;
    private List<User> users = null;
    private User user = null;
    private String imageSend64 = null;

    @FXML
    private TableView<Message> messagesList;

    @FXML
    private TableColumn<Message, String> messageColumn;

    @FXML
    private TableColumn<Message, ImageView> imageColumn;

    @FXML
    private TableColumn<Message, LocalDate> sentColumn;

    @FXML
    private TableColumn<Message, ImageView> avatarMessages;

    @FXML
    private TableColumn<Message, String> nameMessages;

    @FXML
    private Label userLbl;

    @FXML
    private ImageView imageView;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<User> usersList;

    @FXML
    private TableColumn<User, ImageView> avatarColumn;

    @FXML
    private TableColumn<User, String> nicknameColumn;

    @FXML
    private ImageView imageSend;

    @FXML
    private TextField messageTF;

    /**
     * Initializes the FXML view.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userLbl.setText(LoginController.getResponse().getName());
        Image image = new Image(FileUtils.HOST +
                LoginController.getResponse().getImage());
        imageView.setImage(image);

        fillMessageTable();
        fillUsersTable();
    }

    @FXML
    private void changeImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File image = fc.showOpenDialog(((Node)event.getSource()).getScene()
                .getWindow());
        if(image != null &&
                (image.getName().endsWith(".jpg") ||
                        image.getName().endsWith(".jpeg") ||
                        image.getName().endsWith(".png"))) {

            byte[] fileData;
            String data = "";
            try {
                fileData = Files.readAllBytes(image.toPath());
                data = Base64.getEncoder().encodeToString(fileData);
                user.setImage(data);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            UpdateImage update = new UpdateImage(user);

            update.start();
            update.setOnSucceeded((e) -> {

                try {
                    UserResponse resp = update.getValue();

                    if(resp.isOk()) {
                        imageView.setPreserveRatio(true);
                        imageView.setImage(new Image(
                                new FileInputStream(image)));
                        fillUsersTable();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        fillMessageTable();
    }

    @FXML
    private void delete(ActionEvent event) {
        DeleteMessage delete = new DeleteMessage(selectedMessage.get_id());
        delete.start();

        delete.setOnSucceeded(e -> {
            MessageResponse resp = delete.getValue();
            if(resp.isOk()) {
                messagesList.getItems().remove(selectedMessage);
                deleteBtn.setDisable(true);
            }
        });
    }

    @FXML
    private void selectImageSend(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File image = fc.showOpenDialog(((Node)event.getSource()).getScene()
                .getWindow());
        if(image != null &&
                (image.getName().endsWith(".jpg") ||
                        image.getName().endsWith(".jpeg") ||
                        image.getName().endsWith(".png"))) {
            imageSend.setPreserveRatio(true);
            imageSend.setImage(new Image(new FileInputStream(image)));

            byte[] fileData;
            imageSend64 = "";
            try {
                fileData = Files.readAllBytes(image.toPath());
                imageSend64 = Base64.getEncoder().encodeToString(fileData);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void sendMessage(ActionEvent event) {
        if(!messageTF.getText().isEmpty() && selectedUser != null)
        {
            Message messageSend = new Message();
            messageSend.setTo(selectedUser.get_id());
            messageSend.setMessage(messageTF.getText());
            messageSend.setSent(LocalDate.now());

            if(imageSend64 != null)
            {
                messageSend.setImage(imageSend64);
            }

            messageTF.setText("");
            imageSend.setImage(null);
            imageSend64 = null;
            selectedUser = null;

            PostMessage post = new PostMessage(messageSend);
            post.start();
        }
    }

    private void fillMessageTable()
    {
        GetMessages getMessages = new GetMessages();

        messagesList.setPlaceholder(new Label("No messages available"));

        getMessages.start();
        getMessages.setOnSucceeded((e) -> {

            messageColumn.setCellValueFactory(
                    new PropertyValueFactory("message"));
            imageColumn.setCellValueFactory(
                    new PropertyValueFactory("imageView"));

            messagesList.setRowFactory(tv -> {
                TableRow<Message> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    selectedMessage = row.getItem();
                    deleteBtn.setDisable(false);
                });
                return row;
            });

            sentColumn.setCellValueFactory(
                    new PropertyValueFactory<Message, LocalDate>("sent"));

            avatarMessages.setCellValueFactory(
                    c -> new SimpleObjectProperty<ImageView>(
                            c.getValue().getFrom().getImageView()));
            nameMessages.setCellValueFactory(
                    c -> new SimpleStringProperty(
                            c.getValue().getFrom().getName()));

            List<Message> messages = getMessages.getValue().getMessages();
            messagesList.setItems(FXCollections.observableArrayList(
                    messages));
        });
    }

    private void fillUsersTable()
    {
        GetUsers getUsers = new GetUsers();

        getUsers.start();
        getUsers.setOnSucceeded((e) -> {
            usersList.setPlaceholder(new Label("No users available"));
            avatarColumn.setCellValueFactory(
                    new PropertyValueFactory("imageView"));
            nicknameColumn.setCellValueFactory(
                    new PropertyValueFactory("name"));

            usersList.setRowFactory(tv -> {
                TableRow<User> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    selectedUser = row.getItem();
                });
                return row;
            });

            users = getUsers.getValue().getUsers();
            usersList.setItems(FXCollections.observableArrayList(users));

            for(User u: users)
            {
                if(u.getName().equals(LoginController.getResponse().getName()))
                {
                    user = u;
                    break;
                }
            }
        });
    }
}
