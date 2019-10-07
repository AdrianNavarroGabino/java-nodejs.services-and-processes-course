package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ChoiceBox<String> operation;

    @FXML
    private TextField num1;

    @FXML
    private TextField num2;

    @FXML
    private Button goBtn;

    @FXML
    private TextField result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operation.setItems(FXCollections.observableArrayList("+", "-", "*", "/"));
    }
}
