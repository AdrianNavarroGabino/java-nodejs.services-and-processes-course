public class ThreadCount extends Thread
{
    public ThreadCount(String name)
    {
        this.setName(name);
    }

    @Override
    public void run()
    {
        for(int i = 1; i <= 1000; i++)
        {
            System.out.println("Thread " + this.getName() + ": " + i);
        }
    }
}
