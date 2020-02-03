// Adrián Navarro Gabino

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
    public static final int PORT = 6000;

    public static void main(String[] args)
    {
        try (
                ServerSocket server = new ServerSocket(PORT);
                Socket service = server.accept();
                DataInputStream socketIn = new DataInputStream(service.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(service.getOutputStream());
        )
        {
            String line;
            while (true) {
                line = socketIn.readUTF();
                System.out.println("Read: " + line);

                socketOut.writeUTF(line.toUpperCase());

                if (line.equals("bye"))
                    break;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
