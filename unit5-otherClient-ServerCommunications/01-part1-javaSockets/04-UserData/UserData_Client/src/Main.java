// Adrián Navarro Gabino

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final int PORT = 6000;

    public static void main(String[] args)
    {
        try (
                Socket mySocket = new Socket("localhost", PORT);
                ObjectInputStream socketIn = new ObjectInputStream(mySocket.getInputStream());
                ObjectOutputStream socketOut = new ObjectOutputStream(mySocket.getOutputStream());
                Scanner sc = new Scanner(System.in);
        )
        {
            User u = (User)(socketIn.readObject());

            System.out.print("Login: ");
            u.setLogin(sc.nextLine());
            System.out.print("Password: ");
            u.setPassword(sc.nextLine());

            socketOut.writeObject(u);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
