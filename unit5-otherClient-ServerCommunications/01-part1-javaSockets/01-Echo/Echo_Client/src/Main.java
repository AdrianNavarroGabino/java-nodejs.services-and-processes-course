import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final String HOST = "localhost";
    public static final int PORT = 6000;

    public static void main(String[] args)
    {
        try (
                Socket mySocket = new Socket(HOST, PORT);
                DataInputStream socketIn = new DataInputStream(mySocket.getInputStream());
                DataOutputStream socketOut = new DataOutputStream(mySocket.getOutputStream());
                Scanner in = new Scanner(System.in);
        )
        {
            String line;
            while (true) {
                line = in.nextLine();
                socketOut.writeUTF(line);
                line = socketIn.readUTF();
                System.out.println("Response : " + line);
                if (line.equals("BYE"))
                    break;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
