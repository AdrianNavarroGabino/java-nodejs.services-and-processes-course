// Adrián Navarro Gabino

package unit1_extra_exercise;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Unit1_Extra_Exercise 
{
    public static float calculateAverage(List<Product> products, String category)
    {
        float priceSum = products.stream()
                .filter(a -> a.getCategory().equalsIgnoreCase(category))
                .map(a -> a.getPrice())
                .reduce(0f, (a,b) -> a + b);

        long numOfProducts = products.stream()
                .filter(a -> a.getCategory().equalsIgnoreCase(category))
                .count();

        return priceSum / numOfProducts;
    }

    public static BiFunction<List<Product>, Float, String> moreThan = (list, price) -> {
        String result = list.stream()
                .filter(a -> a.getPrice() > price)
                .map(a -> a.getName())
                .collect(Collectors.joining(", "));
        return result;
    };

    public static void main(String[] args) 
    {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("Assassin's Creed", "Videogames", 19.95f));
        products.add(new Product("MacBook Air 13\"", "Laptops", 1195));
        products.add(new Product("Lenovo Yoga 14\"", "Laptops", 549.95f));
        products.add(new Product("BQ Curie 8\"", "Tablets", 69.45f));
        products.add(new Product("Samsung Galaxy Tab 3 9.7\"", "Tablets", 210.25f));
        products.add(new Product("iPad Mini 3", "Tablets", 399.95f));
        products.add(new Product("Microsoft Surface 3", "Tablets", 419.75f));
        products.add(new Product("The Lasf of Us", "Videogames", 49.95f));
        products.add(new Product("Fifa 17", "Videogames", 69.95f));

        Collections.sort(products, (a,b) -> {
           int order = a.getCategory().toUpperCase().compareTo(b.getCategory().toUpperCase());
           if(order == 0)
           {
               return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
           }
           return order;
        });

        products.stream().forEach(System.out::println);

        System.out.println();

        products.stream()
                .filter(a -> a.getCategory().equalsIgnoreCase("tablets"))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Average Videogames price: " +
                String.format("%.2f", calculateAverage(products, "videogames")) +
                "€");

        System.out.println();
        System.out.println("Products more expensive than 100€: " +
                moreThan.apply(products, 100f));

        HashMap<String, Long> finalResult = new HashMap<>();

        System.out.println();
        products.stream()
                .forEach(a -> {
                    long result = products.stream()
                            .filter(b -> b.getCategory().equals(a.getCategory()))
                            .count();
                    finalResult.put(a.getCategory(), result);
                });

        Set set = finalResult.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            System.out.print(mentry.getKey() + ": ");
            System.out.println(mentry.getValue());
        }
    }
}
