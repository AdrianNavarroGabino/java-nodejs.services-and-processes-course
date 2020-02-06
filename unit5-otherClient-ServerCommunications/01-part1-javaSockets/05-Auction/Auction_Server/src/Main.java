import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int PORT = 6000;
    public static final int NUM_CLIENTS = 3;

    public static void main(String[] args)
    {
        List<Client> clients = new ArrayList<>();

        Product product = new Product("Xbox One", 100);

        try (DatagramSocket socket = new DatagramSocket(PORT, InetAddress.getLocalHost());)
        {
            while (clients.size() < NUM_CLIENTS)
            {
                try
                {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packetR = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packetR);

                    int destPort = packetR.getPort();
                    InetAddress destAddr = packetR.getAddress();

                    boolean exists = false;
                    for(int i = 0; i < clients.size() && !exists; i++)
                    {
                        if (clients.get(i).getAddress().equals(destAddr) &&
                                clients.get(i).getPort() == destPort)
                        {
                            exists = true;
                        }
                    }
                    if (!exists)
                    {
                        System.out.println("Client added");
                        clients.add(new Client(destAddr, destPort));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            for(Client c: clients)
            {
                try
                {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    ObjectOutputStream objOut = new ObjectOutputStream(bs);
                    objOut.writeObject(product);
                    byte[] bytes = bs.toByteArray();
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length, c.getAddress(), c.getPort());
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println();

            for (int i = 0; i < clients.size(); i++)
            {
                try
                {
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    int destPort = packet.getPort();
                    InetAddress destAddr = packet.getAddress();

                    String data = new String(packet.getData()).trim();
                    System.out.println("Received bid: " + data);
                    String[] parts = data.split(" ");
                    Client c = null;
                    for (int j = 0; j < clients.size() && c==null; j++)
                    {
                        if (clients.get(j).getAddress().equals(destAddr) && clients.get(j).getPort() == destPort)
                            c = clients.get(j);
                    }
                    if (c != null)
                    {
                        System.out.println("Updating bid from " + parts[0]);
                        c.setName(parts[0]);
                        c.setBid(Float.parseFloat(parts[1]));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println();


            Client maxClient = null;
            float maxBid = -1;
            for (Client c: clients)
            {
                if (c.getBid() > maxBid)
                {
                    maxClient = c;
                    maxBid = c.getBid();
                }
            }

            for(Client c: clients)
            {
                try
                {
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    ObjectOutputStream objOut = new ObjectOutputStream(bs);
                    objOut.writeObject(maxClient);
                    byte[] bytes = bs.toByteArray();
                    DatagramPacket packet = new DatagramPacket(bytes, bytes.length, c.getAddress(), c.getPort());
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
