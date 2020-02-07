import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Main {
    public static final int PORT = 6000;

    public static void main(String[] args) {
        MulticastSocket socket;

        try
        {
            socket = new MulticastSocket(PORT);
            InetAddress host = InetAddress.getByName("225.0.0.1");
            socket.joinGroup(host);

            String message = "";
            byte[] buffer;

            while(!message.equals("finish"))
            {
                buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                message = new String(packet.getData());
                System.out.println(message);
            }

            socket.leaveGroup(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
