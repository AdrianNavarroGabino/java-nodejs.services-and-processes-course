public class ThreadCount extends Thread
{
    Thread waitThread;
    int number;

    public ThreadCount(String name)
    {
        number = 1;
        this.setName(name);
        waitThread = null;
    }

    public ThreadCount(String name, Thread waitThread)
    {
        number = 1;
        this.setName(name);
        this.waitThread = waitThread;
    }

    public int getNumber() { return number; }

    @Override
    public void run()
    {
        if(waitThread != null)
        {
            try {
                waitThread.join();
            } catch (InterruptedException e) { }
        }

        while(number <= 1000)
        {
            System.out.println("Thread " + getName() + ": " + number);
            number++;
        }
    }
}
