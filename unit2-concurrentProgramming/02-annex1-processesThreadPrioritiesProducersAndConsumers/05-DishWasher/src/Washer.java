// Adrián Navarro Gabino

public class Washer extends Thread
{
    int washedDishes;
    DishPile pile;

    public Washer(int washedDishes, DishPile pile)
    {
        this.washedDishes = washedDishes;
        this.pile = pile;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < washedDishes; i++)
        {
            Dish dish = new Dish(i);
            pile.wash(dish);

            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
        }
    }
}
