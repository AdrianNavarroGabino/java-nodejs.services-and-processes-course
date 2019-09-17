// Adrián Navarro Gabino

public class Main {
    public static void main(String[] args)
    {
        LiquorStore ls = new LiquorStore(8.95, 20);
        ls.payDrinks(10);
        System.out.println(ls.getCash());

        Store store = new Store(8.95)
        {
            @Override
            public void welcome()
            {
                System.out.println("Welcome to anonymous store! Our drink price is " +
                        getDrinkPrice() +"€");
            }
        };

        store.welcome();
        store.payDrinks(10);
        System.out.println(String.format("%.2f", store.getCash()));
    }
}
