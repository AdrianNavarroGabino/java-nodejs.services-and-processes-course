// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        DishPile pile = new DishPile();
        Washer washer = new Washer(20, pile);
        Dryer dryer = new Dryer(20, pile);
        washer.start();
        dryer.start();
    }
}
