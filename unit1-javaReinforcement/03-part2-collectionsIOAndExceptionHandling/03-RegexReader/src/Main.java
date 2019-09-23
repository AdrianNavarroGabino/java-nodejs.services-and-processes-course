// Adrián Navarro Gabino

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = sc.nextLine();

        try (RegexReader regexReader = new RegexReader(
                new FileReader(filename), ".*\\d{2}/\\d{2}/(\\d{2}|\\d{4}).*");)
        {
            String line = regexReader.readLine();

            while(line != null)
            {
                System.out.println(line);
                line = regexReader.readLine();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
