// Adrián Navarro Gabino

public class MultiplyTable extends Thread
{
    Thread waitThread;
    private int num;

    public MultiplyTable(int num)
    {
        this.num = num;
        waitThread = null;
    }

    public MultiplyTable(int num, Thread waitThread)
    {
        this.num = num;
        this.waitThread = waitThread;
    }

    @Override
    public void run()
    {
        if(waitThread != null)
        {
            try {
                waitThread.join();
            } catch (InterruptedException e) { }
        }

        for(int i = 0; i <= 10; i++)
        {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }
}
