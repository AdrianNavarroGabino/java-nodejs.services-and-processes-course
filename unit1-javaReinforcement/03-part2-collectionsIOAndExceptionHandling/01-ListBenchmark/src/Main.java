// Adrián Navarro Gabino

import java.time.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Instant start, end;
        Duration dur;

        Random ran = new Random();

        List<Double> arrayList = new ArrayList<>();
        List<Double> linkedList = new LinkedList<>();

        // 1. Add 100.000 (double) random items always at position 0.

        start = Instant.now();
        for(int i = 0; i < 100000; i++)
        {
            arrayList.add(0, ran.nextDouble());
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf("ArrayList: The operation add 100.000 (double) " +
                        "random items always at position 0 takes: %dms\n",
                dur.toMillis());

        start = Instant.now();
        for(int i = 0; i < 100000; i++)
        {
            linkedList.add(0, ran.nextDouble());
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf("LinkedList: The operation add 100.000 (double) " +
                        "random items always at position 0 takes: %dms\n",
                dur.toMillis());

        // 2. Delete the first 50.000 items (always delete the first one).

        start = Instant.now();
        for(int i = 0; i < 50000; i++)
        {
            arrayList.remove(0);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf("ArrayList: The operation remove the first 50.000 " +
                        "items takes: %dms\n",
                dur.toMillis());

        start = Instant.now();
        for(int i = 0; i < 50000; i++)
        {
            linkedList.remove(0);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf("LinkedList: The operation remove the first 50.000 " +
                        "items takes: %dms\n",
                dur.toMillis());

        // 3. Add 50.000 random items in random positions.

        start = Instant.now();
        for(int i = 0; i < 50000; i++)
        {
            arrayList.add(ran.nextInt(50000), ran.nextDouble());
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf(
                "ArrayList: The operation add 50.000 items in random " +
                        "positions takes: %dms\n",
                dur.toMillis());

        start = Instant.now();
        for(int i = 0; i < 50000; i++)
        {
            linkedList.add(ran.nextInt(50000), ran.nextDouble());
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf(
                "LinkedList: The operation add 50.000 items in random " +
                        "positions takes: %dms\n",
                dur.toMillis());

        // 4. Delete 50.000 items from random positions.

        start = Instant.now();
        for(int i = 0; i < 50000; i++)
        {
            arrayList.remove(ran.nextInt(50000));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf(
                "ArrayList: The operation remove 50.000 items in random " +
                        "positions takes: %dms\n",
                dur.toMillis());

        start = Instant.now();
        for(int i = 0; i < 50000; i++)
        {
            linkedList.remove(ran.nextInt(50000));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf(
                "LinkedList: The operation remove 50.000 items in random " +
                        "positions takes: %dms\n",
                dur.toMillis());

        // 5. Delete items that are in even positions using an Iterator.

        Iterator<Double> arrayListIt = arrayList.iterator();
        Iterator<Double> linkedListIt = linkedList.iterator();
        int arrayListCount = 0;
        int linkedListCount = 0;

        start = Instant.now();
        while(arrayListIt.hasNext())
        {
            arrayListIt.next();
            if(arrayListCount % 2 == 0)
            {
                arrayListIt.remove();
            }

            arrayListCount++;
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf(
                "ArrayList: The operation remove items that are in even " +
                        "positions using an Iterator takes: %dms\n",
                dur.toMillis());

        start = Instant.now();
        while(linkedListIt.hasNext())
        {
            linkedListIt.next();
            if(linkedListCount % 2 == 0)
            {
                linkedListIt.remove();
            }

            linkedListCount++;
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        System.out.printf(
                "LinkedList: The operation remove items that are in even " +
                        "positions using an Iterator takes: %dms\n",
                dur.toMillis());
    }
}
