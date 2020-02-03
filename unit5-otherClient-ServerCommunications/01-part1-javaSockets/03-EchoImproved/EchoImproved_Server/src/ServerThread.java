// Adrián Navarro Gabino

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread
{
    Socket service;

    public ServerThread(Socket service)
    {
        this.service = service;
    }

    @Override
    public void run()
    {
        DataInputStream socketIn = null;
        DataOutputStream socketOut = null;

        try
        {
            socketIn = new DataInputStream(service.getInputStream());
            socketOut = new DataOutputStream(service.getOutputStream());

            String line;
            while(true) {
                line = socketIn.readUTF();
                System.out.println("Read: " + line);

                socketOut.writeUTF(line.toUpperCase());

                if(line.equals("bye")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if(socketOut != null)
                    socketOut.close();
            } catch (IOException ex) {}
            try {
                if(socketIn != null)
                    socketIn.close();
            } catch (IOException ex) {}
            try {
                if(service != null)
                    service.close();
            } catch(IOException ex) {}
        }

    }

}