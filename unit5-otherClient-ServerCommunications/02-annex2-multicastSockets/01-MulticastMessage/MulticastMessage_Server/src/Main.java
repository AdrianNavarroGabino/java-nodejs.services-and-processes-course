import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Main {
    public static final int PORT = 6000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "";

        try
        {
            MulticastSocket socket = new MulticastSocket(PORT);
            InetAddress host = InetAddress.getByName("225.0.0.1");
            socket.joinGroup(host);

            while(!message.equals("finish"))
            {
                System.out.print("Message: ");
                message = sc.nextLine();

                DatagramPacket packet = new DatagramPacket(message.getBytes(),
                        message.length(), host, PORT);

                socket.send(packet);
            }

            socket.leaveGroup(host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
