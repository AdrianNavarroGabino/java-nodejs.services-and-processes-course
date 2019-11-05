// Adrián Navarro Gabino

import java.util.Random;

public class WritingThread extends Thread
{
    MyData sharedData;
    Random rn;

    public WritingThread(MyData sharedData, Random rn)
    {
        this.rn = rn;
        this.sharedData = sharedData;
    }
    @Override
    public void run()
    {
        int time = rn.nextInt(10) + 1;
        try
        {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) { }
        sharedData.setValue(10);
    }
}