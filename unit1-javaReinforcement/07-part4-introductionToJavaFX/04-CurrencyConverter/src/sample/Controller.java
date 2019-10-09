// Adrián Navarro Gabino

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller
{
    private final double EUR2USD = 1.10;
    private final double EUR2GBP = 0.8;
    private final double USD2GBP = 0.7;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioMenuItem b1;

    @FXML
    private RadioMenuItem b2;

    @FXML
    private RadioMenuItem b3;

    @FXML
    private RadioMenuItem b4;

    @FXML
    private RadioMenuItem b5;

    @FXML
    private RadioMenuItem b6;

    @FXML
    private Label result;

    @FXML
    private TextField money;

    @FXML
    private Label coin;

    public void convert(KeyEvent keyEvent)
    {
        if(money.getText().equals(""))
        {
            coin.setText("");
            result.setText("");
        }
        else
        {
            try
            {
                RadioMenuItem rb = (RadioMenuItem) toggleGroup.getSelectedToggle();
                coin.setText(rb.getText());
                double auxMoney = Double.parseDouble(money.getText().replace(",","."));

                switch(rb.getText())
                {
                    case "EUR>USD":
                        result.setText(auxMoney + " EUR = " + String.format("%.2f", auxMoney * EUR2USD) + " USD");
                        break;
                    case "EUR>GBP":
                        result.setText(auxMoney + " EUR = " + String.format("%.2f", auxMoney * EUR2GBP) + " GBP");
                        break;
                    case "USD>EUR":
                        result.setText(auxMoney + " USD = " + String.format("%.2f", auxMoney / EUR2USD) + " EUR");
                        break;
                    case "USD>GBP":
                        result.setText(auxMoney + " USD = " + String.format("%.2f", auxMoney * USD2GBP) + " GBP");
                        break;
                    case "GBP>EUR":
                        result.setText(auxMoney + " GBP = " + String.format("%.2f", auxMoney / EUR2GBP) + " EUR");
                        break;
                    case "GBP>USD":
                        result.setText(auxMoney + " GBP = " + String.format("%.2f", auxMoney / USD2GBP) + " USD");
                        break;
                }
            }
            catch(Exception e)
            {
                coin.setText("");
                result.setText("");
            }
        }
    }

    public void clearOut(ActionEvent actionEvent)
    {
        money.setText("");
        coin.setText("");
        result.setText("");
    }
}
