// Adrián Navarro Gabino

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        String[] cmd = {"C:\\Windows\\notepad.exe"};
        ProcessBuilder pb = new ProcessBuilder(cmd);

        try {
            Process p = pb.start();
            Thread.sleep(10000);
            p.destroy();
        } catch (IOException | InterruptedException e) {
        }
    }
}
