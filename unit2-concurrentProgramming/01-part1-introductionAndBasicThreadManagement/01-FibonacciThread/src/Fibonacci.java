import java.util.ArrayList;
import java.util.List;

public class Fibonacci extends Thread
{
    private int limit;

    public Fibonacci(int limit)
    {
        this.limit = limit;
    }

    @Override
    public void run()
    {
        List<Long> fibonacci = new ArrayList<>();
        fibonacci.add(0L);
        fibonacci.add(1L);

        System.out.println(fibonacci.get(0));
        System.out.println(fibonacci.get(1));

        for(int i = 2; i < limit; i++)
        {
            fibonacci.add(fibonacci.get(i - 1) + fibonacci.get(i - 2));
            System.out.println(fibonacci.get(i));
        }
    }
}