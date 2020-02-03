// Adrián Navarro Gabino

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static final int PORT = 6000;

    public static void main(String[] args)
    {
        InetAddress host = null;

        try (
                Scanner sc = new Scanner(System.in);
        )
        {
            System.out.print("Enter the host: ");
            host = InetAddress.getByName(sc.nextLine());

            try (
                    Socket mySocket = new Socket(host, PORT);
                    DataInputStream socketIn = new DataInputStream(mySocket.getInputStream());
                    DataOutputStream socketOut = new DataOutputStream(mySocket.getOutputStream());
            )
            {
                String line;
                while (true)
                {
                    line = sc.nextLine();
                    socketOut.writeUTF(line);
                    line = socketIn.readUTF();
                    System.out.println("Response : " + line);
                    if (line.equals("BYE"))
                        break;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host: " + ex.getMessage());
            System.exit(-1);
        }
    }
}
