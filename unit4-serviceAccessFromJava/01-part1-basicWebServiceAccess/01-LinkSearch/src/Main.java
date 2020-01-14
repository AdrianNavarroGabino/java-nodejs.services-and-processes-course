// Adrián Navarro Gabino

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter address:");
        String address = sc.nextLine();

        LinkSearch.getLinks(address).stream().forEach(System.out::println);
    }
}
