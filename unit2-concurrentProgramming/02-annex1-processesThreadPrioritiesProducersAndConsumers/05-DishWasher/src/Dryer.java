// Adrián Navarro Gabino

public class Dryer extends Thread
{
    int driedDishes;
    DishPile pile;

    public Dryer(int driedDishes, DishPile pile)
    {
        this.driedDishes = driedDishes;
        this.pile = pile;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < driedDishes; i++)
        {
            pile.dry();
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
        }
    }
}
