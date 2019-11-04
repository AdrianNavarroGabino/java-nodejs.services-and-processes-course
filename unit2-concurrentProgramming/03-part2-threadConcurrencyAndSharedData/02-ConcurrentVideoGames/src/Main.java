// Adrián Navarro Gabino

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

public class Main
{
    public static void main(String[] args)
    {
        LinkedBlockingDeque<VideoGame> videoGames =
                new LinkedBlockingDeque<VideoGame>();

        for (int i = 1; i <= 100; i++)
        {
            videoGames.add(new VideoGame("Videogame #" + i, 60));
        }

        AddAndSubstract addObject = new AddAndSubstract(videoGames, true);
        AddAndSubstract substractObject =
                new AddAndSubstract(videoGames, false);
        addObject.start();
        substractObject.start();

        do
        {
            try { Thread.sleep(100); } catch (Exception e) {}
        } while (addObject.isAlive() || substractObject.isAlive());

        float total = 0;

        Iterator<VideoGame> iterator = videoGames.iterator();
        while (iterator.hasNext())
        {
            VideoGame videoGame = iterator.next();
            total += videoGame.getPrice();
        }

        System.out.println("Price: " + total);
    }

}
