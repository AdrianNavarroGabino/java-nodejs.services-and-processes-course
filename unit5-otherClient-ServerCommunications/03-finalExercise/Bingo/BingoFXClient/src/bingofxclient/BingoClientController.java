package bingofxclient;

import bingofxclient.utils.MessageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <h1>Bingo Client Controller</h1>
 * Manages the main view.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class BingoClientController {

    private int matchingNumbers;
    private int[] ticket;
    private Label[] labels;

    @FXML
    private TextField serverAddress;

    @FXML
    private TextField port;

    @FXML
    private Label num1;

    @FXML
    private Label num2;

    @FXML
    private Label num3;

    @FXML
    private Label num4;

    @FXML
    private Label num5;

    @FXML
    private Label result;

    @FXML
    private void connect(ActionEvent event) {
        labels = new Label[]{num1, num2, num3, num4, num5};

        ticket = null;
        matchingNumbers = 0;

        result.setStyle("-fx-foreground-color: #000000");
        result.setText("Waiting to start");

        for(Label l: labels){
            l.setText("");
            l.setStyle("-fx-background-color: #9a9898");
        }

        try
        {
            InetAddress host = InetAddress.getByName(serverAddress.getText());
            Socket socket = new Socket(host, Integer.parseInt(port.getText()));

            ClientThread thread = new ClientThread(socket);
            thread.start();
            thread.setPeriod(Duration.millis(2000));

            thread.setOnSucceeded(eventHandler -> {
                try
                {
                    if(ticket == null)
                    {
                        ticket = thread.getTicket();
                        for(int i = 0; i < labels.length; i++){
                            labels[i].setText(ticket[i] + "");
                        }

                        thread.getSocketOut().writeBoolean(false);
                        thread.getSocketOut().flush();
                    }
                    else if(thread.getValue() == -1)
                    {
                        result.setText("You LOSE");
                        thread.cancel();
                    }
                    else
                    {
                        int number = thread.getValue();
                        result.setText(number + "");

                        for(int i = 0; i < labels.length; i++)
                        {
                            if(Integer.parseInt(labels[i].getText()) == number)
                            {
                                labels[i].setStyle("-fx-background-color: #00ff00");
                                matchingNumbers++;
                                break;
                            }
                        }

                        if(matchingNumbers == ticket.length)
                        {
                            result.setText("You WIN!!");
                            thread.getSocketOut().writeBoolean(true);
                            thread.cancel();
                        }
                        else
                        {
                            thread.getSocketOut().writeBoolean(false);
                            thread.getSocketOut().flush();
                        }
                    }
                } catch (IOException e) {
                }
            });
        } catch (UnknownHostException e) {
        } catch (IOException e) {
            MessageUtils.showIOError();
            result.setText("");
        }
    }
}

