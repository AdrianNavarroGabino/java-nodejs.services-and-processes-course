// Adrián Navarro Gabino

public class Main
{
    public static void main(String[] args)
    {
        MultiplyTable mt = new MultiplyTable(1);
        mt.start();

        for(int i = 2; i <= 10; i++)
        {
            mt = new MultiplyTable(i, mt);
            mt.start();
        }
    }
}
