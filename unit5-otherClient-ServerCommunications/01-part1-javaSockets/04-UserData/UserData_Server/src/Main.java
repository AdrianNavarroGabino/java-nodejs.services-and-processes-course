// Adrián Navarro Gabino

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final int PORT = 6000;

    User user;

    public static void main(String[] args)
    {
        try (
                ServerSocket server = new ServerSocket(PORT);
                Socket service = server.accept();
                ObjectOutputStream socketOut = new ObjectOutputStream(service.getOutputStream());
                ObjectInputStream socketIn = new ObjectInputStream(service.getInputStream());
        )
        {

            User u = new User();
            socketOut.writeObject(u);

            u = (User)(socketIn.readObject());
            System.out.println(u);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
