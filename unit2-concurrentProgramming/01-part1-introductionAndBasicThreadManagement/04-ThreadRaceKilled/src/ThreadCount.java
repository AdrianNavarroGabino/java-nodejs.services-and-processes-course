public class ThreadCount extends Thread
{
    int number;
    boolean finish;

    public ThreadCount(String name)
    {
        finish = false;
        number = 1;
        this.setName(name);
    }

    public void setFinish(boolean finish) { this.finish = finish; }

    public int getNumber() { return number; }

    @Override
    public void run()
    {
        while(!finish && number < 1000)
        {
            System.out.println("Thread " + getName() + ": " + number);
            number++;
        }
    }
}
