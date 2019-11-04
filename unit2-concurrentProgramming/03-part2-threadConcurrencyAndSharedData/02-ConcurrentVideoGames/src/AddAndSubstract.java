// Adrián Navarro Gabino

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

class AddAndSubstract extends Thread
{
    LinkedBlockingDeque<VideoGame> videoGames;
    boolean add;

    public AddAndSubstract(LinkedBlockingDeque<VideoGame> videoGames, boolean add)
    {
        this.videoGames = videoGames;
        this.add = add;
    }

    @Override
    public void run()
    {
        Iterator<VideoGame> iterator = videoGames.iterator();

        while (iterator.hasNext())
        {
            VideoGame game = iterator.next();

            if(add)
                game.addPrice(1);
            else
                game.addPrice(-1);
            try
            {
                Thread.sleep(50);
            }
            catch (Exception e) { }
        }
    }
}
