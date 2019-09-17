// Adrián Navarro Gabino

public abstract class Store
{
    private double cash;
    private double drinkPrice;

    public Store(double drinkPrice)
    {
        this.drinkPrice = drinkPrice;
        cash = 0;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public abstract void welcome();

    public void payDrinks(int numOfDrinks)
    {
        cash += numOfDrinks * drinkPrice;
    }
}
