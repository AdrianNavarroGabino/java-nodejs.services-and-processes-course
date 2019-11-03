// Adrián Navarro Gabino

import java.util.Random;

public class ThreadCount extends Thread
{
    int num;
    int priority;

    public ThreadCount(String name, int priority)
    {
        this.setName(name);
        num = 1;

        if(priority < 1)
        {
            this.priority = 1;
        }
        else if(priority > 10)
        {
            this.priority = 10;
        }
        else
        {
            this.priority = priority;
        }
    }

    @Override
    public void run()
    {
        Random rn = new Random(System.currentTimeMillis());

        for(int i = num; i <= 1000; i++)
        {
            int number = rn.nextInt(10) + 1;
            if(number >= priority)
            {
                try
                {
                    Thread.sleep(5);
                } catch (InterruptedException e) { }
            }
            System.out.println("Thread " + this.getName() + ": " + i);
        }
    }
}
