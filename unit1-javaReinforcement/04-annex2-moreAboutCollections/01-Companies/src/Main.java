// Adrián Navarro Gabino

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Comparator<Person> personComparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        };

        SortedMap<Company, TreeSet<Person>> companies = new TreeMap<>(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return Double.compare(o1.getMoney(), o2.getMoney());
            }
        });

        TreeSet<Person> people = new TreeSet<>(personComparator);
        people.add(new Person("Person1", 45));
        people.add(new Person("Person2", 25));
        people.add(new Person("Person3", 30));
        companies.put(new Company("Company1", 32323.32), people);

        people = new TreeSet<>(personComparator);
        people.add(new Person("Person4", 24));
        people.add(new Person("Person5", 26));
        people.add(new Person("Person6", 25));
        companies.put(new Company("Company2", 100000), people);

        people = new TreeSet<>(personComparator);
        people.add(new Person("Person7", 60));
        people.add(new Person("Person8", 25));
        people.add(new Person("Person9", 20));
        companies.put(new Company("Company3", 25000), people);

        for(Company c: companies.keySet())
        {
            System.out.println("Company: " + c.getName());
            System.out.println("Money: " + c.getMoney());
            System.out.println();
            System.out.println("Employees:");
            System.out.println();

            for(Person p: companies.get(c))
            {
                System.out.println("Name: " + p.getName() + ", age: " +
                        p.getAge());
            }

            System.out.println();
            System.out.println();
        }
    }
}
