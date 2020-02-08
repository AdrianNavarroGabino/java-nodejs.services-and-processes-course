package bingofxclient;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * <h1>Client Thread</h1>
 * Thread to manage every player in the client side.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class ClientThread extends ScheduledService<Integer> {
    private Socket socket;
    private int[] ticket;
    private DataOutputStream socketOut;
    private ObjectInputStream socketIn;

    /**
     * Performs the complete initialization.
     * @param socket Socket
     */
    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    /**
     * Gets the ticket with the numbers to play.
     * @return Game ticket
     */
    public int[] getTicket() { return ticket; }

    /**
     * Gets the DataOutputStream to send information to the server side.
     * @return the DataOutputStream
     */
    public DataOutputStream getSocketOut() { return socketOut; }

    @Override
    protected Task<Integer> createTask() {
        return new Task<>() {
            @Override
            protected Integer call() {
                try
                {
                    if(ticket == null)
                    {
                        socketOut = new DataOutputStream(socket.getOutputStream());
                        socketIn = new ObjectInputStream(socket.getInputStream());
                        ticket = Arrays
                                .stream((int[])socketIn.readObject())
                                .sorted()
                                .toArray();
                        return -1;
                    }
                    else
                    {
                        try {
                            int number = socketIn.readInt();
                            return number;
                        }
                        catch(SocketException e)
                        {
                            return -1;
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        };
    }
}
