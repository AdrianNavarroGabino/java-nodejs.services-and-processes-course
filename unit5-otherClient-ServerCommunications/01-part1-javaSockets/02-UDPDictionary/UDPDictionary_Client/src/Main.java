// Adrián Navarro Gabino

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        try (DatagramSocket mySocket = new DatagramSocket())
        {
            mySocket.setSoTimeout(5000);

            System.out.print("Enter the word to translate: ");
            String word = new Scanner(System.in).nextLine();
            byte[] message = word.getBytes();
            DatagramPacket packetS = new DatagramPacket(message, message.length,
                    InetAddress.getLocalHost(), 6000);
            mySocket.send(packetS);

            try
            {
                byte[] buffer = new byte[1024];
                DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
                mySocket.receive(packetR);
                System.out.println("Translation: " + new String(packetR.getData()).trim());
            } catch (InterruptedIOException e) {
                System.out.println("No translation found");
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
