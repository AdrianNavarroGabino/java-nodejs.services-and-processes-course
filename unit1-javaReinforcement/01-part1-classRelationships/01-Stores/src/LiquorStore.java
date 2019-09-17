// Adrián Navarro Gabino

public class LiquorStore extends Store
{
    private int tax;

    public LiquorStore(double drinkPrice, int tax)
    {
        super(drinkPrice);
        this.tax = tax;
    }

    @Override
    public void welcome() {
        System.out.println("Welcome to our liquor store. " +
                "If you are younger than 18, go back home!");
    }

    @Override
    public void payDrinks(int numOfDrinks)
    {
        super.payDrinks(numOfDrinks);
        super.setCash(getCash() +
            numOfDrinks * getDrinkPrice() * tax / 100);
    }
}
