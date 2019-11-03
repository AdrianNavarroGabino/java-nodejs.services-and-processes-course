// Adrián Navarro Gabino

import java.util.Stack;

public class DishPile
{
    public int numberOfDishes = 5;
    Stack<Dish> pile;

    public DishPile()
    {
        pile = new Stack<>();
    }

    public synchronized void wash(Dish dish)
    {
        if(pile.size() == numberOfDishes)
        {
            try
            {
                wait();
            } catch (InterruptedException e) { }
        }

        pile.push(dish);
        System.out.println("Dish " + dish.getNumber() + " washed");
        notify();
    }

    public synchronized void dry()
    {
        if(pile.size() == 0)
        {
            try
            {
                wait();
            } catch (InterruptedException e) { }
        }

        Dish dish = pile.pop();
        System.out.println("Dish " + dish.getNumber() + " dried");
        notify();
    }
}
