// Adrián Navarro Gabino

package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

    public void calculate(ActionEvent actionEvent) {
        if(!num1.getText().equals("") && !num2.getText().equals(""))
        {
            double n1 = Double.parseDouble(num1.getText());
            double n2 = Double.parseDouble(num2.getText());

            switch (operation.getValue())
            {
                case "+":
                    result.setText(n1 + n2 + "");
                    break;
                case "-":
                    result.setText(n1 - n2 + "");
                    break;
                case "*":
                    result.setText(n1 * n2 + "");
                    break;
                case "/":
                    result.setText(n1 / n2 + "");
                    break;
            }
        }
        else
        {
            result.setText("0");
        }
    }
}

