// Adrián Navarro Gabino

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        HashMap dictionary = new HashMap();
        dictionary.put("Hello", "Hola");
        dictionary.put("Bye", "Adiós");
        dictionary.put("Dog", "Perro");
        dictionary.put("Cat", "Gato");

        try (DatagramSocket mySocket = new DatagramSocket(
                6000, InetAddress.getLocalHost()))
        {
            byte[] buffer = new byte[1024];
            DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
            mySocket.receive(packetR);
            String word = new String(packetR.getData()).trim();
            System.out.println("Received: " + word);

            int destPort = packetR.getPort();
            InetAddress destAddr = packetR.getAddress();

            if (dictionary.get(word) != null)
            {
                String text = (String)(dictionary.get(word));
                byte[] message = text.getBytes();
                DatagramPacket packetS = new DatagramPacket(
                        message, message.length, destAddr, destPort);
                mySocket.send(packetS);
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
