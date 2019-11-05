// Adrián Navarro Gabino

import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random rn = new Random(System.currentTimeMillis());
        MyData mds = new MyData(10);
        ReadingThread[] threadsR = new ReadingThread[10];
        WritingThread[] threadsW = new WritingThread[2];

        for (int i = 0; i < threadsW.length; i++)
        {
            threadsW[i] = new WritingThread(mds, rn);
        }
        for (int i = 0; i < threadsR.length; i++)
        {
            threadsR[i] = new ReadingThread(mds, rn);
        }
        for (int i = 0; i < threadsW.length; i++)
        {
            threadsW[i].start();
        }
        for (int i = 0; i < threadsR.length; i++)
        {
            threadsR[i].start();
        }

    }
}
