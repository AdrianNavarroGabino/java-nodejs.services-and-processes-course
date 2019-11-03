// Adrián Navarro Gabino

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter path: ");
        String path = sc.nextLine();

        ArrayList<String> list = new ArrayList<>();
        String line = "";
        Runtime rt = Runtime.getRuntime();

        try
        {
            Process p = rt.exec("find " + path + " -name *.png");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            while((line = br.readLine()) != null)
            {
                if(line.endsWith(".png"))
                {
                    list.add(line);
                }
            }
        } catch (IOException e) { }

        if(list.size() == 0)
        {
            System.out.println("No results");
        }
        else
        {
            System.out.println("PNG files:");
            list.stream().forEach(System.out::println);
        }
    }
}
