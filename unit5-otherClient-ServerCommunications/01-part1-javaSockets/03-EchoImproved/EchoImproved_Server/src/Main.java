// Adrián Navarro Gabino

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static final int PORT = 6000;

    public static void main(String[] args)
    {
        try(ServerSocket server = new ServerSocket(PORT))
        {
            while(true)
            {
                Socket service = server.accept();
                ServerThread st = new ServerThread(service);
                st.start();
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
