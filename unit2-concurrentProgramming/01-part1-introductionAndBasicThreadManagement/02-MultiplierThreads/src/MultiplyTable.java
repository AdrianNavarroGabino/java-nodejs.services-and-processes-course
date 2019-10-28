public class MultiplyTable extends Thread
{
    private int num;

    public MultiplyTable(int num)
    {
        this.num = num;
    }

    @Override
    public void run()
    {
        for(int i = 0; i <= 10; i++)
        {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }
}
