package bingoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>Bingo Server</h1>
 * Main class of the server side.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class BingoServer {
    /**
     * Max number of players.
     */
    public static final int MAX_CONNECTIONS = 3;

    /**
     * Max number in the bingo.
     */
    public static final int MAX_NUMBER = 20;

    /**
     * Connection port.
     */
    public static final int PORT = 6000;

    /**
     * List of sockets.
     */
    public static List<Socket> sockets;

    /**
     * Bingo numbers messy.
     */
    public static List<Integer> numbers;

    /**
     * Boolean to check if the game has finished.
     */
    public static boolean hasFinished;

    /**
     * Main method of the class to connect the players.
     * @param args Console args
     */
    public static void main(String[] args) {
        hasFinished = false;

        int connected = 0;

        numbers = IntStream.rangeClosed(1, MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        try(ServerSocket server = new ServerSocket(PORT))
        {
            sockets = new ArrayList<>();
            while(connected < MAX_CONNECTIONS)
            {
                Socket service = server.accept();
                sockets.add(service);
                connected++;
            }

            for(int i = 0; i < MAX_CONNECTIONS; i++)
            {
                ServerThread st = new ServerThread(sockets.get(i));
                st.start();
            }
        } catch(IOException e)
        {
        }
    }
}
