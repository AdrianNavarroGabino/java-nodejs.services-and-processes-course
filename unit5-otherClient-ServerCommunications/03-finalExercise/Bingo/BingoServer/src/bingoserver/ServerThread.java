package bingoserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>Server Thread</h1>
 * Thread to manage every player in the server side.
 * @author Adrian Navarro Gabino
 * @version 1.0
 */
public class ServerThread extends Thread {
    private Socket service;
    private List<Integer> assignedNumbers;
    private int turn;
    public static boolean end;

    /**
     * Performs the complete initialization.
     * @param service Socket
     */
    public ServerThread(Socket service)
    {
        this.service = service;
        assignedNumbers = IntStream.rangeClosed(1, BingoServer.MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(assignedNumbers);
        assignedNumbers = assignedNumbers.stream()
                                        .limit(5)
                                        .collect(Collectors.toList());
        turn = 0;
        end = false;
    }

    /**
     * Main method of the thread. It manages all the game in the server side.
     */
    @Override
    public void run()
    {
        DataInputStream socketIn = null;
        ObjectOutputStream socketOut = null;

        try
        {
            socketIn = new DataInputStream(service.getInputStream());
            socketOut = new ObjectOutputStream(service.getOutputStream());

            socketOut.writeObject(assignedNumbers.stream()
                                                .mapToInt(i -> i)
                                                .toArray());
            socketOut.flush();

            Thread.sleep(2000);

            while(!BingoServer.hasFinished)
            {
                BingoServer.hasFinished = socketIn.readBoolean();

                if(!BingoServer.hasFinished && !end)
                {
                    int number = BingoServer.numbers.get(turn);
                    socketOut.writeInt(number);
                    socketOut.flush();
                    turn++;
                }
                else
                {
                    end = true;
                    socketOut.writeInt(-1);
                    socketOut.flush();
                }

                Thread.sleep(2000);
            }

            socketOut.writeInt(-1);
        }
        catch(IOException | InterruptedException e)
        {
        }
        finally
        {
            try
            {
                if(socketOut != null)
                {
                    socketOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try
            {
                if(socketIn != null)
                {
                    socketIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try
            {
                if(service != null)
                {
                    service.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
